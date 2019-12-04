package gameclient;

import Enums.InputType;
import Interfaces.IPlatformGameClient;
import Interfaces.IPlatformGameServer;
import SharedClasses.SpriteUpdate;
import models.classes.GameTimerTask;

import java.util.List;
import java.util.Timer;

/**
 * The Game container, will be controlled by an instance of IPlatformGameClient (later via websockets and
 * send it to an instance of this Interface (which will later contain websockets)
 */
public class GameServer implements IPlatformGameServer {
    GameTimerTask gameTimerTask;
    IPlatformGameClient platformGameClient; //feedback
    Timer timer = new Timer();

    @Override
    public void registerPlayer(String name, String password, IPlatformGameClient client) {

    }

    @Override
    public void loginPlayer(String name, String password, IPlatformGameClient client) {
        System.out.println("[GameServer.java] Registering " + name);
        this.platformGameClient = client;
    }

    @Override
    public void startGame() {
        gameTimerTask = new GameTimerTask(this);
        timer.schedule(gameTimerTask, 1000, GameTimerTask.tickRate);
    }

    @Override
    public synchronized void receiveInput(InputType type) {
        gameTimerTask.sendInput(type);
    }

    @Override
    public void sendSpriteUpdates(List<SpriteUpdate> spriteUpdateList) {
        if (spriteUpdateList.size() > 0) {
            System.out.println("[GameServer.java] Sending updates: " + spriteUpdateList);
            if(platformGameClient != null) platformGameClient.updateScreen(spriteUpdateList);
        }

    }
}
