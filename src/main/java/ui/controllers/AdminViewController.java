package main.java.ui.controllers;

import com.sun.scenario.effect.impl.sw.java.JSWBlend_HARD_LIGHTPeer;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.domain.entities.Book;
import main.java.domain.entities.User;
import main.java.domain.enums.Genre;
import main.java.domain.enums.Language;
import main.java.ui.common.interfaces.ISceneManager;
import main.java.ui.controls.AddBookControl;
import main.java.util.interfaces.IAuthManager;
import main.java.util.interfaces.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

@Component
public class AdminViewController implements Initializable {

    private final IBookRepository _bookRepository;

    private final IAuthManager _authManager;

    private final ISceneManager _sceneManager;

    private ObservableList<Book> books;

    @FXML
    private AddBookControl addBookControl;

    @FXML
    private Button logoutBtn;

    @FXML
    private TableView<Book> booksTbl;

    @FXML
    private TableColumn<Book, String> barcode, title, type, length, date;

    @FXML
    private TableColumn<Book, Language> language;

    @FXML
    private TableColumn<Book, Genre> genre;

    @FXML
    private TableColumn<Book, Integer> quantity;

    @FXML
    private TableColumn<Book, Double> price;

    @Autowired
    public AdminViewController(IBookRepository bookRepository,
                               IAuthManager authManager,
                               ISceneManager sceneManager) {
        _bookRepository = bookRepository;
        _authManager = authManager;
        _sceneManager = sceneManager;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addBookControl.setAddBookAction((book) -> addBook(book));
        addBookControl.setValidateBookAction((book) -> validateBook(book));

        var user = _authManager.currentUser();

        if (user != null) {
            logoutBtn.setText(user.getUsername() + " Logout");
        }
        logoutBtn.setOnAction((e) -> logout());

        initializeTable();

        books = FXCollections.observableArrayList(_bookRepository.GetAll());

        booksTbl.itemsProperty().bind(new SimpleObjectProperty<>(books));
        booksTbl.getSortOrder().add(quantity);

    }

    private void initializeTable() {
        barcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        language.setCellValueFactory(new PropertyValueFactory<>("language"));
        genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        date.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        type.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().addionalInfoToString()));
        length.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().lengthToString()));

        quantity.setSortType(TableColumn.SortType.ASCENDING);
    }

    private void logout() {
        _authManager.logout();
        _sceneManager.switchScene("login");
    }

    private void addBook(Book book) {
        _bookRepository.Add(book);
        books.add(book);
        booksTbl.sort();
    }

    private List<String> validateBook(Book book) {
        var problems = new ArrayList<String>();

        var barcodePattern = Pattern.compile("^\\d{8}");
        if (!barcodePattern.matcher(book.getBarcode()).matches()) {
            problems.add("Barcode must be 8 digits");
            return problems;
        }
        if (_bookRepository.Get(book.getBarcode()) != null) {
            problems.add(String.format("Book already exists with barcode %s", book.getBarcode()));
            return problems;
        }

        // non-unique barcode
        return problems;
    }
}
