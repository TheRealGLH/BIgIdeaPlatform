package PlatformGameShared.Messages;

import PlatformGameShared.Enums.GameResponseMessageType;

public class PlatformGameResponseMessage {

    private GameResponseMessageType responseMessageType;

    public GameResponseMessageType getResponseMessageType() {
        return responseMessageType;
    }

    public void setResponseMessageType(GameResponseMessageType responseMessageType) {
        this.responseMessageType = responseMessageType;
    }
}
