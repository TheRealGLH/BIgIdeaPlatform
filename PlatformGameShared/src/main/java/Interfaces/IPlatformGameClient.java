package Interfaces;

import Enums.*;
import SharedClasses.SpriteUpdate;

import java.util.List;

public interface IPlatformGameClient {

    public void updateScreen(List<SpriteUpdate> positions);

    public void joinGame();

    public void receiveGameState(GameState gameState);

    public void sendInput(InputType inputType);

    public List<SpriteUpdate> getAllSprites();

    public void sendRegisterRequest(String name, String password);

    public void sendLoginRequest(String name, String password);

    public void receiveLoginState(String name, LoginState loginState);

    public void receiveRegisterState(String name, RegisterState registerState);


}
