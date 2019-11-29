package gamegui.Interfaces;

import SharedClasses.SpriteUpdate;

import java.util.List;

public interface ISpriteUpdateEventListener {

    void handleSpriteUpdate(List<SpriteUpdate> spriteUpdates);
}
