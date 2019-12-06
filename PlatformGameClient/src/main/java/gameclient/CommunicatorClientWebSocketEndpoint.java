package gameclient;

import PlatformGameShared.Enums.GameResponseMessageType;
import PlatformGameShared.Interfaces.IPlatformGameClient;
import PlatformGameShared.Messages.Client.PlatformGameMessage;
import PlatformGameShared.Messages.Response.*;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@ClientEndpoint
public class CommunicatorClientWebSocketEndpoint implements ICommunicator {

    // Singleton
    private static CommunicatorClientWebSocketEndpoint instance = null;

    /**
     * The local websocket uri to connect to.
     */
    private final String uri = "ws://localhost:8095/platform/";

    private Session session;

    private String message;

    private Gson gson = null;

    private IPlatformGameClient platformGameClient;

    // Status of the webSocket client
    boolean isRunning = false;

    // Private constructor (singleton pattern)
    private CommunicatorClientWebSocketEndpoint() {
        gson = new Gson();
    }

    /**
     * Get singleton instance of this class.
     * Ensure that only one instance of this class is created.
     *
     * @return instance of client web socket
     */
    public static CommunicatorClientWebSocketEndpoint getInstance() {
        if (instance == null) {
            System.out.println("[WebSocket Client create singleton instance]");
            instance = new CommunicatorClientWebSocketEndpoint();
        }
        return instance;
    }

    /**
     * Start the connection.
     */
    @Override
    public void start() {
        System.out.println("[WebSocket Client start connection]");
        if (!isRunning) {
            startClient();
            isRunning = true;
        }
    }

    @Override
    public void stop() {
        System.out.println("[WebSocket Client stop]");
        if (isRunning) {
            stopClient();
            isRunning = false;
        }
    }

    @OnOpen
    public void onWebSocketConnect(Session session) {
        System.out.println("[WebSocket Client open session] " + session.getRequestURI());
        this.session = session;
    }

    @OnMessage
    public void onWebSocketText(String message, Session session) {
        this.message = message;
        System.out.println("[WebSocket Client message received] " + message);
        processMessage(message);
    }

    @OnError
    public void onWebSocketError(Session session, Throwable cause) {
        System.out.println("[WebSocket Client connection error] " + cause.toString());
    }

    @OnClose
    public void onWebSocketClose(CloseReason reason) {
        System.out.print("[WebSocket Client close session] " + session.getRequestURI());
        System.out.println(" for reason " + reason);
        session = null;
    }


    @Override
    public void sendMessage(PlatformGameMessage message) {
        sendMessageToServer(message);
    }

    @Override
    public void setGameClient(IPlatformGameClient platformGameClient) {
        this.platformGameClient = platformGameClient;
    }


    private void sendMessageToServer(PlatformGameMessage message) {
        String jsonMessage = gson.toJson(message);
        // Use asynchronous communication
        session.getAsyncRemote().sendText(jsonMessage);
    }

    /**
     * Get the latest message received from the websocket communication.
     *
     * @return The message from the websocket communication
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the message, but no action is taken when the message is changed.
     *
     * @param message the new message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Start a WebSocket client.
     */
    private void startClient() {
        System.out.println("[WebSocket Client start]");
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, new URI(uri));

        } catch (IOException | URISyntaxException | DeploymentException ex) {
            // do something useful eventually
            ex.printStackTrace();
        }
    }

    /**
     * Stop the client when it is running.
     */
    private void stopClient() {
        System.out.println("[WebSocket Client stop]");
        try {
            session.close();

        } catch (IOException ex) {
            // do something useful eventually
            ex.printStackTrace();
        }
    }

    // Process incoming json message
    private void processMessage(String jsonMessage) {

        // Parse incoming message
        GameResponseMessageType messageType = null;
        try {
            messageType = gson.fromJson(jsonMessage, PlatformGameResponseMessage.class).getResponseMessageType();
        } catch (JsonSyntaxException ex) {
            System.out.println("[WebSocket Client ERROR: cannot parse Json message " + jsonMessage);
        }
        assert messageType != null;
        try {
            switch (messageType) {

                case LoginState:
                    PlatformGameResponseMessageLogin responseMessageLogin = gson.fromJson(jsonMessage, PlatformGameResponseMessageLogin.class);
                    platformGameClient.receiveLoginState(responseMessageLogin.getName(), responseMessageLogin.getLoginState());
                    break;
                case RegisterState:
                    PlatformGameResponseMessageRegister responseMessageRegister = gson.fromJson(jsonMessage, PlatformGameResponseMessageRegister.class);
                    platformGameClient.receiveRegisterState(responseMessageRegister.getName(), responseMessageRegister.getRegisterState());
                    break;
                case SpriteUpdate:
                    PlatformGameResponseMessageSpriteUpdate responseMessageSpriteUpdate = gson.fromJson(jsonMessage, PlatformGameResponseMessageSpriteUpdate.class);
                    platformGameClient.updateScreen(responseMessageSpriteUpdate.getSpriteUpdates());
                    break;
                case LobbyNameChange:
                    PlatformGameResponseMessageLobbyNames responseMessageLobbyNames = gson.fromJson(jsonMessage, PlatformGameResponseMessageLobbyNames.class);
                    platformGameClient.lobbyJoinedNotify(responseMessageLobbyNames.getNames());
                    break;
                case LobbyMapChange:
                    System.out.println("[WebSocket Client ERROR: handling of Map change events has not been handled " +
                            "from a WebSocket level yet...");
                    break;
                case GameState:
                    PlatformGameResponseMessageGameState messageGameState = gson.fromJson(jsonMessage, PlatformGameResponseMessageGameState.class);
                    platformGameClient.receiveGameState(messageGameState.getGameState());
                    break;
                case NotifyStart:
                    platformGameClient.gameStartNotification();
                    break;
            }
        } catch (JsonSyntaxException ex) {
            System.out.println("WebSocket Client ERROR: Json message " + jsonMessage + " was malformed for the type " + messageType);
        }


    }
}
