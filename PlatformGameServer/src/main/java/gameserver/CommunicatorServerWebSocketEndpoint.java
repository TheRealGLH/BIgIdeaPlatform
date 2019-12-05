package gameserver;

import PlatformGameShared.Enums.GameClientMessageType;
import PlatformGameShared.Interfaces.IPlatformGameClient;
import PlatformGameShared.Interfaces.IPlatformGameServer;
import PlatformGameShared.Messages.Client.*;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

// https://github.com/jetty-project/embedded-jetty-websocket-examples/tree/master/javax.websocket-example/src/main/java/org/eclipse/jetty/demo

/**
 * Server-side implementation of Communicator using WebSockets for communication.
 * <p>
 * This code is based on example code from:
 * https://github.com/jetty-project/embedded-jetty-websocket-examples/blob/
 * master/javax.websocket-example/src/main/java/org/eclipse/jetty/
 * demo/EventServerSocket.java
 *
 * @author Nico Kuijpers, based on example project
 */

@ServerEndpoint(value = "/communicator/")
public class CommunicatorServerWebSocketEndpoint {

    // All sessions
    private static final List<Session> sessions = new ArrayList<>();
    private Map<Session, IPlatformGameClient> sessionIPlatformGameClientMap = new HashMap<>();

    // Map each property to list of sessions that are subscribed to that property
    IPlatformGameServer gameServer = new GameServer();

    @OnOpen
    public void onConnect(Session session) {
        System.out.println("[WebSocket Connected] SessionID: " + session.getId());
        String message = String.format("[New client with client side session ID]: %s", session.getId());
        IPlatformGameClient responseClient = new GameServerMessageSender(session);
        sessionIPlatformGameClientMap.put(session, responseClient);
        System.out.println("[#sessions]: " + sessionIPlatformGameClientMap.size());
    }

    @OnMessage
    public void onText(String message, Session session) {
        System.out.println("[WebSocket Session ID] : " + session.getId() + " [Received] : " + message);
        handleMessageFromClient(message, session);
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        System.out.println("[WebSocket Session ID] : " + session.getId() + " [Socket Closed]: " + reason);
        sessionIPlatformGameClientMap.remove(session);
    }

    @OnError
    public void onError(Throwable cause, Session session) {
        System.out.println("[WebSocket Session ID] : " + session.getId() + "[ERROR]: ");
        cause.printStackTrace(System.err);
    }

    // Handle incoming message from client
    private void handleMessageFromClient(String jsonMessage, Session session) {
        Gson gson = new Gson();
        GameClientMessageType messageType = gson.fromJson(jsonMessage, PlatformGameMessage.class).getMessageType();
        IPlatformGameClient client = sessionIPlatformGameClientMap.get(session);
        switch (messageType) {
            case Login:
                PlatformGameMessageLogin messageLogin = gson.fromJson(jsonMessage, PlatformGameMessageLogin.class);
                gameServer.loginPlayer(messageLogin.getName(), messageLogin.getPassword(), client);
                break;
            case Register:
                PlatformGameMessageRegister messageRegister = gson.fromJson(jsonMessage, PlatformGameMessageRegister.class);
                gameServer.registerPlayer(messageRegister.getName(), messageRegister.getPassword(), client);
                break;
            case Input:
                PlatformGameMessageInput messageInput = gson.fromJson(jsonMessage, PlatformGameMessageInput.class);
                gameServer.receiveInput(messageInput.getInputType(), client);
                break;
            case StartGame:
                PlatformGameMessageStart messageStart = gson.fromJson(jsonMessage, PlatformGameMessageStart.class);
                gameServer.startGame();
                break;
        }

    }
}