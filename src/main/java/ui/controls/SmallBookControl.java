package main.java.ui.controls;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.SpinnerValueFactory;
import main.java.domain.entities.Book;
import main.java.ui.common.interfaces.BookControlBase;

public class SmallBookControl extends BookControlBase {

    public SmallBookControl(Book book) {
        super(book);
    }
    @Override
    protected String getFXMLPath() {
        return "../../../resources/fxml/controls/SmallBookControl.fxml";
    }
}
