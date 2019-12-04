package gamegui;

import Enums.GameState;
import Enums.InputType;
import Enums.LoginState;
import Enums.RegisterState;
import Interfaces.IPlatformGameServer;
import SharedClasses.SpriteUpdate;
import gameclient.GameServer;
import gamegui.Interfaces.ISpriteUpdateEventListener;
import gamegui.enums.GUIState;
import javafx.application.Platform;
import models.classes.Game;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class GUIScreenController extends ScreenController {

    private static final GUIScreenController instance = new GUIScreenController();

    private PlatformGUI platformGUI;
    private GUIState guiState;
    private IPlatformGameServer gameServer;// message creator
    private List<ISpriteUpdateEventListener> spriteUpdateEventListeners = new ArrayList<>();
    private GUIScreenController() {
        gameServer = new GameServer();
    }

    //We use a so called 'Eager' Singleton pattern here, because it supposedly goes nicer with a multithreaded environment (Such as JavaFX)
    public static GUIScreenController getInstance() {
        return instance;
    }

    public void setPlatformGUI(PlatformGUI platformGUI){
        this.platformGUI = platformGUI;
    }

    public void showMainMenu(){
        guiState = GUIState.MainMenu;
        platformGUI.showMainMenu();
    }

    public void showInputTest(){
        guiState = GUIState.MainMenu;
        platformGUI.showInputTest();
    }

    public void showLoginScreen(){
        guiState = GUIState.Login;
        platformGUI.showLoginScreen();
    }

    public void showRegisterScreen(){
        guiState = GUIState.Register;
        platformGUI.showRegisterScreen();
    }

    public void showLobbyScreen(){
        guiState = GUIState.Lobby;
        platformGUI.showLobbyScreen();
    }

    public void showGameView(){
        guiState = GUIState.Game;
        platformGUI.showGameViewScreen();
    }

    @Override
    public void updateScreen(List<SpriteUpdate> positions) {
        for (ISpriteUpdateEventListener listener : spriteUpdateEventListeners){
            Platform.runLater(() -> {
                        listener.handleSpriteUpdate(positions);
                    }
            );
        }
    }

    @Override
    public void joinGame() {
        if(guiState != GUIState.MainMenu) return;
        gameServer.loginPlayer("REMOVE THIS LATER","123",this);
        gameServer.startGame();
    }

    @Override
    public void receiveGameState(GameState gameState) {
        if(guiState != GUIState.Game) return;
        throw new UnsupportedOperationException("receiveGameState has not yet been implemented.");
    }

    @Override
    public void sendInput(InputType inputType) {
        if(guiState != GUIState.Game) return;
        gameServer.receiveInput(inputType);
    }

    @Override
    public List<SpriteUpdate> getAllSprites() {
        if(guiState == GUIState.Game) {
            throw new UnsupportedOperationException("updateScreen has not yet been implemented.");
        }
        throw new InvalidParameterException("You're not allowed to call getAllSprites when not in the game view.");
    }

    @Override
    public void sendRegisterRequest(String name, String password) {
        gameServer.registerPlayer(name,password,this);
    }

    @Override
    public void sendLoginRequest(String name, String password) {
        gameServer.loginPlayer(name,password,this);
    }

    @Override
    public void receiveLoginState(String name, LoginState loginState) {
        throw new UnsupportedOperationException("method receiveLoginSuccess not implemented");
    }

    @Override
    public void receiveRegisterState(String name, RegisterState registerState) {
        throw new UnsupportedOperationException("method receiveRegisterSuccess not implemented");
    }

    @Override
    public void addSpriteEventListener(ISpriteUpdateEventListener listener) {
        spriteUpdateEventListeners.add(listener);
    }
}


