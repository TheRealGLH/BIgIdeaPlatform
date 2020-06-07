package models.classes;

import PlatformGameShared.Enums.InputType;
import PlatformGameShared.Interfaces.IPlatformGameServer;
import PlatformGameShared.Points.GameLevel;
import PlatformGameShared.Points.GameStateEvent;
import PlatformGameShared.PropertiesLoader;

import java.util.TimerTask;

public class GameTimerTask extends TimerTask {

    public static final int tickRate = Integer.parseInt(PropertiesLoader.getPropValues("game.tickrate", "game.properties"));
    Game game;
    IPlatformGameServer gameServer;
    private GameStateEvent mostRecentGameState;

    /**
     * @param gameServer The game server that we send updates back to
     */
    public GameTimerTask(IPlatformGameServer gameServer, int[] playerNrs, String[] names, GameLevel gameLevel) {
        this.gameServer = gameServer;
        game = new Game();
        game.setUpGame(playerNrs, names, gameLevel);
    }


    @Override
    public void run() {
        Thread.currentThread().setName("Game Timer");
        game.updateState();
        gameServer.sendSpriteUpdates(game.getSpriteUpdates());
        // gameServer.sendInputRequest();
        GameStateEvent currentGameState = game.getGameStateEvent();
        if (!currentGameState.equals(mostRecentGameState)) {
            mostRecentGameState = currentGameState;
                    gameServer.sendGameStateEvent(mostRecentGameState);
        }
    }


    public synchronized void sendInput(int playerNr, InputType inputType) {
        game.sendInput(playerNr, inputType);
    }
}
