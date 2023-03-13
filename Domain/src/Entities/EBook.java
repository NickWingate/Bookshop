package Entities;

import Enums.BookType;
import Enums.EBookFormat;
import Enums.Genre;
import Enums.Language;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class EBook extends Book {

    public EBook(String barcode,
                 String title,
                 Language language,
                 Genre genre,
                 LocalDate releaseDate,
                 int quantity,
                 BigDecimal price,
                 int numberOfPages,
                 EBookFormat format) {
        super(barcode, title, language, genre, releaseDate, quantity, price);
        this.numberOfPages = numberOfPages;
        this.format = format;
    }

    public EBook(){

    }

    private int numberOfPages;
    private EBookFormat format;

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public EBookFormat getFormat() {
        return format;
    }

    public void setFormat(EBookFormat format) {
        this.format = format;
    }

    @Override
    public BookType getBookType() {
        return BookType.EBOOK;
    }
}
