package models.classes;

import PlatformGameShared.Enums.InputType;
import PlatformGameShared.Interfaces.IPlatformGameServer;
import PlatformGameShared.Points.GameLevel;
import PlatformGameShared.PropertiesLoader;

import java.util.TimerTask;

public class GameTimerTask extends TimerTask {

    public static final int tickRate = Integer.parseInt(PropertiesLoader.getPropValues("game.tickrate","game.properties"));
    Game game;
    IPlatformGameServer gameServer;

    /**
     * @param gameServer The game server that we send updates back to
     */
    public GameTimerTask(IPlatformGameServer gameServer, int[] playerNrs, String[] names) {
        this.gameServer = gameServer;
        game = new Game();
        game.setUpGame(playerNrs, names);
    }

    public GameTimerTask(IPlatformGameServer gameServer, int[] playerNrs, String[] names, GameLevel gameLevel) {
        this.gameServer = gameServer;
        game = new Game();
        game.setUpGame(playerNrs, names, gameLevel);
    }

    @Override
    public void run() {
        game.updateState();
        gameServer.sendSpriteUpdates(game.getSpriteUpdates());
        // gameServer.sendInputRequest();
    }

    public synchronized void sendInput(int playerNr, InputType inputType) {
        game.sendInput(playerNr, inputType);
    }
}
