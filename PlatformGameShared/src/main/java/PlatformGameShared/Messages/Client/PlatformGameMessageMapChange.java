package PlatformGameShared.Messages.Client;

import PlatformGameShared.Enums.GameClientMessageType;

public class PlatformGameMessageMapChange extends PlatformGameMessage {

    private String mapName;

    public PlatformGameMessageMapChange(String mapName) {
        this.setMessageType(GameClientMessageType.MapChange);
        this.mapName = mapName;
    }

    public PlatformGameMessageMapChange() {
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }
}
