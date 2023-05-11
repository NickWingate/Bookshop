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

import java.awt.*;
import java.time.LocalDate;

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
    private Spinner quantityField;

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
    private TextField lengthField;

    @FXML
    private Button addBookBtn;

    private IAddBookAction addBookAction;

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

        addBookBtn.setOnAction((e) -> addBook());
        quantityField.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(
                0, Integer.MAX_VALUE, 0));
        resetFields();
    }

    private void addBook() {
        Book book = null;
        switch (typeField.getValue()){
            case AUDIOBOOK:
                var audioBook = new AudioBook();
                audioBook.setFormat(audiobookFormatField.getValue());
                audioBook.setDuration(Float.valueOf(lengthField.getText()));
                book = audioBook;
                break;
            case EBOOK:
                var eBook = new EBook();
                eBook.setFormat(ebookFormatField.getValue());
                eBook.setNumberOfPages(Integer.valueOf(lengthField.getText()));
                book = eBook;
                break;
            case PAPERBACK:
                var paperbackBook = new PaperbackBook();
                paperbackBook.setCondition(conditionField.getValue());
                paperbackBook.setNumberOfPages(Integer.valueOf(lengthField.getText()));
                book = paperbackBook;
                break;
        }

        book.setQuantity((Integer) quantityField.getValue());
        book.setBarcode(barcodeField.getText());
        book.setTitle(titleField.getText());
        book.setLanguage(languageField.getValue());
        book.setGenre(genreField.getValue());
        book.setPrice(Double.valueOf(priceField.getText()));
        book.setReleaseDate(dateField.getValue());

        addBookAction.Add(book);
        resetFields();
    }

    private void resetFields() {
        barcodeField.clear();
        titleField.clear();
        languageField.setValue(Language.ENGLISH);
        genreField.setValue(Genre.POLITICS);
        dateField.setValue(LocalDate.now());
        quantityField.getValueFactory().setValue(0);
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
        lengthField.clear();
        lengthField.setVisible(true);

        switch (value){
            case PAPERBACK:
                additionalInfoLbl.setText("Condition:");
                conditionField.setVisible(true);
                break;
            case EBOOK:
                additionalInfoLbl.setText("Format");
                ebookFormatField.setVisible(true);
                break;
            case AUDIOBOOK:
                additionalInfoLbl.setText("Format");
                audiobookFormatField.setVisible(true);
                break;
        }
    }

    public void setAddBookAction(IAddBookAction addBookAction) {
        this.addBookAction = addBookAction;
    }
}
