package main.java.domain.enums;

public enum Genre implements IBookProperty {
    POLITICS("Politics"),
    BUSINESS("Business"),
    COMPUTER_SCIENCE("Computer Science"),
    BIOGRAPHY("Biography");

    private String userFriendlyName;
    Genre(String name) {
        userFriendlyName = name;
    }

    @Override
    public String toString() {
        return userFriendlyName;
    }
}
