package gamegui.controllers;

import gamegui.GUIScreenController;
import javafx.fxml.FXML;

public class PlatformRegisterScreenController {
    GUIScreenController sceneController = GUIScreenController.getInstance();

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
