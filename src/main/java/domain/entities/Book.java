package main.java.domain.entities;

import javafx.beans.property.*;
import main.java.domain.enums.BookType;
import main.java.domain.enums.Genre;
import main.java.domain.enums.IBookProperty;
import main.java.domain.enums.Language;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Book {
    public Book(String barcode,
                String title,
                Language language,
                Genre genre,
                LocalDate releaseDate,
                int quantity,
                double price) {
        this.barcode = new SimpleStringProperty(barcode);
        this.title = new SimpleStringProperty(title);
        this.language = new SimpleObjectProperty<>(language);
        this.genre = new SimpleObjectProperty<>(genre);
        this.releaseDate = new SimpleObjectProperty<>(releaseDate);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.price = new SimpleDoubleProperty(price);
    }
    public Book(){
        barcode = new SimpleStringProperty(this, "barcode");
        quantity = new SimpleIntegerProperty(this, "quantity");
        genre = new SimpleObjectProperty<>(this, "genre");
        releaseDate = new SimpleObjectProperty<>(this, "releaseDate");
        price = new SimpleDoubleProperty(this, "price");
        language = new SimpleObjectProperty<>(this, "language");
        title = new SimpleStringProperty(this, "title");
    }

    private SimpleStringProperty barcode;
    private SimpleStringProperty title;
    private SimpleObjectProperty<Language> language;
    private SimpleObjectProperty<Genre> genre;
    private SimpleObjectProperty<LocalDate> releaseDate;
    private SimpleIntegerProperty quantity;
    private SimpleIntegerProperty quantitySelected = new SimpleIntegerProperty();
    private SimpleDoubleProperty price;

    public String getBarcode() {
        return barcode.get();
    }

    public String getTitle() {
        return title.get();
    }

    public Language getLanguage() {
        return language.get();
    }

    public Genre getGenre() {
        return genre.get();
    }

    public LocalDate getReleaseDate() {
        return releaseDate.get();
    }

    public int getQuantity() {
        return quantity.get();
    }

    public double getPrice() {
        return price.get();
    }

    public void setBarcode(String barcode) {
        this.barcode.set(barcode);
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public void setLanguage(Language language) {
        this.language.set(language);
    }

    public void setGenre(Genre genre) {
        this.genre.set(genre);
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate.set(releaseDate);
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public abstract BookType getBookType();

    public abstract String addionalInfoToString();

    public abstract String lengthToString();

    public List<IBookProperty> getProperties() {
        var props = new ArrayList<IBookProperty>();
        props.add(getLanguage());
        props.add(getGenre());
        props.add(getBookType());

        return props;
    }
    public IntegerProperty quantityProperty(){
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof Book))
            return false;

        var toCompare = (Book) o;

        return getBarcode().equals(toCompare.getBarcode()) &&
                getTitle().equals(toCompare.getTitle()) &&
                getLanguage() == toCompare.getLanguage() &&
                getGenre() == toCompare.getGenre() &&
                getReleaseDate().isEqual(toCompare.getReleaseDate()) &&
                getPrice() == toCompare.getPrice(); // todo: and quantity? no because quantity is seperate from book
    }

    public int getQuantitySelected() {
        return quantitySelected.get();
    }

    public void setQuantitySelected(int quantitySelected) {
        this.quantitySelected.set(quantitySelected);
    }
}
