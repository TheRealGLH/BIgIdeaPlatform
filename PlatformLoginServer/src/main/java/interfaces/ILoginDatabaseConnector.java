package interfaces;

import PlatformGameShared.Enums.LoginState;
import PlatformGameShared.Enums.RegisterState;
import RESTObjects.GameData;

public interface ILoginDatabaseConnector {

    public LoginState loginPlayer(String name, String password);

    public RegisterState registerPlayer(String name, String password);

    public void addGame(String map, String victor, String[] players);

    public GameData getGameData(int ID);

    public String[] getPlayersInMatch(int ID);

    public int getPlayerWins(String name);

    public int[] getPlayerMatchIds(String name);

    public void resetData();



}
