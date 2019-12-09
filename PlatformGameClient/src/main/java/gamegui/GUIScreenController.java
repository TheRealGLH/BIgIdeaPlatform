package gamegui;

import PlatformGameShared.Enums.GameState;
import PlatformGameShared.Enums.InputType;
import PlatformGameShared.Enums.LoginState;
import PlatformGameShared.Enums.RegisterState;
import PlatformGameShared.Interfaces.IPlatformGameServer;
import PlatformGameShared.Points.SpriteUpdate;
import gameclient.GameClientMessageSender;
import gamegui.Interfaces.ILobbyEventListener;
import gamegui.Interfaces.ISpriteUpdateEventListener;
import gamegui.enums.GUIState;
import gameserver.GameServer;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class GUIScreenController extends ScreenController {

    private static final GUIScreenController instance = new GUIScreenController();

    private PlatformGUI platformGUI;
    private GUIState guiState;
    private IPlatformGameServer gameServer;// message creator
    private List<ISpriteUpdateEventListener> spriteUpdateEventListeners = new ArrayList<>();
    private List<ILobbyEventListener> lobbyEventListeners = new ArrayList<>();
    private String name;

    private GUIScreenController() {
        gameServer = new GameServer();
    }

    //We use a so called 'Eager' Singleton pattern here, because it supposedly goes nicer with a multithreaded environment (Such as JavaFX)
    public static GUIScreenController getInstance() {
        return instance;
    }

    public void setPlatformGUI(PlatformGUI platformGUI) {
        this.platformGUI = platformGUI;
    }

    public void showMainMenu() {
        guiState = GUIState.MainMenu;
        platformGUI.showMainMenu();
    }

    public void showInputTest() {
        guiState = GUIState.MainMenu;
        platformGUI.showInputTest();
    }

    public void showLoginScreen() {
        guiState = GUIState.Login;
        platformGUI.showLoginScreen();
    }

    public void showRegisterScreen() {
        guiState = GUIState.Register;
        platformGUI.showRegisterScreen();
    }

    public void showLobbyScreen() {
        guiState = GUIState.Lobby;
        platformGUI.showLobbyScreen();
    }

    public void showGameView() {
        guiState = GUIState.Game;
        platformGUI.showGameViewScreen();
    }

    @Override
    public void updateScreen(List<SpriteUpdate> positions) {
        for (ISpriteUpdateEventListener listener : spriteUpdateEventListeners) {
            Platform.runLater(() -> {
                        listener.handleSpriteUpdate(positions);
                    }
            );
        }
    }

    @Override
    public void joinGame() {
        if (guiState != GUIState.Lobby) return;
        gameServer.startGame(this);
    }

    @Override
    public void receiveGameState(GameState gameState) {
        if (guiState != GUIState.Game) return;
        throw new UnsupportedOperationException("receiveGameState has not yet been implemented.");
    }

    @Override
    public void sendInput(InputType inputType) {
        if (guiState != GUIState.Game) return;
        gameServer.receiveInput(inputType, this);
    }

    @Override
    public List<SpriteUpdate> getAllSprites() {
        if (guiState == GUIState.Game) {
            throw new UnsupportedOperationException("getAllSprites has not yet been implemented.");
        }
        throw new InvalidParameterException("You're not allowed to call getAllSprites when not in the game view.");
    }

    @Override
    public void sendRegisterRequest(String name, String password) {
        gameServer.registerPlayer(name, password, this);
    }

    @Override
    public void sendLoginRequest(String name, String password) {
        gameServer.loginPlayer(name, password, this);
    }

    @Override
    public void receiveLoginState(String name, LoginState loginState) {
        Platform.runLater(() -> {
                    String title = "Logging in.";
                    String message = "";
                    Alert.AlertType alertType = Alert.AlertType.INFORMATION;
                    switch (loginState) {
                        case SUCCESS:
                            message = "You were logged in as " + name;
                            break;
                        case INCORRECTDATA:
                            message = "Incorrect user/password combination.";
                            break;
                        case BANNED:
                            message = "This account has been banned from playing";
                            alertType = Alert.AlertType.ERROR;
                            break;
                        default:
                            message = "Invalid loginstate: " + loginState;
                    }
                    platformGUI.showPopupMessage(title, message, alertType);
                    if (loginState == LoginState.SUCCESS) {
                        this.name = name;
                        this.showLobbyScreen();
                    }
                }
        );

    }

    @Override
    public void receiveRegisterState(String name, RegisterState registerState) {
        Platform.runLater(() -> {
                    String title = "Register.";
                    String message = "";
                    Alert.AlertType alertType = Alert.AlertType.INFORMATION;
                    switch (registerState) {
                        case SUCCESS:
                            message = "Registered as " + name + ". You may now log in";
                            break;
                        case INCORRECTDATA:
                            alertType = Alert.AlertType.ERROR;
                            message = "THe supplied credentials no mot match our requirements.";
                            break;
                        case ALREADYEXISTS:
                            alertType = Alert.AlertType.ERROR;
                            message = "The account " + name + " was already registered";
                            break;
                    }
                    platformGUI.showPopupMessage(title, message, alertType);
                    if (registerState == RegisterState.SUCCESS) platformGUI.showLoginScreen();
                }
        );

    }

    @Override
    public int getPlayerNr() {
        return 1;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setPlayerNr(int playerNr) {
        //not here
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void gameStartNotification() {
        Platform.runLater(() -> {
            showGameView();
                }
        );

    }

    @Override
    public void lobbyJoinedNotify(String[] playerNames) {
        Platform.runLater(() -> {
                    for (ILobbyEventListener lobbyEventListener : lobbyEventListeners) {
                        lobbyEventListener.updateLobbyPlayers(playerNames);
                    }
                }
        );
    }

    @Override
    public void addSpriteEventListener(ISpriteUpdateEventListener listener) {
        spriteUpdateEventListeners.add(listener);
    }

    @Override
    public void addEventListener(ILobbyEventListener lobbyEventListener) {
        lobbyEventListeners.add(lobbyEventListener);
    }
}


