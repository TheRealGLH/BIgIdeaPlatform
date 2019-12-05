package PlatformGameShared.Messages.Response;

import PlatformGameShared.Enums.GameResponseMessageType;

public class PlatformGameResponseMessageLobbyMap extends PlatformGameResponseMessage{

    private String mapName;

    public PlatformGameResponseMessageLobbyMap(String mapName) {
        this.setResponseMessageType(GameResponseMessageType.LobbyMapChange);
        this.mapName = mapName;
    }

    public PlatformGameResponseMessageLobbyMap(){}

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }
}
