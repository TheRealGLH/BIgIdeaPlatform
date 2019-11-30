package Interfaces;

import Enums.InputType;
import SharedClasses.SpriteUpdate;

import java.util.List;

public interface IPlatformGameServer {

    public void registerPlayer(String name, String password, IPlatformGameClient client);

    public void loginPlayer(String name, String password, IPlatformGameClient client);

    public void startGame();

    public void receiveInput(InputType type);

    void sendSpriteUpdates(List<SpriteUpdate> spriteUpdateList);
}
