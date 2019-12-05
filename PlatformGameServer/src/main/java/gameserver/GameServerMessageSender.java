package gameserver;

import PlatformGameShared.Enums.GameState;
import PlatformGameShared.Enums.InputType;
import PlatformGameShared.Enums.LoginState;
import PlatformGameShared.Enums.RegisterState;
import PlatformGameShared.Interfaces.IPlatformGameClient;
import PlatformGameShared.Points.SpriteUpdate;

import javax.websocket.Session;
import java.util.List;

public class GameServerMessageSender implements IPlatformGameClient {

    private int playerNr;
    private String name;
    private Session session;

    public GameServerMessageSender(Session session){
        this.session = session;
    }

    @Override
    public void updateScreen(List<SpriteUpdate> positions) {
        throw new UnsupportedOperationException("Method updateScreen has not yet been implemented.");
    }

    @Override
    public void joinGame() {
        throw new UnsupportedOperationException("Method joinGame has not yet been implemented.");
    }

    @Override
    public void receiveGameState(GameState gameState) {
        throw new UnsupportedOperationException("Method receiveGameState has not yet been implemented.");
    }

    @Override
    public void sendInput(InputType inputType) {
        throw new UnsupportedOperationException("Method sendInput has not yet been implemented.");
    }

    @Override
    public List<SpriteUpdate> getAllSprites() {
        throw new UnsupportedOperationException("Method getAllSprites has not yet been implemented.");
    }

    @Override
    public void sendRegisterRequest(String name, String password) {
        throw new UnsupportedOperationException("Method sendRegisterRequest has not yet been implemented.");
    }

    @Override
    public void sendLoginRequest(String name, String password) {
        throw new UnsupportedOperationException("Method sendLoginRequest has not yet been implemented.");
    }

    @Override
    public void receiveLoginState(String name, LoginState loginState) {
        throw new UnsupportedOperationException("Method receiveLoginState has not yet been implemented.");
    }

    @Override
    public void receiveRegisterState(String name, RegisterState registerState) {
        throw new UnsupportedOperationException("Method receiveRegisterState has not yet been implemented.");
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

}
