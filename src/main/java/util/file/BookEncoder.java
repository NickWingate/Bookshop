package main.java.util.file;

import main.java.domain.entities.AudioBook;
import main.java.domain.entities.Book;
import main.java.domain.entities.EBook;
import main.java.domain.entities.PaperbackBook;
import main.java.util.interfaces.IBookEncoder;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class BookEncoder implements IBookEncoder {

    @Override
    public String EncodeObject(Book object) {
        //barcode, book type, title, language, genre, release
        //date, quantity in stock, retail price, additional information 1, additional information 2
        var string = object.getBarcode() + ", " +
                object.getBookType().toString() +", " +
                object.getTitle() +", " +
                object.getLanguage().toString() +", " +
                object.getGenre().toString() +", " +
                object.getReleaseDate()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +", " +
                object.getQuantity() +", " +
                object.getPrice();

        switch (object.getBookType()) {
            case PAPERBACK -> {
                string += ", " + ((PaperbackBook) object).getNumberOfPages()
                        + ", " + ((PaperbackBook) object).getCondition();
            }
            case AUDIOBOOK -> {
                string += ", " + ((AudioBook) object).getDuration()
                        + ", " + ((AudioBook) object).getFormat();
            }
            case EBOOK -> {
                string += ", " + ((EBook) object).getNumberOfPages()
                        + ", " + ((EBook) object).getFormat();
            }
        }

        return string + '\n';
    }
}
