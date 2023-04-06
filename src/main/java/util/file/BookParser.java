package main.java.util.file;

import main.java.domain.entities.AudioBook;
import main.java.domain.entities.Book;
import main.java.domain.entities.EBook;
import main.java.domain.entities.PaperbackBook;
import main.java.domain.enums.*;
import main.java.util.interfaces.IBookParser;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BookParser implements IBookParser {
    @Override
    public List<Book> ParseFile(String fileName) {
        return null;
    }

    @Override
    public Book ParseLine(String line) {
        var rawValues = line.split(",");
        var values = new String[rawValues.length];
        for (int i = 0; i < values.length; i++) {
            values[i] = rawValues[i].trim();
        }
        //barcode, book type, title, language, genre, release date, quantity in stock, retail price, additional information 1, additional information 2
        var booktype = BookType.valueOf(values[1].toUpperCase());

        // todo: book.setBarcode() complains that book may not be initialized
        Book book = null;
        switch (booktype) {
            case PAPERBACK -> book = new PaperbackBook();
            case AUDIOBOOK -> book = new AudioBook();
            case EBOOK -> book = new EBook();
        }

        book.setBarcode(values[0]);
        book.setTitle(values[2]);
        book.setLanguage(Language.valueOf(values[3].toUpperCase()));
        book.setGenre(Genre.valueOf(values[4].toUpperCase()));
        book.setReleaseDate(LocalDate.parse(values[5],
                DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        book.setQuantity(Integer.valueOf(values[6]));
        book.setPrice(new BigDecimal(values[7]));
        var addInfo1 = values[8];
        var addInfo2 = values[9];

        switch (booktype) {
            case PAPERBACK -> {
                ((PaperbackBook) book).setNumberOfPages(Integer.valueOf(addInfo1));
                ((PaperbackBook) book).setCondition(Condition.valueOf(addInfo2.toUpperCase()));
            }
            case AUDIOBOOK -> {
                var timeData = addInfo1.split("\\.");
                var duration = Duration.between(
                        LocalTime.MIN,
                        LocalTime.of(
                                Integer.parseInt(timeData[0]),
                                Integer.parseInt(timeData[1])
                        ));
                ((AudioBook) book).setDuration(duration);
                ((AudioBook) book).setFormat(AudioFormat.valueOf(addInfo2.toUpperCase()));
            }
            case EBOOK -> {
                ((EBook) book).setNumberOfPages(Integer.valueOf(addInfo1));
                ((EBook) book).setFormat(EBookFormat.valueOf(addInfo2.toUpperCase()));
            }
        }

        return book;
    }
}
