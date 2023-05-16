package main.java.ui.controls;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import main.java.domain.entities.Book;
import main.java.ui.common.interfaces.IBookControlAction;

public class BasketControl extends GridPane {

    @FXML
    private BookCollectionControl bookCollection;
    private ListProperty<Book> books = new SimpleListProperty<>();
    public BasketControl() {
        super();

        var loader = new FXMLLoader(getClass()
                .getResource("../../../resources/fxml/controls/BasketControl.fxml"));
        loader.setController(this);

        try {
            Node node = loader.load();
            this.getChildren().add(node);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        bookCollection.booksProperty().bindBidirectional(booksProperty());

    }

    public void setActionButtonFunction(IBookControlAction actionButtonFunction) {
        bookCollection.setActionButtonFunction(actionButtonFunction);
    }

    public ListProperty<Book> booksProperty() {
        return books;
    }

    public ObservableList<Book> getBooks() {
        return booksProperty().get();
    }
}
