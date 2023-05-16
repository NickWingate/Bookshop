package main.java.ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.util.StringConverter;
import main.java.domain.entities.User;
import main.java.ui.common.interfaces.ISceneManager;
import main.java.util.interfaces.IAuthManager;
import main.java.util.interfaces.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class LoginViewController implements Initializable {

    private IUserRepository _userRepository;
    private ISceneManager _sceneManager;
    private StringConverter<User> _userStringConverter;

    @FXML
    private ChoiceBox<User> userChoiceBox;

    @FXML
    private Button loginBtn;
    private IAuthManager _authManager;

    @Autowired
    public LoginViewController(IUserRepository userRepository,
                               ISceneManager sceneManager,
                               StringConverter<User> userStringConverter,
                               IAuthManager authManager) {
        _userRepository = userRepository;
        _sceneManager = sceneManager;
        _userStringConverter = userStringConverter;
        _authManager = authManager;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateUsernames();

        loginBtn.setOnAction((e) -> login());
    }

    private void login() {
        var user = userChoiceBox.getValue();
        _authManager.login(user);
        switch (user.getRole()){
            case CUSTOMER -> _sceneManager.switchScene("home");
            case ADMIN -> _sceneManager.switchScene("admin");
        }
    }

    private void populateUsernames() {
        var users = _userRepository.GetAll();

        userChoiceBox.setConverter(_userStringConverter);

        userChoiceBox.getItems().addAll(users);
    }
}
