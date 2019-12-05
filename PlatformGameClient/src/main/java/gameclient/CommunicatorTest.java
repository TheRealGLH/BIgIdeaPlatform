package gameclient;

import PlatformGameShared.Enums.GameClientMessageType;
import PlatformGameShared.Messages.PlatformGameClientMessage;

public class CommunicatorTest {

    public static void main(String[] args) {
        ICommunicator communicator = CommunicatorClientWebSocket.getInstance();
        communicator.start();
        PlatformGameClientMessage message = new PlatformGameClientMessage();
        message.setMessageType(GameClientMessageType.Login);
        communicator.sendMessage(message);
        communicator.stop();
    }
}
