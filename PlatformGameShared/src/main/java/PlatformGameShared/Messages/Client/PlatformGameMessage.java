package PlatformGameShared.Messages.Client;

import PlatformGameShared.Enums.GameClientMessageType;

public class PlatformGameMessage {
    private GameClientMessageType messageType;

    public GameClientMessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(GameClientMessageType messageType) {
        this.messageType = messageType;
    }
}
