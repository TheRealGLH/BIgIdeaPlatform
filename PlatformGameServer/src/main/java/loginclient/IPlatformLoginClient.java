package loginclient;

import PlatformGameShared.Enums.LoginState;
import PlatformGameShared.Enums.RegisterState;

public interface IPlatformLoginClient {

    public LoginState attemptLogin(String username, String password);

    public int getPlayerWins(String username);

    public RegisterState attemptRegistration(String username, String password);

    public String getLevelNames();

    public String getLevelContent(String levelname);

}
