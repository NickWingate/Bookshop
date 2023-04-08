package main.java.domain.enums;

public enum BookType {
    PAPERBACK,
    AUDIOBOOK,
    EBOOK;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
