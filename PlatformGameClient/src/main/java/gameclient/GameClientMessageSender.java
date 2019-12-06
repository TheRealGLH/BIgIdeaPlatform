package gameclient;

import PlatformGameShared.Enums.InputType;
import PlatformGameShared.Interfaces.IPlatformGameClient;
import PlatformGameShared.Interfaces.IPlatformGameServer;
import PlatformGameShared.Messages.Client.PlatformGameMessageInput;
import PlatformGameShared.Messages.Client.PlatformGameMessageLogin;
import PlatformGameShared.Messages.Client.PlatformGameMessageRegister;
import PlatformGameShared.Messages.Client.PlatformGameMessageStart;
import PlatformGameShared.Points.SpriteUpdate;

import java.util.List;

public class GameClientMessageSender implements IPlatformGameServer {

    private ICommunicator communicator = CommunicatorClientWebSocketEndpoint.getInstance();

    public GameClientMessageSender(){
        communicator.start();
    }

    @Override
    public void registerPlayer(String name, String password, IPlatformGameClient client) {
        communicator.setGameClient(client);
        PlatformGameMessageRegister messageRegister = new PlatformGameMessageRegister(name, password);
        communicator.sendMessage(messageRegister);
    }

    @Override
    public void loginPlayer(String name, String password, IPlatformGameClient client) {
        communicator.setGameClient(client);
        PlatformGameMessageLogin messageLogin = new PlatformGameMessageLogin(name, password);
        communicator.sendMessage(messageLogin);
    }

    @Override
    public void startGame(IPlatformGameClient client) {
        PlatformGameMessageStart messageStart = new PlatformGameMessageStart();
        communicator.sendMessage(messageStart);
    }

    @Override
    public void receiveInput(InputType type, IPlatformGameClient client) {
        PlatformGameMessageInput messageInput = new PlatformGameMessageInput(type);
        communicator.sendMessage(messageInput);
    }

    @Override
    public void sendSpriteUpdates(List<SpriteUpdate> spriteUpdateList) {
        throw new UnsupportedOperationException("This method is not supposed to be called on the client");
    }
}
