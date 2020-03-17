package PlatformGameShared.Messages.Client;

import PlatformGameShared.Enums.GameClientMessageType;

public class PlatformGameMessageMapSelect extends PlatformGameMessage {

    private String mapName;

    public PlatformGameMessageMapSelect(String mapName) {
        this.setMessageType(GameClientMessageType.Input);
        this.mapName = mapName;
    }

    public PlatformGameMessageMapSelect() {
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }
}
