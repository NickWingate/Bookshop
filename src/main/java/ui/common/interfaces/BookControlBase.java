package main.java.ui.common.interfaces;

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
import javafx.scene.layout.Pane;
import main.java.domain.entities.Book;

import java.time.format.DateTimeFormatter;

public abstract class BookControlBase extends GridPane {


    @FXML
    protected Label title;

    @FXML
    protected Label language;

    @FXML
    protected Label type;

    @FXML
    protected Label price;

    @FXML
    protected Label barcode;

    @FXML
    protected Label length;

    @FXML
    protected Spinner quantitySelected;

    @FXML
    private Button actionButton;

    protected ObjectProperty<Book> book;

    public BookControlBase() {
        super();

        loadFXML();
    }

    public BookControlBase(Book book) {
        this();

        this.book = new SimpleObjectProperty<>(book);

        title.setText(book.getTitle());
        language.setText(book.getLanguage().toString());
        type.setText(book.addionalInfoToString());
        price.setText(String.format("£%.2f", book.getPrice()));
        barcode.setText(book.getBarcode());
        length.setText(book.lengthToString());
        quantitySelected.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(
                0,
                book.getQuantity()
        ));
    }

    private void loadFXML() {
        var loader = new FXMLLoader(getClass()
                .getResource(getFXMLPath()));
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

    protected abstract String getFXMLPath();

    public boolean equalBook(Book toCompare) {
        return book.get().equals(toCompare);
    }

    public void setButtonText(String text) {
        actionButton.textProperty().set(text);
    }

    public void setActionButtonEvent(AddToBasketFunction func) {
        var eventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                func.add(book.get(), (Integer) quantitySelected.getValue());
            }
        };
        actionButton.setOnAction(eventHandler);
    }
}
