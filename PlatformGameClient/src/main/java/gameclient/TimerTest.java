package gameclient;

import Enums.InputType;
import models.classes.GameTimerTask;

import java.util.Timer;

class TimerTest {
    public static void main(String[] args) throws InterruptedException
    {
        GameServer gameServer = new GameServer();
        gameServer.startGame();
        Thread.sleep(500);
        gameServer.receiveInput(InputType.MOVELEFT);
    }
}