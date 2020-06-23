package models.classes;

import PlatformGameShared.Enums.GameState;
import PlatformGameShared.Enums.InputType;
import PlatformGameShared.Interfaces.IPlatformGameServer;
import PlatformGameShared.PlatformLogger;
import PlatformGameShared.Points.GameLevel;
import PlatformGameShared.Points.GameStateEvent;
import PlatformGameShared.PropertiesLoader;

import java.util.Date;
import java.util.TimerTask;
import java.util.logging.Level;

public class GameTimerTask extends TimerTask {

    public static final int tickRate = Integer.parseInt(PropertiesLoader.getPropValues("game.tickrate", "game.properties"));
    Game game;
    IPlatformGameServer gameServer;
    private GameStateEvent mostRecentGameState = new GameStateEvent(GameState.STARTED, "server");

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
        Date beginTick = new Date();
        Date endTick;
        game.updateState();
        gameServer.sendSpriteUpdates(game.getSpriteUpdates());
        // gameServer.sendInputRequest();
        GameStateEvent currentGameState = game.getGameStateEvent();
        //TODO this ought to be done with an update interface of some sorts.
        if (currentGameState != null && !currentGameState.equals(mostRecentGameState)) {
            mostRecentGameState = currentGameState;
            gameServer.sendGameStateEvent(mostRecentGameState);
        }
        endTick = new Date();
        PlatformLogger.Log(Level.FINEST, "Tick took " + (endTick.getTime() - beginTick.getTime()), endTick);
    }


    public synchronized void sendInput(int playerNr, InputType inputType) {
        game.sendInput(playerNr, inputType);
    }
}
