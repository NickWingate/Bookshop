package main.java.domain.entities;

import main.java.domain.enums.Role;

public class Admin extends User{
    public Admin(String id,
                 String username,
                 String surname,
                 Address address) {
        super(id, username, surname, address);
    }

    public Admin(){

    }
    @Override
    public Role getRole() {
        return Role.ADMIN;
    }
}
