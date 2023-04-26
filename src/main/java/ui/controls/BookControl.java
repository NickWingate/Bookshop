package main.java.ui.controls;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.converter.NumberStringConverter;
import main.java.domain.entities.Book;

import java.time.format.DateTimeFormatter;


public class BookControl extends GridPane {

    @FXML
    private Label title;

    @FXML
    private Label language;

    @FXML
    private Label genre;

    @FXML
    private Label type;

    @FXML
    private Label price;

    @FXML
    private Label quantity;

    @FXML
    private Label barcode;

    @FXML
    private Label date;

    @FXML
    private Label length;

    private ObjectProperty<Book> book;

    public BookControl() {

        super();

        var loader = new FXMLLoader(getClass()
                .getResource("../../../resources/fxml/controls/BookControl.fxml"));
        loader.setController(this);

        try {
            Node node = loader.load();
            this.getChildren().add(node);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public BookControl(Book book){
        this();
        this.book = new SimpleObjectProperty<>(book);
        bindLabels();
        title.setText(book.getTitle());
        language.setText(book.getLanguage().toString());
        genre.setText(book.getGenre().toString());
        type.setText(book.addionalInfoToString());
        price.setText(String.format("£%.2f", book.getPrice()));
        quantity.setText(book.getQuantity() + " Available");
        barcode.setText(book.getBarcode());
        date.setText(book.getReleaseDate()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        length.setText(book.lengthToString());
    }

    private void bindLabels() {
        // todo: only quantity is mutable?
        quantity.textProperty().bindBidirectional(book.get().quantityProperty(), new NumberStringConverter());
    }

    public String getBarcode() {
        return barcode.getText();
    }
}
