package gamegui.controllers;

import gamegui.GUIScreenController;
import javafx.fxml.FXML;

public class PlatformLoginScreenController {
    GUIScreenController sceneController = GUIScreenController.getInstance();
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
