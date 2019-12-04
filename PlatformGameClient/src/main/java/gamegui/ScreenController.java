package gamegui;

import PlatformGameShared.Interfaces.IPlatformGameClient;
import gamegui.Interfaces.ILobbyEventPublisher;
import gamegui.Interfaces.ISpriteUpdateEventPublisher;

public abstract class ScreenController implements IPlatformGameClient, ISpriteUpdateEventPublisher, ILobbyEventPublisher {
}
