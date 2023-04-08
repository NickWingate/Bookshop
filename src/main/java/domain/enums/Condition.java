package main.java.domain.enums;

public enum Condition {
    NEW,
    USED;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
