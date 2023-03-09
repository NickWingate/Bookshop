package Entities;

import Enums.Genre;
import Enums.Language;

import java.math.BigDecimal;
import java.util.Date;

public class Book {
    public Book(String barcode,
                String title,
                Language language,
                Genre genre,
                Date releaseDate,
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

    private final String barcode;
    private final String title;
    private final Language language;
    private final Genre genre;
    private final Date releaseDate;
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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
