package gamegui.controllers;

import gamegui.GUIScreenController;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class PlatformLobbyScreenController {
    public Label lblJoinedPlayers;
    GUIScreenController screenController = GUIScreenController.getInstance();

    public void onStartAttempt(ActionEvent actionEvent) {
        screenController.joinGame();
    }

    public void onBackPressed(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Sorry, but exiting games has not yet been implemented...");
        alert.showAndWait();
    }
}
