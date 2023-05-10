package main.java.domain.entities;

import main.java.domain.enums.*;

import java.time.LocalDate;
import java.util.List;

public class AudioBook extends Book{

    public AudioBook(String barcode,
                     String title,
                     Language language,
                     Genre genre,
                     LocalDate releaseDate,
                     int quantity,
                     double price,
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

    @Override
    public List<IBookProperty> getProperties() {
        var props = super.getProperties();
        props.add(getFormat());

        return props;
    }
}
