package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.*;
import main.java.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Main extends Application {

    private AnnotationConfigApplicationContext context;

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
        var loader = new FXMLLoader(getClass().getResource("resources/fxml/HomeView.fxml"));
        loader.setControllerFactory(context::getBean);
        try {
            Pane pane = loader.load();
            stage.setScene(new Scene(pane, 1080, 720));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage.show();
    }
}
