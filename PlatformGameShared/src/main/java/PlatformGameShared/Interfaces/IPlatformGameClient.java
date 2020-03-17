package PlatformGameShared.Interfaces;

import PlatformGameShared.Enums.GameState;
import PlatformGameShared.Enums.InputType;
import PlatformGameShared.Enums.LoginState;
import PlatformGameShared.Enums.RegisterState;
import PlatformGameShared.Points.SpriteUpdate;

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

    public int getPlayerNr();

    public String getName();

    public void setPlayerNr(int playerNr);

    public void setName(String name);

    public void gameStartNotification();

    public void lobbyJoinedNotify(String[] playerNames);

    public void lobbyMapNamesNotify(String[] mapNames);

    public void lobbyNotifyNewMapChoice(String newMapName);

    public void receiveAllowInput();

    public Object getAddress();

    void setAddress(Object o);
}
