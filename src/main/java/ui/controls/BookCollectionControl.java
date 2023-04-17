package main.java.ui.controls;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import main.java.domain.entities.Book;
import main.java.domain.entities.EBook;
import main.java.domain.entities.PaperbackBook;
import main.java.domain.enums.BookType;
import main.java.domain.enums.Condition;
import main.java.domain.enums.Genre;
import main.java.domain.enums.Language;
import main.java.util.file.BookEncoder;
import main.java.util.file.BookParser;
import main.java.util.file.CSVWriter;
import main.java.util.repositories.BookRepository;

import java.time.LocalDate;

public class BookCollectionControl extends GridPane {

    @FXML
    private VBox bookBox;
    private ListProperty<Book> books = new SimpleListProperty<>();


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

        books.addListener((Change<? extends Book> change) -> rebuildBooks());
    }

    public ListProperty<Book> booksProperty() {
        return books;
    }

    public ObservableList<Book> getBooks() {
        return booksProperty().get();
    }

    private void rebuildBooks() {
        bookBox.getChildren().clear();
        for (var book : books) {
            bookBox.getChildren().add(new BookControl(book));
        }
    }
}
