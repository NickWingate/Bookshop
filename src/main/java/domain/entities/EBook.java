package main.java.domain.entities;

import main.java.domain.enums.*;

import java.time.LocalDate;
import java.util.List;

public class EBook extends Book {

    public EBook(String barcode,
                 String title,
                 Language language,
                 Genre genre,
                 LocalDate releaseDate,
                 int quantity,
                 double price,
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

    @Override
    public String addionalInfoToString() {
        return "EBook - " + getFormat().toString();
    }

    @Override
    public String lengthToString() {
        return getNumberOfPages() + " Pages";
    }

    @Override
    public List<IBookProperty> getProperties() {
        var props = super.getProperties();
        props.add(getFormat());

        return props;
    }
}
