package main.java.ui.common.util;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.ui.common.interfaces.ISceneManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Component
public class SceneManager implements ISceneManager {
    //private Map<String, Parent> sceneMap = new HashMap<>();
    private Map<String, URL> sceneMap = new HashMap<>();
    private Stage mainStage;

    private ApplicationContext context;

    @Autowired
    public SceneManager(Stage mainStage, ApplicationContext context) {
        this.mainStage = mainStage;
        this.context = context;
    }

    @Override
    public void switchScene(String key) {
        var prepSceneTask = new Task<Scene>() {
            @Override
            protected Scene call() throws Exception {
                return prepareScene(sceneMap.get(key));
            }
        };

        prepSceneTask.setOnSucceeded(e -> {
            mainStage.setScene(prepSceneTask.getValue());

            mainStage.show();
        });

        prepSceneTask.setOnFailed(e -> {
            prepSceneTask.getException().printStackTrace();
        });

        Platform.runLater(new Thread(prepSceneTask));

    }

    private Scene prepareScene(URL fxmlUrl) {
        try {
            var pane = loadScene(fxmlUrl);
            var scene = mainStage.getScene();

            if (scene == null) {
                return new Scene(pane);
            }

            scene.setRoot(pane);
            return scene;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void registerScene(String key, URL fxmlUrl) {
        sceneMap.put(key, fxmlUrl);
    }

    private Parent loadScene(URL fxmlUrl) throws IOException {
        var loader = new FXMLLoader(fxmlUrl);
        loader.setControllerFactory(context::getBean);
        return loader.load();
    }
}
