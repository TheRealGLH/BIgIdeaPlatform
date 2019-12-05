package gameclient;

import PlatformGameShared.Enums.GameClientMessageType;
import PlatformGameShared.Interfaces.IPlatformGameServer;
import PlatformGameShared.Messages.Client.PlatformGameMessage;

public class CommunicatorTest {

    public static void main(String[] args) {
        IPlatformGameServer server = new GameClientMessageSender();
        server.registerPlayer("mr test","123",null);
    }
}
