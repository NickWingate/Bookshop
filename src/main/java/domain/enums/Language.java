package main.java.domain.enums;

public enum Language {
    ENGLISH("English"),
    FRENCH("French");

    private String userFriendlyName;
    Language(String name) {
        userFriendlyName = name;
    }

    @Override
    public String toString() {
        return userFriendlyName;
    }
}
