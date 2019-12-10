package gameserver;

import PlatformGameShared.Enums.InputType;
import PlatformGameShared.Enums.LoginState;
import PlatformGameShared.Enums.RegisterState;
import PlatformGameShared.Interfaces.IPlatformGameClient;
import PlatformGameShared.Interfaces.IPlatformGameServer;
import loginclient.IPlatformLoginClient;
import loginclient.PlatformLoginClientMock;
import PlatformGameShared.Points.SpriteUpdate;
import models.classes.GameTimerTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * The Game container, will be controlled by an instance of IPlatformGameClient (later via websockets and
 * send it to an instance of this Interface (which will later contain websockets)
 */
public class GameServer implements IPlatformGameServer {
    GameTimerTask gameTimerTask;
    List<IPlatformGameClient> joinedClients;//clients which  are connected
    IPlatformLoginClient loginClient; //REST communicator
    int minAmountOfPlayers = 1;
    Timer timer = new Timer();

    public GameServer() {
        loginClient = new PlatformLoginClientMock();
        joinedClients = new ArrayList<>();
    }

    @Override
    public void registerPlayer(String name, String password, IPlatformGameClient client) {
        RegisterState registerState = loginClient.attemptRegistration(name, password);
        System.out.println("[GameServer.java] Attempt to register " + name + ". state: " + registerState);
        client.receiveRegisterState(name, registerState);
    }

    @Override
    public void loginPlayer(String name, String password, IPlatformGameClient client) {
        System.out.println("[GameServer.java] Logging in as " + name + " " + this.toString());
        LoginState loginState = loginClient.attemptLogin(name, password);

        client.receiveLoginState(name, loginState);
        if (loginState == LoginState.SUCCESS) {
            client.setName(name);
            joinedClients.add(client);
            String names[] = new String[joinedClients.size()];
            for (int i = 0; i < joinedClients.size(); i++) {
                names[i] = joinedClients.get(i).getName();
            }
            for (IPlatformGameClient platformGameClient : joinedClients) {
                platformGameClient.lobbyJoinedNotify(names);
            }
        }
    }

    @Override
    public void startGame(IPlatformGameClient platformGameClient) {
        if (joinedClients.contains(platformGameClient)) {
            if (joinedClients.size() >= minAmountOfPlayers) {
                int size = joinedClients.size();
                String[] names = new String[size];
                int[] playerNrs = new int[size];
                for (int i = 0; i < size; i++) {
                    names[i] = joinedClients.get(i).getName();
                    playerNrs[i] = joinedClients.get(i).getPlayerNr();
                }
                gameTimerTask = new GameTimerTask(this, playerNrs, names);
                for (IPlatformGameClient joinedClient : joinedClients) joinedClient.gameStartNotification();
                //TODO send stuff to the GameTimerTask, like the map perhaps
                timer.schedule(gameTimerTask, 1000, GameTimerTask.tickRate);
            } else {
                System.out.println("[GameServer.java] A request to start the game was denied because of too few players");
            }
        }
    }

    @Override
    public synchronized void receiveInput(InputType type, IPlatformGameClient client) {
        if (joinedClients.contains(client)) {
            gameTimerTask.sendInput(client.getPlayerNr(), type);
        }
    }

    @Override
    public void sendSpriteUpdates(List<SpriteUpdate> spriteUpdateList) {
        if (spriteUpdateList.size() > 0) {
            System.out.println("[GameServer.java] Sending updates: " + spriteUpdateList);
            for (IPlatformGameClient platformGameClient : joinedClients) {
                platformGameClient.updateScreen(spriteUpdateList);
            }
        }

    }

    @Override
    public void sendInputRequest() {
        for (IPlatformGameClient platformGameClient : joinedClients) {
            platformGameClient.receiveAllowInput();
        }
    }
}
