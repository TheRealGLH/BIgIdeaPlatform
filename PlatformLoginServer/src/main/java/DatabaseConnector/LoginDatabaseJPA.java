package DatabaseConnector;

import DatabaseConnector.jpa.GameRepository;
import DatabaseConnector.jpa.PlayerRepository;
import DatabaseConnector.jpa.model.Game;
import DatabaseConnector.jpa.model.Player;
import PlatformGameShared.Enums.LoginState;
import PlatformGameShared.Enums.RegisterState;
import PlatformGameShared.PlatformLogger;
import RESTObjects.GameData;
import interfaces.ILoginDatabaseConnector;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

public class LoginDatabaseJPA implements ILoginDatabaseConnector {

    private PlayerRepository playerRepository;
    private GameRepository gameRepository;
    private static LoginDatabaseJPA instance;

    LoginDatabaseJPA(PlayerRepository playerRepository, GameRepository gameRepository) {
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
    }

    public static LoginDatabaseJPA getInstance(PlayerRepository playerRepository, GameRepository gameRepository) {
        if (instance == null) instance = new LoginDatabaseJPA(playerRepository, gameRepository);
        return instance;
    }

    @Override
    public LoginState loginPlayer(String name, String password) {
        try {
            if(!playerRepository.existsById(name)) return LoginState.INCORRECTDATA;
            Player player = playerRepository.getOne(name);
            if ( !player.getPassword().equals(password)) {
                return LoginState.INCORRECTDATA;
            }
            if (player.isBanned()) return LoginState.BANNED;
            else return LoginState.SUCCESS;
        } catch (Exception e) {
            PlatformLogger.Log(Level.WARNING, "Error logging in: " + name + " " + e.getClass().getName() + ":" + e.getMessage());
            e.printStackTrace();
            return LoginState.ERROR;
        }
    }

    @Override
    public RegisterState registerPlayer(String name, String password) {
        try {
            if (name.length() < 6 && password.length() < 8) return RegisterState.INCORRECTDATA;
            Player player;
            if (playerRepository.existsById(name)) {
                return RegisterState.ALREADYEXISTS;
            }
            player = new Player(name, password);
            playerRepository.save(player);
            return RegisterState.SUCCESS;
        } catch (Exception e) {
            PlatformLogger.Log(Level.WARNING, "Error registering: " + name + " " + e.getClass().getName() + ":" + e.getMessage());
            return RegisterState.ERROR;
        }
    }

    @Override
    public void addGame(String map, String victor, String[] players) {
        Set<Player> playerSet = new HashSet<>();
        for (String player : players) {
            playerSet.add(playerRepository.getOne(player));
        }
        Game game = new Game(map, playerSet, victor);
        gameRepository.save(game);
    }

    @Override
    public GameData getGameData(int ID) {
        GameData gameData = new GameData();
        Game game = gameRepository.getOne(ID);
        gameData.setWinnerName(game.getVictor());
        gameData.setMapName(game.getMap());
        int arraySize = game.getPlayer().size();
        String[] players = new String[arraySize];
        int i = 0;
        for (Player player : game.getPlayer()) {
            players[i] = player.getName();
            i++;
        }
        gameData.setPlayerNames(players);
        return gameData;
    }

    @Override
    public String[] getPlayersInMatch(int ID) {
        Game game = gameRepository.getOne(ID);
        String[] players = new String[game.getPlayer().size()];
        int i = 0;
        for (Player player : game.getPlayer()) {
            players[i] = player.getName();
            i++;
        }
        return players;
    }

    @Override
    public int getPlayerWins(String name) {
        throw new UnsupportedOperationException("The method getPlayerWins has not yet been implemented");
    }

    @Override
    public int[] getPlayerMatchIds(String name) {
        throw new UnsupportedOperationException("The method getPlayerMatchIds has not yet been implemented");
    }

    @Override
    public void resetData() {
        playerRepository.deleteAll();
        gameRepository.deleteAll();
    }
}
