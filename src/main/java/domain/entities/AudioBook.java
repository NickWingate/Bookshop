package main.java.domain.entities;

import main.java.domain.enums.AudioFormat;
import main.java.domain.enums.BookType;
import main.java.domain.enums.Genre;
import main.java.domain.enums.Language;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;

public class AudioBook extends Book{

    public AudioBook(String barcode,
                     String title,
                     Language language,
                     Genre genre,
                     LocalDate releaseDate,
                     int quantity,
                     BigDecimal price,
                     float duration,
                     AudioFormat format) {
        super(barcode, title, language, genre, releaseDate, quantity, price);
        this.duration = duration;
        this.format = format;
    }

    public AudioBook(){

    }

    private float duration;
    private AudioFormat format;

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
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

    @Override
    public String addionalInfoToString() {
        return "Audio Book - " + getFormat();
    }

    @Override
    public String lengthToString() {
        return getDuration() + " Hours";
    }
}
