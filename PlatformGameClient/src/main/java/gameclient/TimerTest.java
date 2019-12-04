package gameclient;

import PlatformGameShared.Enums.InputType;
import gameserver.GameServer;

class TimerTest {
    public static void main(String[] args) throws InterruptedException
    {
        GameServer gameServer = new GameServer();
        gameServer.startGame();
        Thread.sleep(500);
        gameServer.receiveInput(InputType.MOVELEFT,null);
    }
}