package interfaces;

import PlatformGameShared.Enums.LoginState;
import PlatformGameShared.Enums.RegisterState;
import io.swagger.model.PlayerLoginResponse;

public interface ILoginDatabaseConnector {

    public LoginState loginPlayer(String name, String password);

    public RegisterState registerPlayer(String name, String password);




}
