package Entities;

import Enums.Role;

public class Admin extends User{
    public Admin(int id,
                 String username,
                 String surname,
                 Address address,
                 Role role) {
        super(id, username, surname, address, role);
    }

    @Override
    public Role getRole() {
        return Role.ADMIN;
    }
}
