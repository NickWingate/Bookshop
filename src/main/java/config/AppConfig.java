package main.java.config;

import main.java.domain.entities.Book;
import main.java.domain.entities.User;
import main.java.ui.controllers.AdminViewController;
import main.java.ui.controllers.HomeViewController;
import main.java.util.file.*;
import main.java.util.interfaces.*;
import main.java.util.repositories.BookRepository;
import main.java.util.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
    public final String stockFilePath = "src/main/resources/Stock.txt";
    public final String usersFilePath = "src/main/resources/UserAccounts.txt";

    @Bean
    @Scope(value = "singleton")
    public IBookEncoder bookEncoder() {
        return new BookEncoder();
    }

    @Bean
    @Scope(value = "singleton")
    public IUserEncoder userEncoder() {
        return new UserEncoder();
    }

    @Bean
    @Scope(value = "singleton")
    public IBookParser bookParser() {
        return new BookParser();
    }

    @Bean
    @Scope(value = "singleton")
    public IUserParser userParser() {
        return new UserParser();
    }

    @Bean
    @Scope(value = "singleton")
    public ICSVWriter<Book> bookWriter(IBookEncoder bookEncoder) {
        return new CSVWriter<>(bookEncoder);
    }

    @Bean
    @Scope(value = "singleton")
    public ICSVWriter<User> userWriter(IUserEncoder userEncoder) {
        return new CSVWriter<>(userEncoder);
    }

    @Bean
    @Scope(value = "singleton")
    public IBookRepository bookRepository(IBookParser bookParser,
                                          ICSVWriter<Book> bookWriter) {
        return new BookRepository(bookParser, bookWriter, stockFilePath);
    }

    @Bean
    @Scope(value = "singleton")
    public IUserRepository userRepository(IUserParser userParser,
                                          ICSVWriter<User> userWriter) {
        return new UserRepository(userParser, userWriter, usersFilePath);
    }

    @Bean
    @Scope(value = "prototype")
    public HomeViewController homeViewController(IBookRepository bookRepository) {
        return new HomeViewController(bookRepository);
    }
}
