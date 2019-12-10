package gameclient;

import PlatformGameShared.Interfaces.IPlatformGameServer;

public class CommunicatorTest {

    public static void main(String[] args) {
        IPlatformGameServer server = new GameClientMessageSender();
        server.registerPlayer("mr test","123",null);
    }
}
