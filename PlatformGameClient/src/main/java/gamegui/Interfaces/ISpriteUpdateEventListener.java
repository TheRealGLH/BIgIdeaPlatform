package gamegui.Interfaces;

import PlatformGameShared.Points.SpriteUpdate;

import java.util.List;

public interface ISpriteUpdateEventListener {

    void handleSpriteUpdate(List<SpriteUpdate> spriteUpdates);

    void allowSendInput();
}
