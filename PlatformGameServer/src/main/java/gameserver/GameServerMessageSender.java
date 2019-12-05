package gameserver;

import PlatformGameShared.Enums.GameState;
import PlatformGameShared.Enums.InputType;
import PlatformGameShared.Enums.LoginState;
import PlatformGameShared.Enums.RegisterState;
import PlatformGameShared.Interfaces.IPlatformGameClient;
import PlatformGameShared.Messages.Response.*;
import PlatformGameShared.Points.SpriteUpdate;
import com.google.gson.Gson;

import javax.websocket.Session;
import java.util.List;

public class GameServerMessageSender implements IPlatformGameClient {

    private int playerNr;
    private String name;
    private Session session;
    private Gson gson = new Gson();

    public GameServerMessageSender(Session session) {
        this.session = session;
    }

    @Override
    public void updateScreen(List<SpriteUpdate> positions) {
        PlatformGameResponseMessageSpriteUpdate messageSpriteUpdate = new PlatformGameResponseMessageSpriteUpdate(positions);
        sendMessage(messageSpriteUpdate);
    }

    @Override
    public void joinGame() {
        throw new UnsupportedOperationException("This method is not supposed to be called from the server!");
    }

    @Override
    public void receiveGameState(GameState gameState) {
        PlatformGameResponseMessageGameState messageGameState = new PlatformGameResponseMessageGameState(gameState);
    }

    @Override
    public void sendInput(InputType inputType) {
        throw new UnsupportedOperationException("This method is not supposed to be called from the server!");
    }

    @Override
    public List<SpriteUpdate> getAllSprites() {
        throw new UnsupportedOperationException("This method is not supposed to be called from the server!");
    }

    @Override
    public void sendRegisterRequest(String name, String password) {
        throw new UnsupportedOperationException("This method is not supposed to be called from the server!");
    }

    @Override
    public void sendLoginRequest(String name, String password) {
        throw new UnsupportedOperationException("This method is not supposed to be called from the server!");
    }

    @Override
    public void receiveLoginState(String name, LoginState loginState) {
        PlatformGameResponseMessageLogin messageLogin = new PlatformGameResponseMessageLogin(loginState, name);
    }

    @Override
    public void receiveRegisterState(String name, RegisterState registerState) {
        PlatformGameResponseMessageRegister messageRegister = new PlatformGameResponseMessageRegister(registerState, name);
    }

    @Override
    public int getPlayerNr() {
        return playerNr;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setPlayerNr(int playerNr) {
        this.playerNr = playerNr;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void gameStartNotification() {
        throw new UnsupportedOperationException("Method gameStartNotification has not yet been implemented.");
    }

    @Override
    public void lobbyJoinedNotify(String[] playerNames) {
        throw new UnsupportedOperationException("Method lobbyJoinedNotify has not yet been implemented.");
    }


    private void sendMessage(PlatformGameResponseMessage responseMessage) {
        String json = gson.toJson(responseMessage);
        session.getAsyncRemote().sendText(json);
    }

}
