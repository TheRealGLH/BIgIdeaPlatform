package PlatformGameShared.Messages.Response;

import PlatformGameShared.Enums.GameResponseMessageType;

public class PlatformGameResponseMessageLobbyMapNames extends PlatformGameResponseMessage {

    private String[] mapNames;

    public PlatformGameResponseMessageLobbyMapNames(String[] mapNames) {
        super();
        this.setResponseMessageType(GameResponseMessageType.LobbyMapList);
        this.mapNames = mapNames;
    }

    public PlatformGameResponseMessageLobbyMapNames() {
    }

    public String[] getMapNames() {
        return mapNames;
    }

    public void setMapNames(String[] mapNames) {
        this.mapNames = mapNames;
    }


}
