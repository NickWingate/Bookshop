package Entities;

import Enums.Role;

public class User {
    public User(int id,
                String username,
                String surname,
                Address address,
                Role role) {
        this.id = id;
        this.username = username;
        this.surname = surname;
        this.address = address;
        this.role = role;
    }

    private int id;
    private String username;
    private String surname;
    private Address address;
    private final Role role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }
}
