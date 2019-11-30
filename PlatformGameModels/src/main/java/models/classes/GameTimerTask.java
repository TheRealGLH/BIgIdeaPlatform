package models.classes;

import models.classes.Game;

import java.util.TimerTask;

public class GameTimerTask extends TimerTask {

    Game game;

    public GameTimerTask(){
        game = new Game();
        game.setUpGame();
    }

    @Override
    public void run() {
        game.updateState();
    }
}
