package gameclient;

import PlatformGameShared.Enums.InputType;
import PlatformGameShared.Interfaces.IPlatformGameClient;
import PlatformGameShared.Interfaces.IPlatformGameServer;
import PlatformGameShared.Points.SpriteUpdate;

import java.util.List;

public class GameClientMessageSender implements IPlatformGameServer {

    private ICommunicator communicator = CommunicatorClientWebSocket.getInstance();

    @Override
    public void registerPlayer(String name, String password, IPlatformGameClient client) {
        throw new UnsupportedOperationException("Method registerPlayer() has not yet been implemented");
    }

    @Override
    public void loginPlayer(String name, String password, IPlatformGameClient client) {
        throw new UnsupportedOperationException("Method loginPlayer() has not yet been implemented");
    }

    @Override
    public void startGame() {
        throw new UnsupportedOperationException("Method startGame() has not yet been implemented");
    }

    @Override
    public void receiveInput(InputType type, IPlatformGameClient client) {
        throw new UnsupportedOperationException("Method receiveInput() has not yet been implemented");
    }

    @Override
    public void sendSpriteUpdates(List<SpriteUpdate> spriteUpdateList) {
        throw new UnsupportedOperationException("Method sendSpriteUpdates() has not yet been implemented");
    }
}
