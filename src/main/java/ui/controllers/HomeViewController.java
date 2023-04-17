package main.java.ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.java.domain.entities.AudioBook;
import main.java.domain.entities.Book;
import main.java.domain.enums.AudioFormat;
import main.java.domain.enums.Genre;
import main.java.domain.enums.Language;
import main.java.ui.controls.BookCollectionControl;
import main.java.util.file.BookEncoder;
import main.java.util.file.BookParser;
import main.java.util.file.CSVWriter;
import main.java.util.interfaces.IBookRepository;
import main.java.util.repositories.BookRepository;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class HomeViewController implements Initializable {

    private IBookRepository _bookRepository;
    @FXML
    private BookCollectionControl bookCollectionControl;

    @FXML
    private Button addBook;

    @FXML
    private Button search;

    @FXML
    private TextField searchField;

    private ObservableList<Book> books = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        _bookRepository = new BookRepository(new BookParser(),
                new CSVWriter<Book>(new BookEncoder()),
                "src/main/resources/Stock.txt");
        books.addAll(_bookRepository.GetAll());

//        addBook.setOnAction(actionEvent -> {
//            books.add(new AudioBook(
//                    "11224455",
//                    "A Promised Land",
//                    Language.ENGLISH,
//                    Genre.BIOGRAPHY,
//                    LocalDate.of(2020, 11, 24),
//                    10,
//                    new BigDecimal("30.25"),
//                    4.5f,
//                    AudioFormat.MP3));
//        });

        search.setOnAction(actionEvent -> searchBooks());
    }

    private void searchBooks() {
        books.clear();
        var searchTerm = searchField.getText();

        var allBooks = _bookRepository.GetAll();

        if (searchTerm.equals("")) {
            books.addAll(allBooks);
            return;
        }

        for (var book : allBooks) {
            if (book.getTitle().toLowerCase().contains(searchTerm.toLowerCase())){
                books.add(book);
            }
        }


    }

    public ObservableList<Book> getBooks() {
        return books;
    }
}
