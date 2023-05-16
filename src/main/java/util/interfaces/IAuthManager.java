package main.java.util.interfaces;

import main.java.domain.entities.User;

public interface IAuthManager {
    void login(User user);

    void logout();

    User currentUser();
}
