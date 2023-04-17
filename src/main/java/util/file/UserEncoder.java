package main.java.util.file;

import main.java.domain.entities.Address;
import main.java.domain.entities.Customer;
import main.java.domain.entities.User;
import main.java.domain.enums.Role;
import main.java.util.interfaces.IUserEncoder;

public class UserEncoder implements IUserEncoder {
    @Override
    public String EncodeObject(User object) {
        // user ID, username, surname,
        // house number, postcode, city, credit balance, role
        var string = object.getId() + ", " +
                object.getUsername() + ", " +
                object.getSurname() + ", " +
                EncodeAddress(object.getAddress()) + ", ";

        if (object.getRole() == Role.CUSTOMER){
            string += String.format("%.2f", ((Customer) object).getCreditBalance());
        }

        return string + ", " + object.getRole().toString() + '\n';
    }

    private String EncodeAddress(Address address){
        return address.getHouseNumber() + ", " +
                address.getPostcode() + ", " +
                address.getCity();
    }
}
