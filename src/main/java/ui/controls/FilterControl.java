package main.java.ui.controls;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import main.java.domain.entities.Filter;
import main.java.domain.enums.BookType;
import main.java.domain.enums.IBookProperty;
import main.java.domain.enums.Language;
import main.java.ui.common.interfaces.IFilterChangedEvent;

import java.util.ArrayList;
import java.util.List;

public class FilterControl extends VBox {

    @FXML
    private CheckBox englishToggle;

    @FXML
    private CheckBox frenchToggle;

    @FXML
    private CheckBox paperbackToggle;

    @FXML
    private CheckBox audiobookToggle;

    @FXML
    private CheckBox ebookToggle;

    private IFilterChangedEvent filterChangedEvent;

    private List<IBookProperty> selectedProperties = new ArrayList<>();
    public FilterControl() {
        super();

        var loader = new FXMLLoader(getClass()
                .getResource("../../../resources/fxml/controls/FilterControl.fxml"));
        loader.setController(this);

        try {
            Node node = loader.load();
            this.getChildren().add(node);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        setCheckBoxEvents();
    }

    private void setCheckBoxEvents() {
        var toggles = new CheckBox[] { englishToggle, frenchToggle, paperbackToggle, ebookToggle, audiobookToggle };

        for (var toggle : toggles) {
            var eventHandler = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    updateSelectedPropertiesList(); // todo this might cause issues
                    filterChangedEvent.onChange(selectedProperties);
                }
            };

            toggle.setOnAction(eventHandler);
        }
    }

    private void updateSelectedPropertiesList() {
        selectedProperties.clear();
        if (englishToggle.isSelected())
            selectedProperties.add(Language.ENGLISH);
        if (frenchToggle.isSelected())
            selectedProperties.add(Language.FRENCH);
        if (paperbackToggle.isSelected())
            selectedProperties.add(BookType.PAPERBACK);
        if (audiobookToggle.isSelected())
            selectedProperties.add(BookType.AUDIOBOOK);
        if (ebookToggle.isSelected())
            selectedProperties.add(BookType.EBOOK);
    }

    public void setFilterChangedEvent(IFilterChangedEvent event) {
        filterChangedEvent = event;
        setCheckBoxEvents();
    }
}
