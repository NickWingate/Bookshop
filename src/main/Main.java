package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.*;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        var loader = new FXMLLoader(getClass().getResource("resources/fxml/HomeView.fxml"));
        try {
            Pane pane = loader.load();
            stage.setScene(new Scene(pane, 1080, 720));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage.show();
    }
}
