package gamegui;

public class GUISceneController {

    private static final GUISceneController instance = new GUISceneController();

    private PlatformGUI platformGUI;
    private GUISceneController() {

    }

    //We use a so called 'Eager' Singleton pattern here, because it supposedly goes nicer with a multithreaded environment (Such as JavaFX)
    public static GUISceneController getInstance() {
        return instance;
    }

    public void setPlatformGUI(PlatformGUI platformGUI){
        this.platformGUI = platformGUI;
    }

    public void showMainMenu(){
        platformGUI.showMainMenu();
    }

    public void showLoginScreen(){
        platformGUI.showLoginScreen();
    }

    public void showRegisterScreen(){
        platformGUI.showRegisterScreen();
    }

    public void showLobbyScreen(){
        platformGUI.showLobbyScreen();
    }

    public void showGameView(){
        platformGUI.showGameViewScreen();
    }
}
