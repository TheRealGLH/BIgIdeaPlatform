package Interfaces;

import Enums.InputType;

public interface IPlatformGameServer {

    public void registerPlayer(String name, String password, IPlatformGameClient client);

    public void loginPlayer(String name, String password, IPlatformGameClient client);

    public void startGame();

    public void receiveInput(InputType type);

}
