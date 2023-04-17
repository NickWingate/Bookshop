package test.java.util.file;

import main.java.domain.entities.Address;
import main.java.domain.entities.Admin;
import main.java.domain.entities.Customer;
import main.java.util.file.UserEncoder;
import main.java.util.interfaces.IUserEncoder;
import org.assertj.core.api.AbstractArrayAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserEncoderTest {

    private IUserEncoder _sut;

    @BeforeEach
    void setUp() {
        _sut = new UserEncoder();
    }

    @Test
    void encodeObject_shouldEncodeAdmin() {
        // Arrange
        var expected = "101, user1, Smith, 12, LE11 3TU, Loughborough, , admin\n";
        var user = new Admin(
                101,
                "user1",
                "Smith",
                new Address("12", "LE11 3TU", "Loughborough")
        );

        // Act
        var actual = _sut.EncodeObject(user);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void encodeObject_shouldEncodeCustomer() {
        // Arrange
        var expected = "102, user2, Williams, 14, E20 3BS, London, 100.00, customer\n";
        var user = new Customer(
                102,
                "user2",
                "Williams",
                new Address("14", "E20 3BS", "London"),
                100.00
        );

        // Act
        var actual = _sut.EncodeObject(user);

        // Assert
        assertEquals(expected, actual);
    }
}