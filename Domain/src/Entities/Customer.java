package Entities;

import Enums.Role;

import java.math.BigDecimal;

public class Customer extends User{
    public Customer(int id,
                    String username,
                    String surname,
                    Address address,
                    Role role,
                    BigDecimal creditBalance,
                    Basket basket) {
        super(id, username, surname, address, role);
        this.creditBalance = creditBalance;
        this.basket = basket;
    }

    private BigDecimal creditBalance;

    private Basket basket;


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
