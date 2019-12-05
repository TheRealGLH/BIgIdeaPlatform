package gamegui.controllers;

import gamegui.GUIScreenController;
import gamegui.Interfaces.ILobbyEventListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class PlatformLobbyScreenController implements ILobbyEventListener {
    public Label lblJoinedPlayers;
    GUIScreenController screenController = GUIScreenController.getInstance();

    @FXML
    protected void initialize() {
        screenController.addEventListener(this);
    }

    public void onStartAttempt(ActionEvent actionEvent) {
        screenController.joinGame();
    }

    public void onBackPressed(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Sorry, but exiting games has not yet been implemented...");
        alert.showAndWait();
    }

    @Override
    public void updateLobbyPlayers(String[] playerNames) {
        StringBuilder sb = new StringBuilder();
        sb.append("Joined players:");
        for (String player : playerNames) {
            sb.append("\n\t- ").append(player);
        }
        lblJoinedPlayers.setText(sb.toString());
    }

    @Override
    public void updateMap(String mapName) {
        Alert.AlertType type = Alert.AlertType.INFORMATION;
        new Alert(type, "The map is now " + mapName).showAndWait();
    }
}
