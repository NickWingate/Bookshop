package main.java.ui.common.util;

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
    private Map<String, Parent> sceneMap = new HashMap<>();
    private Stage mainStage;

    private ApplicationContext context;

    @Autowired
    public SceneManager(Stage mainStage, ApplicationContext context) {
        this.mainStage = mainStage;
        this.context = context;
    }

    @Override
    public void switchScene(String key) {
        var scene = prepareScene(sceneMap.get(key));

        mainStage.setScene(scene);
        mainStage.sizeToScene();

        mainStage.show();
    }

    private Scene prepareScene(Parent pane) {
        var scene = mainStage.getScene();

        if (scene == null) {
            return new Scene(pane);
        }

        scene.setRoot(pane);
        return scene;
    }

    @Override
    public void registerScene(String key, URL fxmlUrl) {
        try {
            var scene = loadScene(fxmlUrl);
            sceneMap.put(key, scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Parent loadScene(URL fxmlUrl) throws IOException {
        var loader = new FXMLLoader(fxmlUrl);
        loader.setControllerFactory(context::getBean);
        return loader.load();
    }
}
