package PlatformGameShared.Points;

import PlatformGameShared.Enums.GameState;

public class GameStateEvent {
    private GameState state;
    //The name of the player who caused the event
    private String name;

    public GameStateEvent(GameState state, String name) {
        this.state = state;
        this.name = name;
    }

    public GameStateEvent() {

    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
