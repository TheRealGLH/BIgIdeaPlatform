package gamegui.controllers;

import gamegui.GUISceneController;
import javafx.fxml.FXML;

public class PlatformMainMenuController {

    GUISceneController sceneController = GUISceneController.getInstance();

    @FXML
    void LoginPressed(){
        sceneController.showLoginScreen();
    }

    @FXML
    void RegisterPressed(){
        sceneController.showRegisterScreen();
    }
}
