package main.java.ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.java.domain.entities.Book;
import main.java.domain.enums.IBookProperty;
import main.java.ui.controls.BookCollectionControl;
import main.java.ui.controls.FilterControl;
import main.java.util.file.BookEncoder;
import main.java.util.file.BookParser;
import main.java.util.file.CSVWriter;
import main.java.util.interfaces.IBookCloner;
import main.java.util.interfaces.IBookRepository;
import main.java.util.misc.BookCloner;
import main.java.util.repositories.BookRepository;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeViewController implements Initializable {

    private IBookRepository _bookRepository;

    @FXML
    private BookCollectionControl bookStockControl;

    @FXML
    private BookCollectionControl basketControl;

    @FXML
    private FilterControl filterControl;

    private List<IBookProperty> selectedProperties = new ArrayList<>();

    @FXML
    private Button addBook;

    @FXML
    private Button search;

    @FXML
    private TextField searchField;

    private ObservableList<Book> stockBooks = FXCollections.observableArrayList();

    private ObservableList<Book> basketBooks = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        _bookRepository = new BookRepository(new BookParser(),
                new CSVWriter<Book>(new BookEncoder()),
                "src/main/resources/Stock.txt");

        stockBooks.addAll(_bookRepository.GetAll());

        bookStockControl.setActionButtonFunction((Book book, int quantity) -> addToBasket(book, quantity));
        basketControl.setActionButtonFunction((Book book, int quantity) -> removeFromBasket(book));
        filterControl.setFilterChangedEvent((List<IBookProperty> properties) -> searchBooks(searchField.getText() ,properties));

        searchField.textProperty().addListener(((observableValue, oldValue, newValue) -> searchBooks(newValue, selectedProperties)));
    }

    private void removeFromBasket(Book book) {
        book.setQuantitySelected(0);
        basketBooks.remove(book);
    }

    private void addToBasket(Book book, int quantity) {
        // todo: buggy when book already added to basket
        if (quantity < 1)
            return;

        if (basketBooks.contains(book)){
            book.setQuantitySelected(book.getQuantitySelected() + quantity);
            return;
        }

        book.setQuantitySelected(quantity);

        basketBooks.add(book);
    }

    private void searchBooks(String searchTerm, List<IBookProperty> properties) {
        selectedProperties = properties;
        stockBooks.clear();

        var allBooks = _bookRepository.GetAll();

        if (searchTerm.equals("") && properties.isEmpty()) {
            stockBooks.addAll(allBooks);
            return;
        }

        var matchingBooks = new ArrayList<Book>();
        for (var book : allBooks) {
            if (bookMeetsConditions(book, searchTerm, properties)) {
                matchingBooks.add(book);
            }
        }
        stockBooks.addAll(matchingBooks);
    }

    private static boolean bookMeetsConditions(Book book, String searchTerm, List<IBookProperty> properties) {
        if (!book.getProperties().containsAll(properties))
            return false;

        if (!book.getTitle().toLowerCase().contains(searchTerm.toLowerCase()) &&
                !book.getBarcode().contains(searchTerm))
            return false;

        return true;
    }

    public ObservableList<Book> getStockBooks() {
        return stockBooks;
    }

    public ObservableList<Book> getBasketBooks() {
        return basketBooks;
    }
}
