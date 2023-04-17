package main.java.domain.entities;


import main.java.domain.enums.BookType;
import main.java.domain.enums.Condition;
import main.java.domain.enums.Genre;
import main.java.domain.enums.Language;

import java.time.LocalDate;

public class PaperbackBook extends Book{
    public PaperbackBook(String barcode,
                         String title,
                         Language language,
                         Genre genre,
                         LocalDate releaseDate,
                         int quantity,
                         double price,
                         int numberOfPages,
                         Condition condition) {
        super(barcode, title, language, genre, releaseDate, quantity, price);
        this.numberOfPages = numberOfPages;
        this.condition = condition;
    }

    public PaperbackBook(){

    }

    private int numberOfPages;
    private Condition condition;

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    @Override
    public BookType getBookType() {
        return BookType.PAPERBACK;
    }

    @Override
    public String addionalInfoToString() {
        return "Paperback - " + getCondition().toString();
    }

    @Override
    public String lengthToString() {
        return getNumberOfPages() + " Pages";
    }
}
