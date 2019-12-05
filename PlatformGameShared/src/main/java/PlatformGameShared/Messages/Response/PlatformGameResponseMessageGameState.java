package PlatformGameShared.Messages.Response;

import PlatformGameShared.Enums.GameResponseMessageType;
import PlatformGameShared.Enums.GameState;

public class PlatformGameResponseMessageGameState extends PlatformGameResponseMessage {

    private GameState gameState;

    public PlatformGameResponseMessageGameState(GameState gameState) {
        this.setResponseMessageType(GameResponseMessageType.GameState);
        this.gameState = gameState;
    }

    public PlatformGameResponseMessageGameState(){}

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
