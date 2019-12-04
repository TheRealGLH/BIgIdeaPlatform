package PlatformGameShared.Interfaces;

import PlatformGameShared.Enums.LoginState;
import PlatformGameShared.Enums.RegisterState;

import java.util.HashMap;
import java.util.Map;

public class PlatformLoginClientMock implements IPlatformLoginClient {

    Map<String, String> usernamePasswordMap;

    public PlatformLoginClientMock() {
        usernamePasswordMap = new HashMap<>();
        usernamePasswordMap.put("test", "123456");
    }

    @Override
    public LoginState attemptLogin(String username, String password) {
        LoginState loginState = LoginState.INCORRECTDATA;
        if (usernamePasswordMap.get(username) == password) {
            loginState = LoginState.SUCCESS;
        }
        return loginState;
    }

    @Override
    public int getPlayerWins(String username) {
        return 17;
    }

    @Override
    public RegisterState attemptRegistration(String username, String password) {
        RegisterState registerState = RegisterState.ALREADYEXISTS;
        if (username.length() <= 3 && password.length() < 6) {
            registerState = RegisterState.INCORRECTDATA;
            return registerState;
        }
        if (usernamePasswordMap.get(username) == null) {
            registerState = RegisterState.SUCCESS;
            usernamePasswordMap.put(username,password);
        }
        return registerState;
    }
}
