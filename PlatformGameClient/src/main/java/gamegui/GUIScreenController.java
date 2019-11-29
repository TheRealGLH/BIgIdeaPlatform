package gamegui;

import Enums.GameState;
import Enums.InputType;
import SharedClasses.SpriteUpdate;
import gamegui.Interfaces.ISpriteUpdateEventListener;
import gamegui.enums.GUIState;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class GUIScreenController extends ScreenController {

    private static final GUIScreenController instance = new GUIScreenController();

    private PlatformGUI platformGUI;
    private GUIState guiState;
    private List<ISpriteUpdateEventListener> spriteUpdateEventListeners = new ArrayList<>();
    private GUIScreenController() {

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
        if(guiState != GUIState.Game) return;
        for (ISpriteUpdateEventListener listener : spriteUpdateEventListeners){
            listener.handleSpriteUpdate(positions);
        }
    }

    @Override
    public void joinGame() {
        if(guiState != GUIState.Lobby) return;
        throw new UnsupportedOperationException("joinGame has not yet been implemented.");
    }

    @Override
    public void receiveGameState(GameState gameState) {
        if(guiState != GUIState.Game) return;
        throw new UnsupportedOperationException("receiveGameState has not yet been implemented.");
    }

    @Override
    public void sendInput(InputType inputType) {
        if(guiState != GUIState.Game) return;
        throw new UnsupportedOperationException("sendInput has not yet been implemented.");
    }

    @Override
    public List<SpriteUpdate> getAllSprites() {
        if(guiState == GUIState.Game) {
            throw new UnsupportedOperationException("updateScreen has not yet been implemented.");
        }
        throw new InvalidParameterException("You're not allowed to call getAllSprites when not in the game view.");
    }

    @Override
    public void addEventListener(ISpriteUpdateEventListener listener) {
        spriteUpdateEventListeners.add(listener);
    }
}


