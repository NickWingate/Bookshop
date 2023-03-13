package Entities;

import Enums.BookType;
import Enums.Genre;
import Enums.Language;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public abstract class Book {
    public Book(String barcode,
                String title,
                Language language,
                Genre genre,
                LocalDate releaseDate,
                int quantity,
                BigDecimal price) {
        this.barcode = barcode;
        this.title = title;
        this.language = language;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.quantity = quantity;
        this.price = price;
    }
    public Book(){

    }

    private String barcode;
    private String title;
    private Language language;
    private Genre genre;
    private LocalDate releaseDate;
    private int quantity;
    private BigDecimal price;

    public String getBarcode() {
        return barcode;
    }

    public String getTitle() {
        return title;
    }

    public Language getLanguage() {
        return language;
    }

    public Genre getGenre() {
        return genre;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public abstract BookType getBookType();
}
