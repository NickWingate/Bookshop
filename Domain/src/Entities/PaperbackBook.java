package Entities;

import Enums.BookType;
import Enums.Condition;
import Enums.Genre;
import Enums.Language;

import java.math.BigDecimal;
import java.util.Date;

public class PaperbackBook extends Book{
    public PaperbackBook(String barcode,
                         String title,
                         Language language,
                         Genre genre,
                         Date releaseDate,
                         int quantity,
                         BigDecimal price,
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
}
