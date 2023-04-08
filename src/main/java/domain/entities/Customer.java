package main.java.domain.entities;

import main.java.domain.enums.Role;

import java.math.BigDecimal;

public class Customer extends User{
    public Customer(int id,
                    String username,
                    String surname,
                    Address address,
                    BigDecimal creditBalance) {
        super(id, username, surname, address);
        this.creditBalance = creditBalance;
    }

    public Customer(){

    }
    private BigDecimal creditBalance;

    private Basket basket = new Basket();


    @Override
    public Role getRole() {
        return Role.CUSTOMER;
    }

    public Basket getBasket() {
        return basket;
    }

    public BigDecimal getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(BigDecimal creditBalance) {
        this.creditBalance = creditBalance;
    }

    public void incrementCreditBalance(BigDecimal amount) {
        creditBalance.add(amount);
    }
}
