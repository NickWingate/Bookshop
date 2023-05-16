package main.java.ui.controls;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import main.java.domain.entities.AudioBook;
import main.java.domain.entities.Book;
import main.java.domain.entities.EBook;
import main.java.domain.entities.PaperbackBook;
import main.java.domain.enums.*;
import main.java.ui.common.interfaces.IAddBookAction;
import main.java.ui.common.interfaces.IValidateBookAction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class AddBookControl extends GridPane {
    @FXML
    private TextField barcodeField;

    @FXML
    private TextField titleField;

    @FXML
    private ChoiceBox<Language> languageField;

    @FXML
    private ChoiceBox<Genre> genreField;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextField quantityField;

    @FXML
    private TextField priceField;

    @FXML
    private ChoiceBox<BookType> typeField;

    @FXML
    private ChoiceBox<Condition> conditionField;

    @FXML
    private ChoiceBox<EBookFormat> ebookFormatField;

    @FXML
    private ChoiceBox<AudioFormat> audiobookFormatField;

    @FXML
    private Label additionalInfoLbl;

    @FXML
    private Label lengthLbl;

    @FXML
    private TextField pageCountField;

    @FXML
    private TextField durationField;

    @FXML
    private Button addBookBtn;

    @FXML
    private Label validationErrors;

    private IAddBookAction addBookAction;

    private IValidateBookAction validateBookAction;

    public AddBookControl() {
        super();

        var loader = new FXMLLoader(getClass()
                .getResource("../../../resources/fxml/controls/AddBookControl.fxml"));
        loader.setController(this);

        try {
            Node node = loader.load();
            this.getChildren().add(node);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        initializeChoiceBoxes();
        setTextFormatters();

        addBookBtn.setOnAction((e) -> addBook());
        resetFields();
    }

    private void setTextFormatters() {
        // regex:
        // ^\d* - start with any amount of digits before decimal (non-negative)
        // \.? - optional decimal point
        // (\d{1,2})? - optional 1 or 2 digits after decimal
        var moneyPattern = Pattern.compile("^\\d*\\.?(\\d{1,2})?");
        var durationPattern = Pattern.compile("^\\d*\\.?(\\d)?");
        var intPattern = Pattern.compile("^\\d*");

        var moneyFormatter = new TextFormatter<>(change ->
                moneyPattern.matcher(change.getControlNewText()).matches() ? change : null);

        var durationFormatter = new TextFormatter<>(change ->
                durationPattern.matcher(change.getControlNewText()).matches() ? change : null);

        var intFormatter = new TextFormatter<>(change ->
                intPattern.matcher(change.getControlNewText()).matches() ? change : null);

        priceField.setTextFormatter(moneyFormatter);
        durationField.setTextFormatter(durationFormatter);
        pageCountField.setTextFormatter(intFormatter);

        quantityField.setTextFormatter(new TextFormatter<Object>(intFormatter.getFilter()));
    }

    private void addBook() {
        validationErrors.setText("");

        var blankFields = validateBlankFields();
        if (!blankFields.isEmpty()) {
            validationErrors.setText(String.join(", ", blankFields));
            return;
        }

        Book book = null;
        switch (typeField.getValue()){
            case AUDIOBOOK:
                var audioBook = new AudioBook();
                audioBook.setFormat(audiobookFormatField.getValue());
                audioBook.setDuration(Float.valueOf(durationField.getText()));
                book = audioBook;
                break;
            case EBOOK:
                var eBook = new EBook();
                eBook.setFormat(ebookFormatField.getValue());
                eBook.setNumberOfPages(Integer.valueOf(pageCountField.getText()));
                book = eBook;
                break;
            case PAPERBACK:
                var paperbackBook = new PaperbackBook();
                paperbackBook.setCondition(conditionField.getValue());
                paperbackBook.setNumberOfPages(Integer.valueOf(pageCountField.getText()));
                book = paperbackBook;
                break;
        }

        book.setQuantity(Integer.valueOf(quantityField.getText()));
        book.setBarcode(barcodeField.getText());
        book.setTitle(titleField.getText());
        book.setLanguage(languageField.getValue());
        book.setGenre(genreField.getValue());
        book.setPrice(Double.valueOf(priceField.getText()));
        book.setReleaseDate(dateField.getValue());

        var validationProblems = validateBookAction.validate(book);

        if (!validationProblems.isEmpty()) {
            validationErrors.setText(String.join(", ", validationProblems));
            return;
        }

        addBookAction.Add(book);
        validationErrors.setText("");
        resetFields();
    }

    private List<String> validateBlankFields() {
        var problems = new ArrayList<String>();

        var fieldsMap = Map.of(
                barcodeField, "Barcode",
                titleField, "Title",
                priceField, "Price",
                quantityField, "Quantity");

        for (var kvp : fieldsMap.entrySet()) {
            if (kvp.getKey().getText().isBlank()) {
                problems.add(String.format("%s is blank", kvp.getValue()));
            }
        }
        if (pageCountField.getText().isBlank() && durationField.getText().isBlank()) {
            problems.add("Length is blank");
        }

        return problems;
    }

    private void resetFields() {
        barcodeField.clear();
        titleField.clear();
        languageField.setValue(Language.ENGLISH);
        genreField.setValue(Genre.POLITICS);
        dateField.setValue(LocalDate.now());
        priceField.clear();
        typeField.setValue(BookType.PAPERBACK);
        conditionField.setValue(Condition.NEW);
        ebookFormatField.setValue(EBookFormat.EPUB);
        audiobookFormatField.setValue(AudioFormat.MP3);

        updateShownField(BookType.PAPERBACK);

    }

    private void initializeChoiceBoxes() {
        typeField.getItems().setAll(BookType.values());
        typeField.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> updateShownField(newValue));

        conditionField.getItems().setAll(Condition.values());
        ebookFormatField.getItems().setAll(EBookFormat.values());
        audiobookFormatField.getItems().setAll(AudioFormat.values());
        languageField.getItems().setAll(Language.values());
        genreField.getItems().setAll(Genre.values());

    }

    private void updateShownField(BookType value) {
        additionalInfoLbl.setText("");
        conditionField.setVisible(false);
        ebookFormatField.setVisible(false);
        audiobookFormatField.setVisible(false);
        lengthLbl.setVisible(true);
        pageCountField.setVisible(false);
        durationField.setVisible(false);
        durationField.clear();
        pageCountField.clear();

        switch (value){
            case PAPERBACK:
                additionalInfoLbl.setText("Condition:");
                conditionField.setVisible(true);
                pageCountField.setVisible(true);
                break;
            case EBOOK:
                additionalInfoLbl.setText("Format");
                ebookFormatField.setVisible(true);
                pageCountField.setVisible(true);
                break;
            case AUDIOBOOK:
                additionalInfoLbl.setText("Format");
                audiobookFormatField.setVisible(true);
                durationField.setVisible(true);
                break;
        }
    }

    public void setAddBookAction(IAddBookAction addBookAction) {
        this.addBookAction = addBookAction;
    }

    public void setValidateBookAction(IValidateBookAction validateBookAction) {
        this.validateBookAction = validateBookAction;
    }
}
