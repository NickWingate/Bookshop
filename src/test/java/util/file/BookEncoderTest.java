package test.java.util.file;

import main.java.domain.entities.AudioBook;
import main.java.domain.entities.EBook;
import main.java.domain.entities.PaperbackBook;
import main.java.domain.enums.*;
import main.java.util.file.BookEncoder;
import main.java.util.file.BookParser;
import main.java.util.interfaces.IBookEncoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class BookEncoderTest {

    private IBookEncoder _sut;

    @BeforeEach
    void setUp() {
        _sut = new BookEncoder();
    }
    @Test
    void encodeObject_shouldEncodePaperback() {
        // Arrange
        var expected = "11223344, paperback, A Promised Land, English, Biography, 17-11-2020, 5, 17.5, 768, new\n";
        var book = new PaperbackBook(
                "11223344",
                "A Promised Land",
                Language.ENGLISH,
                Genre.BIOGRAPHY,
                LocalDate.of(2020, 11, 17),
                5,
                new BigDecimal("17.5"),
                768,
                Condition.NEW);

        // Act
        var actual = _sut.EncodeObject(book);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void encodeObject_shouldEncodeAudioBook() {
        // Arrange
        var expected = "11224455, audiobook, A Promised Land, English, Biography, 24-11-2020, 10, 30.25, 4.5, MP3\n";
        var book = new AudioBook(
                "11224455",
                "A Promised Land",
                Language.ENGLISH,
                Genre.BIOGRAPHY,
                LocalDate.of(2020, 11, 24),
                10,
                new BigDecimal("30.25"),
                4.5f,
                AudioFormat.MP3);

        // Act
        var actual = _sut.EncodeObject(book);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void encodeObject_shouldEncodeEbook() {
        // Arrange
        var book = new EBook("22446688",
                "Big Money Energy",
                Language.FRENCH,
                Genre.BUSINESS,
                LocalDate.of(2021, 01, 21),
                12,
                new BigDecimal("10.99"),
                182,
                EBookFormat.PDF);
        var expected = "22446688, ebook, Big Money Energy, French, Business, 21-01-2021, 12, 10.99, 182, PDF\n";

        // Act
        var actual = _sut.EncodeObject(book);
        // Assert
        assertEquals(expected, actual);
    }
}