package main.java.ui.controls;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.converter.NumberStringConverter;
import main.java.domain.entities.Book;
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

        quantity.textProperty().bindBidirectional(book.quantityProperty(), new NumberStringConverter());
    }
}
