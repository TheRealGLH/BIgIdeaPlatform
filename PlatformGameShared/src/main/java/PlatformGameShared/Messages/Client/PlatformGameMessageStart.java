package PlatformGameShared.Messages.Client;

import PlatformGameShared.Enums.GameClientMessageType;

public class PlatformGameMessageStart extends PlatformGameMessage{

    public PlatformGameMessageStart(){
        this.setMessageType(GameClientMessageType.StartGame);
    }

}
