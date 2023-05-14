package main.java.ui.controls;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import main.java.domain.entities.Filter;
import main.java.domain.enums.*;
import main.java.ui.common.interfaces.IFilterChangedEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @FXML
    private CheckBox newToggle, usedToggle, epubToggle, mobiToggle, pdfToggle, mp3Toggle, wmaToggle, aacToggle;

    private Map<CheckBox, IBookProperty> togglePropertyMap;


    private IFilterChangedEvent filterChangedEvent;

    private Filter selectedProperties = new Filter();
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

        initToggleMap();
        setCheckBoxEvents();
    }

    private void initToggleMap() {
        togglePropertyMap = Map.ofEntries(
                Map.entry(englishToggle, Language.ENGLISH),
                Map.entry(frenchToggle, Language.FRENCH),
                Map.entry(paperbackToggle, BookType.PAPERBACK),
                Map.entry(ebookToggle, BookType.EBOOK),
                Map.entry(audiobookToggle, BookType.AUDIOBOOK),
                Map.entry(newToggle, Condition.NEW),
                Map.entry(usedToggle, Condition.USED),
                Map.entry(epubToggle, EBookFormat.EPUB),
                Map.entry(mobiToggle, EBookFormat.MOBI),
                Map.entry(pdfToggle, EBookFormat.PDF),
                Map.entry(mp3Toggle, AudioFormat.MP3),
                Map.entry(wmaToggle, AudioFormat.WMA),
                Map.entry(aacToggle, AudioFormat.AAC))
        ;
    }

    private void setCheckBoxEvents() {

        for (var toggle : togglePropertyMap.keySet()) {
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
        for (var kvp : togglePropertyMap.entrySet()) {
            if (kvp.getKey().isSelected()) {
                selectedProperties.addFilter(kvp.getValue());
            }
        }
//        if (englishToggle.isSelected())
//            selectedProperties.add(Language.ENGLISH);
//        if (frenchToggle.isSelected())
//            selectedProperties.add(Language.FRENCH);
//        if (paperbackToggle.isSelected())
//            selectedProperties.add(BookType.PAPERBACK);
//        if (audiobookToggle.isSelected())
//            selectedProperties.add(BookType.AUDIOBOOK);
//        if (ebookToggle.isSelected())
//            selectedProperties.add(BookType.EBOOK);
    }

    public void setFilterChangedEvent(IFilterChangedEvent event) {
        filterChangedEvent = event;
        setCheckBoxEvents();
    }
}
