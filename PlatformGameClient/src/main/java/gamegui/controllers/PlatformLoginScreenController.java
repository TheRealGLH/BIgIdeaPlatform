package gamegui.controllers;

import gamegui.GUISceneController;
import javafx.fxml.FXML;

public class PlatformLoginScreenController {
    GUISceneController sceneController = GUISceneController.getInstance();
    @FXML
    void LoginPressed(){
        //TODO add login methods to IPlatformGameClient interface
        System.out.println("[PlatformLoginScreenController.java] But logging in has no implementation yet...");
    }

    @FXML
    void backPressed(){
        sceneController.showMainMenu();
    }
}
