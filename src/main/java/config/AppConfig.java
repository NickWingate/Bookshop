package main.java.config;

import javafx.stage.Stage;
import javafx.util.StringConverter;
import main.java.domain.entities.Book;
import main.java.domain.entities.User;
import main.java.ui.common.interfaces.ISceneManager;
import main.java.ui.common.util.SceneManager;
import main.java.ui.common.util.UserStringConverter;
import main.java.ui.controllers.AdminViewController;
import main.java.ui.controllers.HomeViewController;
import main.java.ui.controllers.LoginViewController;
import main.java.util.file.*;
import main.java.util.interfaces.*;
import main.java.util.misc.AuthManager;
import main.java.util.repositories.BookRepository;
import main.java.util.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
    public final String stockFilePath = "src/main/resources/Stock.txt";
    public final String usersFilePath = "src/main/resources/UserAccounts.txt";

    @Autowired
    ApplicationContext context;

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
    @Scope(value = "singleton")
    public StringConverter<User> userStringConverter() {
        return new UserStringConverter();
    }

    @Bean
    @Scope(value = "singleton")
    public IAuthManager authManager() {
        return new AuthManager();
    }

    @Bean
    @Scope(value = "prototype")
    public HomeViewController homeViewController(IBookRepository bookRepository,
                                                 IUserRepository userRepository,
                                                 IAuthManager authManager,
                                                 ISceneManager sceneManager) {
        return new HomeViewController(bookRepository, userRepository, authManager, sceneManager);
    }

    @Bean
    @Scope(value = "prototype")
    public AdminViewController adminViewController(IBookRepository bookRepository, IAuthManager authManager, ISceneManager sceneManager) {
        return new AdminViewController(bookRepository, authManager, sceneManager);
    }

    @Bean
    @Scope(value = "prototype")
    public LoginViewController loginViewController(IUserRepository userRepository,
                                                   ISceneManager sceneManager,
                                                   StringConverter<User> userStringConverter,
                                                   IAuthManager authManager) {
        return new LoginViewController(userRepository, sceneManager, userStringConverter, authManager);
    }

    @Bean
    @Scope(value = "singleton")
    @Lazy(value = true)
    public ISceneManager sceneManager(Stage mainStage) {
        return new SceneManager(mainStage, context);
    }
}
