package main.java.ui.common.misc;

public enum BookshopScene {
    HOME {
        @Override
        String getPath() {
            return "/fxml/HomeView.fxml";
        }
    },
    ADMIN {
        @Override
        String getPath() {
            return "/fxml/AdminView.fxml";
        }
    },
    LOGIN {
        @Override
        String getPath() {
            return "/fxml/LoginView.fxml";
        }
    };

    abstract String getPath();
}
