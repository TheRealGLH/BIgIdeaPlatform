package PlatformGameShared.Messages.Response;

import PlatformGameShared.Enums.GameResponseMessageType;

public class PlatformGameResponseMessageLobbyNames extends PlatformGameResponseMessage {

    private String[] names;


    public PlatformGameResponseMessageLobbyNames(String[] names) {
        this.setResponseMessageType(GameResponseMessageType.LobbyNameChange);
        this.names = names;
    }

    public PlatformGameResponseMessageLobbyNames(){}

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }
}
