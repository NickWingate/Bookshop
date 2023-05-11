package main.java.ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import main.java.domain.entities.Book;
import main.java.ui.controls.AddBookControl;
import main.java.util.file.BookEncoder;
import main.java.util.file.BookParser;
import main.java.util.file.CSVWriter;
import main.java.util.interfaces.IBookRepository;
import main.java.util.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class AdminViewController implements Initializable {

    private final IBookRepository _bookRepository;

    @FXML
    private AddBookControl addBookControl;

    @Autowired
    public AdminViewController(IBookRepository bookRepository) {
        _bookRepository = bookRepository;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addBookControl.setAddBookAction((book) -> addBook(book));
    }

    private void addBook(Book book) {
        _bookRepository.Add(book);
    }
}
