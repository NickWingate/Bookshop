package main.java.ui.controls;

import javafx.beans.property.*;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import main.java.domain.entities.Book;
import main.java.ui.common.interfaces.IBookControlAction;
import main.java.ui.common.interfaces.BookControlBase;

import java.util.ArrayList;

public class BookCollectionControl extends GridPane {

    @FXML
    private VBox bookBox;
    private ListProperty<Book> books = new SimpleListProperty<>();

    private StringProperty actionButtonText = new SimpleStringProperty();

    private StringProperty bookControlType = new SimpleStringProperty();

    private ArrayList<BookControlBase> bookControls = new ArrayList<>();

    private IBookControlAction actionButtonFunction;


    public BookCollectionControl() {
        super();

        var loader = new FXMLLoader(getClass()
                .getResource("../../../resources/fxml/controls/BookCollectionControl.fxml"));
        loader.setController(this);

        try {
            Node node = loader.load();
            this.getChildren().add(node);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        books.addListener((Change<? extends Book> change) -> rebuildBooks(change));
    }

    public ListProperty<Book> booksProperty() {
        return books;
    }

    public ObservableList<Book> getBooks() {
        return booksProperty().get();
    }

    private void rebuildBooks(Change change) {
        bookBox.getChildren().clear();
        for (var book : books) {
            var bookControl = getBookControl(book);
            bookBox.getChildren().add(bookControl);
        }
    }

    private void forceRebuildBooks(){
        bookControls.clear();
        rebuildBooks(null);
    }

    private BookControlBase getBookControl(Book book) {
        for (var bookControl : bookControls) {
            if (bookControl.equalBook(book)){
                return bookControl;
            }
        }

        var bookControl = NewBookControl(book);
        bookControl.setButtonText(getActionButtonText());

        if (actionButtonFunction != null){
            bookControl.setActionButtonEvent(actionButtonFunction);
        }

        bookControls.add(bookControl);

        return bookControl;
    }

    private BookControlBase NewBookControl(Book book) {
        switch (getBookControlType()){
            case "Small":
                return new SmallBookControl(book);
            case "Normal":
            default:
                return new BookControl(book);
        }
    }

    public void setActionButtonFunction(IBookControlAction actionButtonFunction) {
        this.actionButtonFunction = actionButtonFunction;
        forceRebuildBooks();
    }

    public StringProperty actionButtonTextProperty() {
        return actionButtonText;
    }

    public final void setActionButtonText(String string) {
        actionButtonTextProperty().set(string);
    }

    public final String getActionButtonText() {
        return actionButtonTextProperty().get();
    }

    public StringProperty bookControlTypeProperty() {
        return bookControlType;
    }

    public final void setBookControlType(String string) {
        bookControlTypeProperty().set(string);
    }

    public final String getBookControlType() {
        var s = bookControlTypeProperty().get();
        return s == null ? "" : s;
    }
}
