package interfaces;

import PlatformGameShared.Enums.LoginState;
import PlatformGameShared.Enums.RegisterState;

import java.util.HashMap;
import java.util.Map;

public class LoginDatabaseConnectorMock implements ILoginDatabaseConnector {

    Map<String, String> usernamePasswordMap;
    static LoginDatabaseConnectorMock  instance = null;

    private LoginDatabaseConnectorMock(){
        usernamePasswordMap = new HashMap<>();
        usernamePasswordMap.put("test", "letmein123");
        usernamePasswordMap.put("fred","letmein123");
    }

    public static LoginDatabaseConnectorMock getInstance() {
        if (instance == null) instance = new LoginDatabaseConnectorMock();
        return instance;
    }

    @Override
    public LoginState loginPlayer(String name, String password) {
        LoginState loginState = LoginState.INCORRECTDATA;
        String pw = usernamePasswordMap.get(name);
        if(pw == null) return loginState;
        if (pw.equals(password)) {
            loginState = LoginState.SUCCESS;
        }
        return loginState;
    }

    @Override
    public RegisterState registerPlayer(String name, String password) {
        RegisterState registerState = RegisterState.ALREADYEXISTS;
        if (name.length() <= 3 && password.length() < 6) {
            registerState = RegisterState.INCORRECTDATA;
            return registerState;
        }
        if (usernamePasswordMap.get(name) == null) {
            registerState = RegisterState.SUCCESS;
            usernamePasswordMap.put(name,password);
        }
        return registerState;
    }
}
