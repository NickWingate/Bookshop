package main.java.ui.common.interfaces;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import main.java.ui.common.misc.BookshopScene;

import java.net.URL;

public interface ISceneManager {
    void registerScene(String key, URL fxmlUrl);

    void switchScene(String key);
}
