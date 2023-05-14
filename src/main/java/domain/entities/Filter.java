package main.java.domain.entities;

import main.java.domain.enums.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Filter {

    private List<BookType> bookTypes;

    private List<Genre> genres;

    private List<Language> languages;

    private List<Condition> conditions;

    private List<EBookFormat> eBookFormats;

    private List<AudioFormat> audioFormats;


    public Filter() {
        bookTypes = new ArrayList<>();
        genres = new ArrayList<>();
        languages = new ArrayList<>();
        conditions = new ArrayList<>();
        eBookFormats = new ArrayList<>();
        audioFormats = new ArrayList<>();
    }

    public void addFilter(IBookProperty filter) {
        var enumType = filter.getClass();

        if (enumType == BookType.class)
            addFilter((BookType) filter);
        if (enumType == Genre.class)
            addFilter((Genre) filter);
        if (enumType == Language.class)
            addFilter((Language) filter);
        if (enumType == Condition.class)
            addFilter((Condition) filter);
        if (enumType == EBookFormat.class)
            addFilter((EBookFormat) filter);
        if (enumType == AudioFormat.class)
            addFilter((AudioFormat) filter);

    }
    public void addFilter(BookType filter) {
        bookTypes.add(filter);
    }

    public void addFilter(Genre filter) {
        genres.add(filter);
    }

    public void addFilter(Language filter) {
        languages.add(filter);
    }

    public void addFilter(Condition filter) {
        conditions.add(filter);
    }

    public void addFilter(EBookFormat filter) {
        eBookFormats.add(filter);
    }

    public void addFilter(AudioFormat filter) {
        audioFormats.add(filter);
    }

    public void removeFilter(BookType filter) {
        bookTypes.remove(filter);
    }

    public void removeFilter(Genre filter) {
        genres.remove(filter);
    }

    public void removeFilter(Language filter) {
        languages.remove(filter);
    }

    public void removeFilter(Condition filter) {
        conditions.remove(filter);
    }

    public void removeFilter(EBookFormat filter) {
        eBookFormats.remove(filter);
    }

    public void removeFilter(AudioFormat filter) {
        audioFormats.remove(filter);
    }

    public void clear() {
        bookTypes.clear();
        genres.clear();
        languages.clear();
        conditions.clear();
        eBookFormats.clear();
    }

    public boolean isEmpty() {
        return bookTypes.isEmpty() &&
                genres.isEmpty() &&
                languages.isEmpty() &&
                conditions.isEmpty() &&
                eBookFormats.isEmpty();
    }

    public boolean meetsConditions(Book book) {

        switch (book.getBookType()) {
            case PAPERBACK:
                if (!conditions.isEmpty() && !conditions.contains(((PaperbackBook) book).getCondition())) {
                    bookTypes.add(BookType.PAPERBACK);
                    return false;
                }
                break;

            case AUDIOBOOK:
                if (!audioFormats.isEmpty() && !audioFormats.contains(((AudioBook) book).getFormat())) {
                    bookTypes.add(BookType.AUDIOBOOK);
                    return false;
                }
                break;

            case EBOOK:
                if (!eBookFormats.isEmpty() && !eBookFormats.contains(((EBook) book).getFormat())) {
                    bookTypes.add(BookType.EBOOK);
                    return false;
                }
                break;

        }

        if (!bookTypes.isEmpty() && !bookTypes.contains(book.getBookType()))
            return false;

        if (!genres.isEmpty() && !genres.contains(book.getGenre()))
            return false;

        if (!languages.isEmpty() && !languages.contains(book.getLanguage()))
            return false;

        return true;
    }
}
