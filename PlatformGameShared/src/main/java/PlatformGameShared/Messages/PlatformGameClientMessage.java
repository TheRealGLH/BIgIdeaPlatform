package PlatformGameShared.Messages;

import PlatformGameShared.Enums.GameClientMessageType;

public class PlatformGameClientMessage {
    private GameClientMessageType messageType;

    public GameClientMessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(GameClientMessageType messageType) {
        this.messageType = messageType;
    }
}
