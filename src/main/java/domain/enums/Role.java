package main.java.domain.enums;

public enum Role {
    ADMIN,
    CUSTOMER;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
