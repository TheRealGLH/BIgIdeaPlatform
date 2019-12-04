package gamegui.controllers;

import gamegui.GUIScreenController;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class PlatformLoginScreenController {
    public TextField FieldName;
    public PasswordField FieldPassword;
    GUIScreenController sceneController = GUIScreenController.getInstance();
    @FXML
    void LoginPressed(){
        String name = FieldName.getText();
        String password = FieldPassword.getText();
        sceneController.sendLoginRequest(name,password);
    }

    @FXML
    void backPressed(){
        sceneController.showMainMenu();
    }
}
