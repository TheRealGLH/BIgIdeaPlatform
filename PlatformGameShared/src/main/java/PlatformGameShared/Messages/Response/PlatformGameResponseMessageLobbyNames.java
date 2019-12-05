package PlatformGameShared.Messages.Response;

import PlatformGameShared.Enums.GameResponseMessageType;

public class PlatformGameResponseMessageLobbyNames extends PlatformGameResponseMessage {

    private String[] names;


    public PlatformGameResponseMessageLobbyNames(String[] names) {
        this.setResponseMessageType(GameResponseMessageType.LobbyNameChange);
        this.names = names;
    }

    public String[] getNames() {
        return names;
    }
}
