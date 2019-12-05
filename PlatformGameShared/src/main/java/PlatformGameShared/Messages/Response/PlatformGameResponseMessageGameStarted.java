package PlatformGameShared.Messages.Response;

import PlatformGameShared.Enums.GameResponseMessageType;

public class PlatformGameResponseMessageGameStarted extends PlatformGameResponseMessage {

    public PlatformGameResponseMessageGameStarted(){
        this.setResponseMessageType(GameResponseMessageType.NotifyStart);
    }

}
