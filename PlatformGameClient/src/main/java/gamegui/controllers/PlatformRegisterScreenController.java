package gamegui.controllers;

import gamegui.GUISceneController;
import javafx.fxml.FXML;

public class PlatformRegisterScreenController {
    GUISceneController sceneController = GUISceneController.getInstance();

    @FXML
    void registerPressed() {
        //TODO add login methods to IPlatformGameClient interface
        System.out.println("[PlatformRegisterScreenController.java] But registering has no implementation yet...");
    }

    @FXML
    void backPressed() {
        sceneController.showMainMenu();
    }
}
