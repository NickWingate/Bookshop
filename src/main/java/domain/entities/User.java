package main.java.domain.entities;


import main.java.domain.enums.Role;

public abstract class User {
    public User(String id,
                String username,
                String surname,
                Address address) {
        this.id = id;
        this.username = username;
        this.surname = surname;
        this.address = address;
    }

    public User(){

    }

    private String id;
    private String username;
    private String surname;
    private Address address;

    public abstract Role getRole();

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

}
