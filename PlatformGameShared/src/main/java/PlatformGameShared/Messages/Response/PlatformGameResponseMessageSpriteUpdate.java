package PlatformGameShared.Messages.Response;

import PlatformGameShared.Enums.GameResponseMessageType;
import PlatformGameShared.Points.SpriteUpdate;

import java.util.List;

public class PlatformGameResponseMessageSpriteUpdate extends PlatformGameResponseMessage {

    private List<SpriteUpdate> spriteUpdates;

    public PlatformGameResponseMessageSpriteUpdate(List<SpriteUpdate> spriteUpdates) {
        this.setResponseMessageType(GameResponseMessageType.SpriteUpdate);
        this.spriteUpdates = spriteUpdates;
    }

    public PlatformGameResponseMessageSpriteUpdate(){}

    public List<SpriteUpdate> getSpriteUpdates() {
        return spriteUpdates;
    }

    public void setSpriteUpdates(List<SpriteUpdate> spriteUpdates) {
        this.spriteUpdates = spriteUpdates;
    }
}
