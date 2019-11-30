package gameclient;

import models.classes.GameTimerTask;

import java.util.Timer;

class TimerTest {
    public static void main(String[] args) throws InterruptedException
    {
        Timer timer = new Timer();
        timer.schedule(new GameTimerTask(), 0, 100);
    }
}
