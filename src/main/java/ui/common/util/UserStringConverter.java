package main.java.ui.common.util;

import javafx.util.StringConverter;
import main.java.domain.entities.User;

public class UserStringConverter extends StringConverter<User> {
    @Override
    public String toString(User user) {
        if (user == null)
            return "";
        return user.getUsername() + ":" + user.getRole();
    }

    @Override
    public User fromString(String s) {
        return null;
    }
}
