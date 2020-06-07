package PlatformGameShared.Messages.Response;

import PlatformGameShared.Enums.GameResponseMessageType;
import PlatformGameShared.Points.GameStateEvent;

public class PlatformGameResponseMessageGameState extends PlatformGameResponseMessage {

    private GameStateEvent gameStateEvent;

    public PlatformGameResponseMessageGameState(GameStateEvent gameStateEvent) {
        super();
        this.setResponseMessageType(GameResponseMessageType.GameState);
        this.gameStateEvent = gameStateEvent;
    }

    public PlatformGameResponseMessageGameState(){}

    public GameStateEvent getGameStateEvent() {
        return gameStateEvent;
    }

    public void setGameStateEvent(GameStateEvent gameStateEvent) {
        this.gameStateEvent = gameStateEvent;
    }
}
