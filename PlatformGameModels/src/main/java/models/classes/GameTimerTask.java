package models.classes;

import PlatformGameShared.Enums.InputType;
import PlatformGameShared.Interfaces.IPlatformGameServer;

import java.util.TimerTask;

public class GameTimerTask extends TimerTask {

    public static final int tickRate = 30;
    Game game;
    IPlatformGameServer gameServer;

    /**
     * @param gameServer The game server that we send updates back to
     */
    public GameTimerTask(IPlatformGameServer gameServer) {
        this.gameServer = gameServer;
        game = new Game();
        game.setUpGame();
    }

    @Override
    public void run() {
        game.updateState();
        gameServer.sendSpriteUpdates(game.getSpriteUpdates());
    }

    public synchronized void sendInput(int playerNr, InputType inputType) {
        game.sendInput(playerNr, inputType);
    }
}
