package PlatformGameShared.Messages.Response;

import PlatformGameShared.Enums.GameResponseMessageType;

public class PlatformGameResponseMessageAllowInput extends PlatformGameResponseMessage {

    public PlatformGameResponseMessageAllowInput(){
        super();
        this.setResponseMessageType(GameResponseMessageType.AllowInput);
    }
}
