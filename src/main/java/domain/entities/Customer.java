package main.java.domain.entities;

import main.java.domain.enums.Role;

public class Customer extends User{
    public Customer(int id,
                    String username,
                    String surname,
                    Address address,
                    double creditBalance) {
        super(id, username, surname, address);
        this.creditBalance = creditBalance;
    }

    public Customer(){

    }
    private double creditBalance;

    private Basket basket = new Basket();


    @Override
    public Role getRole() {
        return Role.CUSTOMER;
    }

    public Basket getBasket() {
        return basket;
    }

    public double getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(double creditBalance) {
        this.creditBalance = creditBalance;
    }

    public void incrementCreditBalance(double amount) {
        creditBalance += amount;
    }
}
