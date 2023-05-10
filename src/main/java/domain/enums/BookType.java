package main.java.domain.enums;

public enum BookType implements IBookProperty {
    PAPERBACK,
    AUDIOBOOK,
    EBOOK;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
