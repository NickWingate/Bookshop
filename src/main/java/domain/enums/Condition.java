package main.java.domain.enums;

public enum Condition implements IBookProperty {
    NEW,
    USED;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
