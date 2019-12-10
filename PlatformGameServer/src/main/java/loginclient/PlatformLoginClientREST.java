package loginclient;

import PlatformGameShared.Enums.LoginState;
import PlatformGameShared.Enums.RegisterState;

public class PlatformLoginClientREST implements IPlatformLoginClient {
    @Override
    public LoginState attemptLogin(String username, String password) {
        throw new UnsupportedOperationException("method attemptLogin has not yet been implemented");
    }

    @Override
    public int getPlayerWins(String username) {
        throw new UnsupportedOperationException("method attemptLogin has not yet been implemented");
    }

    @Override
    public RegisterState attemptRegistration(String username, String password) {
        throw new UnsupportedOperationException("The method attemptRegistration has not yet been implemented");
    }


}
