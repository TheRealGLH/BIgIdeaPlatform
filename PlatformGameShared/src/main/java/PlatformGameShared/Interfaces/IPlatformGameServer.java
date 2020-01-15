package PlatformGameShared.Interfaces;

import PlatformGameShared.Enums.InputType;
import PlatformGameShared.Points.SpriteUpdate;

import java.util.List;

public interface IPlatformGameServer {

    public void registerPlayer(String name, String password, IPlatformGameClient client);

    public void loginPlayer(String name, String password, IPlatformGameClient client);

    public void attemptStartGame(IPlatformGameClient client);

    public void receiveInput(InputType type, IPlatformGameClient client);

    void sendSpriteUpdates(List<SpriteUpdate> spriteUpdateList);

    void sendInputRequest();
}
