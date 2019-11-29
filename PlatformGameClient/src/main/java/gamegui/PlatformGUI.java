package gamegui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.File;
import java.io.IOException;

public class PlatformGUI extends Application {


    private Scene mainScreen;
    private Scene loginScreen;
    private Scene registerScreen;
    private Scene lobbyScreen;
    private Scene gameViewScreen;
    private Stage primaryStage;
    private final String fxmlpath = "/fxml/";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        //Preload all URLS
        VBox mainRoot;
        VBox loginRoot;
        VBox registerRoot;
        VBox lobbyRoot;
        VBox gameViewRoot;
        try {
            mainRoot = FXMLLoader.load(getClass().getResource(fxmlpath + "PlatformMainScreen.fxml"));
            loginRoot = FXMLLoader.load(getClass().getResource(fxmlpath + "PlatformLoginScreen.fxml"));
            registerRoot = FXMLLoader.load(getClass().getResource(fxmlpath + "PlatformRegisterScreen.fxml"));
            lobbyRoot = FXMLLoader.load(getClass().getResource(fxmlpath + "PlatformLobbyScreen.fxml"));
            gameViewRoot = FXMLLoader.load(getClass().getResource(fxmlpath + "PlatformGameView.fxml"));
            mainScreen = new Scene(mainRoot);
            loginScreen = new Scene(loginRoot);
            registerScreen = new Scene(registerRoot);
            lobbyScreen = new Scene(lobbyRoot);
            gameViewScreen = new Scene(gameViewRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
        GUISceneController.getInstance().setPlatformGUI(this);
        GUISceneController.getInstance().showMainMenu();
        System.out.println("Game started.");
    }

    void showMainMenu() {
        primaryStage.setTitle("Platform Wars!");
        primaryStage.setScene(mainScreen);
        primaryStage.show();
    }

    void showLoginScreen() {
        primaryStage.setTitle("Platform Wars Login");
        primaryStage.setScene(loginScreen);
        primaryStage.show();
    }

    void showRegisterScreen() {
        primaryStage.setTitle("Register a new Platform Wars account.");
        primaryStage.setScene(registerScreen);
        primaryStage.show();
    }

    void showLobbyScreen() {
        primaryStage.setTitle("Platform Wars: In Lobby");
        primaryStage.setScene(lobbyScreen);
        primaryStage.show();
    }

    void showGameViewScreen() {
        primaryStage.setTitle("Platform Wars!");
        primaryStage.setScene(gameViewScreen);
        primaryStage.show();
    }
}
