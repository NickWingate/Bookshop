package main.java.ui.controls;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;
import main.java.domain.entities.Filter;
import main.java.domain.enums.*;
import main.java.ui.common.interfaces.IFilterChangedEvent;

import java.util.Map;
import java.util.regex.Pattern;

public class FilterControl extends VBox {

    @FXML
    private TreeView<String> mainTreeView;

    @FXML
    private Button filterBtn;

    @FXML
    private CheckBoxTreeItem<String> englishToggle;

    @FXML
    private CheckBoxTreeItem<String> frenchToggle;

    @FXML
    private CheckBoxTreeItem<String> paperbackToggle, audiobookToggle, ebookToggle;

    @FXML
    private CheckBoxTreeItem<String> newToggle, usedToggle, epubToggle, mobiToggle, pdfToggle, mp3Toggle, wmaToggle, aacToggle;

    @FXML
    private CheckBoxTreeItem<String> politicsToggle, businessToggle, computerScienceToggle, biographyToggle;

    @FXML
    private Slider durationSlider;

    @FXML
    private TextField durationField;

    private float maxDuration;

    private Map<CheckBoxTreeItem<String>, IBookProperty> togglePropertyMap;

    private IFilterChangedEvent filterChangedEvent;

    private final Filter filterParameters = new Filter();
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

        setTreeCellFactory();
        configureSlider();

        initToggleMap();
        filterBtn.setOnAction((e) -> {
            updateSelectedPropertiesList();
            filterChangedEvent.onChange(filterParameters);
        });
        //setCheckBoxEvents();
    }

    private void configureSlider() {
        setMaxDuration(10);
        Bindings.bindBidirectional(durationField.textProperty(),
                durationSlider.valueProperty(),
                new NumberStringConverter("0.0"));
        var durationPattern = Pattern.compile("^\\d*\\.?(\\d)?");
        var durationFormatter = new TextFormatter<>(change -> {
            var newText = change.getControlNewText();
            if (!durationPattern.matcher(newText).matches()) {
                return null;
            }
            if (newText.isEmpty()) {
                return change;
            }
            if (Float.parseFloat(change.getControlNewText()) > maxDuration) {
                return null;
            }
            return change;
        });
        durationField.setTextFormatter(durationFormatter);
    }

    public void setMaxDuration(float maxDuration) {
        if (maxDuration == 0f) {
            durationSlider.setDisable(true);
            durationField.setDisable(true);
        }
        this.maxDuration = maxDuration;
        durationSlider.setMax(maxDuration);
        durationSlider.setBlockIncrement(maxDuration);
    }

    private void setTreeCellFactory() {
        mainTreeView.setCellFactory(treeView -> new CheckBoxTreeCell<String>() {
            @Override
            public void updateItem(String s, boolean b) {
                super.updateItem(s, b);
                var item = getTreeItem();
                if (item instanceof CheckBoxTreeItem) {
                    setGraphic(getGraphic());
                } else {
                    setItem(s);
                    setGraphic(null);
                }
            }
        });
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
                Map.entry(aacToggle, AudioFormat.AAC),
                Map.entry(politicsToggle, Genre.POLITICS),
                Map.entry(businessToggle, Genre.BUSINESS),
                Map.entry(computerScienceToggle, Genre.COMPUTER_SCIENCE),
                Map.entry(biographyToggle, Genre.BIOGRAPHY));
    }

    private void setCheckBoxEvents() {

        for (var toggle : togglePropertyMap.keySet()) {
            toggle.addEventHandler(CheckBoxTreeItem.checkBoxSelectionChangedEvent(), (c) -> {
                updateSelectedPropertiesList(); // todo this might cause issues
                filterChangedEvent.onChange(filterParameters);
            });
        }
    }


    private void updateSelectedPropertiesList() {
        filterParameters.clear();
        for (var kvp : togglePropertyMap.entrySet()) {
            if (kvp.getKey().isSelected() || kvp.getKey().isIndeterminate()) {
                filterParameters.addFilter(kvp.getValue());
            }
        }
        filterParameters.setMinDuration((float) durationSlider.getValue());
    }

    public void setFilterChangedEvent(IFilterChangedEvent event) {
        filterChangedEvent = event;
        //setCheckBoxEvents();
    }
}
