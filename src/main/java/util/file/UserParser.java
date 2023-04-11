package main.java.util.file;

import main.java.domain.entities.Address;
import main.java.domain.entities.Admin;
import main.java.domain.entities.Customer;
import main.java.domain.entities.User;
import main.java.domain.enums.Role;
import main.java.util.interfaces.IUserParser;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

public class UserParser implements IUserParser {
    @Override
    public User ParseLine(String line) {
        var rawValues = line.split(",");
        var values = new String[rawValues.length];
        for (int i = 0; i < values.length; i++) {
            values[i] = rawValues[i].trim();
        }
        // user ID, username, surname, house number, postcode, city, credit balance, role
        var role = Role.valueOf(values[7].toUpperCase());

        User user = null;
        switch (role) {
            case ADMIN -> user = new Admin();
            case CUSTOMER -> user = new Customer();
        }

        user.setId(Integer.valueOf(values[0]));
        user.setUsername(values[1]);
        user.setSurname(values[2]);

        var address = new Address(values[3], values[4], values[5]);
        user.setAddress(address);

        if (role == Role.CUSTOMER){
            ((Customer) user).setCreditBalance(new BigDecimal(values[6]));
        }

        return user;
    }
}
