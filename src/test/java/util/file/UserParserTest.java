package test.java.util.file;

import main.java.domain.entities.Address;
import main.java.domain.entities.Admin;
import main.java.domain.entities.Customer;
import main.java.util.file.UserParser;
import main.java.util.interfaces.IUserParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserParserTest {

    IUserParser _sut;

    @BeforeEach
    void setUp(){
        _sut = new UserParser();
    }

    @Test
    void parseLine_shouldReturnAdmin() {
        // Arrange
        var line = "101, user1, Smith, 12, LE11 3TU, Loughborough, , admin\n";
        var expected = new Admin(
                101,
                "user1",
                "Smith",
                new Address("12", "LE11 3TU", "Loughborough")
        );

        // Act
        var actual = _sut.ParseLine(line);

        // Assert
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void parseLine_shouldReturnCustomer() {
        // Arrange
        var line = "102, user2, Williams, 14, E20 3BS, London, 100.00, customer\n";
        var expected = new Customer(
                102,
                "user2",
                "Williams",
                new Address("14", "E20 3BS", "London"),
                new BigDecimal("100.00")
        );

        // Act
        var actual = _sut.ParseLine(line);

        // Assert
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}