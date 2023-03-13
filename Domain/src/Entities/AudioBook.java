package Entities;

import Enums.AudioFormat;
import Enums.BookType;
import Enums.Genre;
import Enums.Language;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;

public class AudioBook extends Book{

    public AudioBook(String barcode,
                     String title,
                     Language language,
                     Genre genre,
                     LocalDate releaseDate,
                     int quantity,
                     BigDecimal price,
                     Duration duration,
                     AudioFormat format) {
        super(barcode, title, language, genre, releaseDate, quantity, price);
        this.duration = duration;
        this.format = format;
    }

    public AudioBook(){

    }

    private Duration duration;
    private AudioFormat format;

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public AudioFormat getFormat() {
        return format;
    }

    public void setFormat(AudioFormat format) {
        this.format = format;
    }

    @Override
    public BookType getBookType() {
        return BookType.AUDIOBOOK;
    }
}
