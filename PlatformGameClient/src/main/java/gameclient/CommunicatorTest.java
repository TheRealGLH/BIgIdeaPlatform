package gameclient;

import PlatformGameShared.Enums.GameClientMessageType;
import PlatformGameShared.Messages.Client.PlatformGameMessage;

public class CommunicatorTest {

    public static void main(String[] args) {
        ICommunicator communicator = CommunicatorClientWebSocketEndpoint.getInstance();
        communicator.start();
        PlatformGameMessage message = new PlatformGameMessage();
        message.setMessageType(GameClientMessageType.Login);
        communicator.sendMessage(message);
        communicator.stop();
    }
}
