package models.classes;

import Enums.InputType;
import Interfaces.IPlatformGameServer;
import models.classes.Game;

import java.util.TimerTask;

public class GameTimerTask extends TimerTask {

    Game game;
    IPlatformGameServer gameServer;

    /**
     * @param gameServer The game server that we send updates back to
     */
    public GameTimerTask(IPlatformGameServer gameServer) {
        this.gameServer = gameServer;
        game = new Game();
        game.setUpGame();
        this.gameServer.sendSpriteUpdates(game.getSpriteUpdates());
    }

    @Override
    public void run() {
        game.updateState();
        gameServer.sendSpriteUpdates(game.getSpriteUpdates());
    }

    public synchronized void sendInput(InputType inputType) {
        System.out.println("[GameTimerTask.java] " + inputType);
        game.sendInput(1, inputType);
    }
}
