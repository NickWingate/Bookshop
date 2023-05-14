package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import main.java.config.AppConfig;
import main.java.ui.common.interfaces.ISceneManager;
import main.java.ui.common.misc.BookshopScene;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Main extends Application {

    private AnnotationConfigApplicationContext context;
    private ISceneManager _sceneManager;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
    }

    @Override
    public void start(Stage stage) {

        _sceneManager = context.getBean(ISceneManager.class, stage);

        _sceneManager.registerScene("login", getClass().getResource("resources/fxml/LoginView.fxml"));
        _sceneManager.registerScene("home", getClass().getResource("resources/fxml/HomeView.fxml"));
        _sceneManager.registerScene("admin", getClass().getResource("resources/fxml/AdminView.fxml"));

        _sceneManager.switchScene("login");

    }
}
