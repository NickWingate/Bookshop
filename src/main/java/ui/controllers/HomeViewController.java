package main.java.ui.controllers;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Popup;
import main.java.domain.entities.AudioBook;
import main.java.domain.entities.Book;
import main.java.domain.entities.Customer;
import main.java.domain.entities.Filter;
import main.java.domain.enums.BookType;
import main.java.ui.common.interfaces.ISceneManager;
import main.java.ui.controls.BookCollectionControl;
import main.java.ui.controls.FilterControl;
import main.java.util.interfaces.IAuthManager;
import main.java.util.interfaces.IBookRepository;
import main.java.util.interfaces.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Component
public class HomeViewController implements Initializable {

    private IBookRepository _bookRepository;
    private IUserRepository _userRepository;
    private IAuthManager _authManager;
    private ISceneManager _sceneManager;

    private Customer customer;

    private Double totalPrice = 0.00d;
    @FXML
    private BookCollectionControl bookStockControl;

    @FXML
    private BookCollectionControl basketControl;

    @FXML
    private FilterControl filterControl;

    private Filter selectedProperties = new Filter();

    @FXML
    private Button logoutBtn;

    @FXML
    private Button checkoutBtn;

    @FXML
    private Button emptyBtn;

    @FXML
    private Label userInfoLbl;

    @FXML
    private Label basketTotalLbl;

    @FXML
    private TextField searchField;

    private ObservableList<Book> stockBooks = FXCollections.observableArrayList();

    private ObservableList<Book> basketBooks = FXCollections.observableArrayList();

    @Autowired
    public HomeViewController(IBookRepository bookRepository,
                              IUserRepository userRepository,
                              IAuthManager authManager,
                              ISceneManager sceneManager) {
        _bookRepository = bookRepository;
        _userRepository = userRepository;
        _authManager = authManager;
        _sceneManager = sceneManager;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        stockBooks.addAll(_bookRepository.GetAll());

        bookStockControl.setActionButtonFunction((Book book, int quantity) -> addToBasket(book, quantity));
        basketControl.setActionButtonFunction((Book book, int quantity) -> removeFromBasket(book));
        filterControl.setFilterChangedEvent((filter) -> searchBooks(searchField.getText(), filter));

        searchField.textProperty().addListener(((observableValue, oldValue, newValue) -> searchBooks(newValue, selectedProperties)));

        customer = (Customer) _authManager.currentUser();

        if (customer != null) {
            userInfoLbl.textProperty().bind(Bindings.format("%.2f", customer.creditBalanceProperty()));
        }
        logoutBtn.setOnAction((e) -> logout());
        checkoutBtn.setOnAction((e) -> checkout());
        emptyBtn.setOnAction((e) -> basketBooks.clear());

        basketBooks.addListener((ListChangeListener.Change<? extends Book> change) -> updateTotal());
        basketControl.setQuantityListener((obs, oldValue, newValue) -> updateTotal());
        setMaxDuration();
    }

    private void setMaxDuration() {
        var maxDuration = 0f;
        for (var stockBook : stockBooks) {
            if (stockBook.getBookType() != BookType.AUDIOBOOK) {
                continue;
            }
            var duration = ((AudioBook) stockBook).getDuration();
            if (duration > maxDuration) {
                maxDuration = duration;
            }
        }

        filterControl.setMaxDuration(maxDuration);
    }

    private void updateTotal() {
        totalPrice = 0d;
        for (var book : basketBooks) {
            totalPrice += book.getPrice() * book.getQuantitySelected();
        }
        basketTotalLbl.setText(String.format("%.2f", totalPrice));
    }

    private void checkout() {
        var balance = customer.getCreditBalance();
        if (balance < totalPrice) {
            return;
        }

        var newBalance = balance - totalPrice;
        customer.setCreditBalance(newBalance);


        var receipt = new Alert(Alert.AlertType.INFORMATION);

        var receiptBody = new TextArea();
        receiptBody.setText(String.format("Thank you for the purchase!\n" +
                "£%.2f paid and your remaining credit balance is £%.2f.\n" +
                "Your delivery address is %s, %s.",
                totalPrice,
                newBalance,
                customer.getSurname(),
                customer.getAddress().toString()));

        receipt.setGraphic(receiptBody);

        receipt.show();
        updateStock();

        // save user balance
        _userRepository.Update(customer.getId(), customer);
    }

    private void updateStock() {
        for (var book : basketBooks) {
            book.setQuantity(book.getQuantity() - book.getQuantitySelected());
            _bookRepository.Update(book.getBarcode(), book);
        }

        basketBooks.clear();
    }


    private void logout() {
        _authManager.logout();
        _sceneManager.switchScene("login");
    }

    private void removeFromBasket(Book book) {
        book.setQuantitySelected(0);
        basketBooks.remove(book);
    }

    private void addToBasket(Book book, int quantity) {
        // todo: buggy when book already added to basket
        if (quantity < 1)
            quantity = 1;

        if (basketBooks.contains(book)){
            return;
        }

        book.setQuantitySelected(quantity);

        basketBooks.add(book);
    }

    private void searchBooks(String searchTerm, Filter properties) {
        selectedProperties = properties;
        stockBooks.clear();

        var allBooks = _bookRepository.GetAll();

//        if (searchTerm.equals("") && (properties == null || properties.isEmpty()) ) {
//            stockBooks.addAll(allBooks);
//            return;
//        }

        var matchingBooks = new ArrayList<Book>();
        for (var book : allBooks) {
            if (bookMeetsConditions(book, searchTerm, properties)) {
                matchingBooks.add(book);
            }
        }
        stockBooks.addAll(matchingBooks);
    }

    private static boolean bookMeetsConditions(Book book, String searchTerm, Filter properties) {
        if (!properties.meetsConditions(book))
            return false;

        return book.getTitle().toLowerCase().contains(searchTerm.toLowerCase()) ||
                book.getBarcode().contains(searchTerm);
    }

    public ObservableList<Book> getStockBooks() {
        return stockBooks;
    }

    public ObservableList<Book> getBasketBooks() {
        return basketBooks;
    }
}
