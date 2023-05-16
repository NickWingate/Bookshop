package main.java.util.misc;

import main.java.domain.entities.User;
import main.java.util.interfaces.IAuthManager;

public class AuthManager implements IAuthManager {

    private User user;
    @Override
    public void login(User user) {
        this.user = user;
    }

    @Override
    public void logout() {
        user = null;
    }

    @Override
    public User currentUser() {
        return user;
    }
}
