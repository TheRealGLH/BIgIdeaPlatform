package gamegui.controllers;

import gamegui.GUIScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PlatformMainMenuController {

    GUIScreenController sceneController = GUIScreenController.getInstance();

    @FXML
    void LoginPressed(){
        sceneController.showLoginScreen();
    }

    @FXML
    void RegisterPressed(){
        sceneController.showRegisterScreen();
    }

    @FXML
    void debugGameView() {
        sceneController.joinGame();
        sceneController.showGameView();
    }

    @FXML
    void inputTestView(){
        sceneController.showInputTest();
    }
}
