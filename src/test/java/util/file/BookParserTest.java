package test.java.util.file;

import main.java.domain.entities.AudioBook;
import main.java.domain.entities.EBook;
import main.java.domain.entities.PaperbackBook;
import main.java.domain.enums.*;
import main.java.util.file.BookParser;
import main.java.util.interfaces.IBookParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class BookParserTest {

    IBookParser _sut;

    @BeforeEach
    void setUp() {
        _sut = new BookParser();
    }

    @Test
    void parseLine_shouldReturnPaperbackBook() {
        // Arrange
        var line = "11223344, paperback, A Promised Land, English, Biography, 17-11-2020, 5, 17.5, 768, new\n";
        var expected = new PaperbackBook(
                "11223344",
                "A Promised Land",
                Language.ENGLISH,
                Genre.BIOGRAPHY,
                LocalDate.of(2020, 11, 17),
                5,
                17.5,
                768,
                Condition.NEW);
        // Act
        var actual = _sut.ParseLine(line);

        // Assert
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void parseLine_shouldReturnAudioBook() {
        // Arrange
        var line = "11224455, audiobook, A Promised Land, English, Biography, 24-11-2020, 10, 30.25, 4.5, MP3\n";
        var expected = new AudioBook(
                "11224455",
                "A Promised Land",
                Language.ENGLISH,
                Genre.BIOGRAPHY,
                LocalDate.of(2020, 11, 24),
                10,
                30.25,
               4.5f,
                AudioFormat.MP3);
        // Act
        var actual = _sut.ParseLine(line);

        // Assert
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);

    }

    @Test
    void parseLine_shouldReturnEBook() {
        // Arrange
        var line = "22446688, ebook, Big Money Energy, French, Business, 21-01-2021, 12, 10.99, 182, PDF\n";
        var expected = new EBook(
                "22446688",
                "Big Money Energy",
                Language.FRENCH,
                Genre.BUSINESS,
                LocalDate.of(2021, 01, 21),
                12,
                10.99,
                182,
                EBookFormat.PDF);
        // Act
        var actual = _sut.ParseLine(line);

        // Assert
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}