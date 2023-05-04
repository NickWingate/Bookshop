package main.java.ui.controls;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.converter.NumberStringConverter;
import main.java.domain.entities.Book;
import main.java.ui.common.interfaces.AddToBasketFunction;
import main.java.ui.common.interfaces.BookControlBase;

import java.time.format.DateTimeFormatter;


public class BookControl extends BookControlBase {

    @FXML
    private Label date;

    @FXML
    private Label quantity;

    @FXML
    protected Label genre;

    @Override
    protected String getFXMLPath() {
        return "../../../resources/fxml/controls/BookControl.fxml";
    }

    public BookControl(Book book){
        super(book);

        quantity.setText(book.getQuantity() + " Available");
        genre.setText(book.getGenre().toString());
        date.setText(book.getReleaseDate()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }
}
