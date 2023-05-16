package main.java.domain.entities;

import javafx.beans.property.SimpleDoubleProperty;
import main.java.domain.enums.Role;

public class Customer extends User{
    public Customer(String id,
                    String username,
                    String surname,
                    Address address,
                    double creditBalance) {
        super(id, username, surname, address);
        this.creditBalance.set(creditBalance);
    }

    public Customer(){

    }

    private SimpleDoubleProperty creditBalance = new SimpleDoubleProperty(this, "creditBalance");

    private Basket basket = new Basket();


    @Override
    public Role getRole() {
        return Role.CUSTOMER;
    }

    public Basket getBasket() {
        return basket;
    }

    public double getCreditBalance() {
        return creditBalance.get();
    }

    public void setCreditBalance(double creditBalance) {
        this.creditBalance.set(creditBalance);
    }

    public SimpleDoubleProperty creditBalanceProperty() {
        return creditBalance;
    }

    public void incrementCreditBalance(double amount) {
        creditBalance.set((double) (creditBalance.get() + (amount)));
    }
}
