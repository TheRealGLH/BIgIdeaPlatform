package PlatformGameShared.Messages.Response;

import PlatformGameShared.Enums.GameResponseMessageType;

public class PlatformGameResponseMessageLobbyMapChange extends PlatformGameResponseMessage {

    private String mapName;

    public PlatformGameResponseMessageLobbyMapChange(String mapName) {
        this.setResponseMessageType(GameResponseMessageType.LobbyMapChange);
        this.mapName = mapName;
    }

    public PlatformGameResponseMessageLobbyMapChange() {
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }
}
