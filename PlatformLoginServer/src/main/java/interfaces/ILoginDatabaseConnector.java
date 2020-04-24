package interfaces;

import PlatformGameShared.Enums.LoginState;
import PlatformGameShared.Enums.RegisterState;

public interface ILoginDatabaseConnector {

    public LoginState loginPlayer(String name, String password);

    public RegisterState registerPlayer(String name, String password);

    public void AddGame(String map, String victor, String[] players);

    public void resetData();


}
