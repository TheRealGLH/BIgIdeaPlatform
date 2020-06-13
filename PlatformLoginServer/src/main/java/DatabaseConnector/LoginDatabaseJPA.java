package DatabaseConnector;

import DatabaseConnector.jpa.PlayerRepository;
import DatabaseConnector.jpa.model.Player;
import PlatformGameShared.Enums.LoginState;
import PlatformGameShared.Enums.RegisterState;
import RESTObjects.GameData;
import interfaces.ILoginDatabaseConnector;

public class LoginDatabaseJPA implements ILoginDatabaseConnector {

    private PlayerRepository playerRepository;
    private static LoginDatabaseJPA instance;

    LoginDatabaseJPA (PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    public static LoginDatabaseJPA getInstance(PlayerRepository playerRepository) {
        if (instance == null) instance = new LoginDatabaseJPA(playerRepository);
        return instance;
    }

    @Override
    public LoginState loginPlayer(String name, String password) {
        throw new UnsupportedOperationException("The method <> has not yet been implemented");
    }

    @Override
    public RegisterState registerPlayer(String name, String password) {
        Player player = new Player(name,password);
        playerRepository.save(player);
        return RegisterState.SUCCESS;
    }

    @Override
    public void addGame(String map, String victor, String[] players) {
        throw new UnsupportedOperationException("The method <> has not yet been implemented");
    }

    @Override
    public GameData getGameData(int ID) {
        throw new UnsupportedOperationException("The method <> has not yet been implemented");
    }

    @Override
    public String[] getPlayersInMatch(int ID) {
        throw new UnsupportedOperationException("The method <> has not yet been implemented");
    }

    @Override
    public int getPlayerWins(String name) {
        throw new UnsupportedOperationException("The method <> has not yet been implemented");
    }

    @Override
    public int[] getPlayerMatchIds(String name) {
        throw new UnsupportedOperationException("The method <> has not yet been implemented");
    }

    @Override
    public void resetData() {
        throw new UnsupportedOperationException("The method <> has not yet been implemented");
    }
}
