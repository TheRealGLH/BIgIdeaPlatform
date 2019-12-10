package PlatformGameShared.Messages.Response;

import PlatformGameShared.Enums.GameResponseMessageType;

public class PlatformGameResponseMessageAllowInput extends PlatformGameResponseMessage {

    public PlatformGameResponseMessageAllowInput(){
        this.setResponseMessageType(GameResponseMessageType.AllowInput);
    }
}
