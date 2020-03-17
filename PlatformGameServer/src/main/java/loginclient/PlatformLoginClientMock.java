package loginclient;

import PlatformGameShared.Enums.LoginState;
import PlatformGameShared.Enums.RegisterState;

import java.util.HashMap;
import java.util.Map;

public class PlatformLoginClientMock implements IPlatformLoginClient {

    Map<String, String> usernamePasswordMap;

    public PlatformLoginClientMock() {
        usernamePasswordMap = new HashMap<>();
        usernamePasswordMap.put("test", "123456");
        usernamePasswordMap.put("fred", "123456");
    }



    @Override
    public LoginState attemptLogin(String username, String password) {
        LoginState loginState = LoginState.INCORRECTDATA;
        String pw = usernamePasswordMap.get(username);
        if (pw == null) return loginState;
        if (pw.equals(password)) {
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
            usernamePasswordMap.put(username, password);
        }
        return registerState;
    }

    @Override
    public String getLevelNames() {
        throw new UnsupportedOperationException("The method <> has not yet been implemented");
    }

    @Override
    public String getLevelContent(String levelname) {
        return "{\n" +
                "  \"name\": \"Mock Rest Land\",\n" +
                "  \"width\": 600,\n" +
                "  \"height\": 600,\n" +
                "  \"objects\": [\n" +
                "    {\n" +
                "      \"kind\": \"platform\",\n" +
                "      \"xpos\": 100,\n" +
                "      \"ypos\": 50,\n" +
                "      \"width\": 600,\n" +
                "      \"height\": 50,\n" +
                "      \"solid\": true\n" +
                "    },\n" +
                "    {\n" +
                "      \"kind\": \"platform\",\n" +
                "      \"xpos\": 350,\n" +
                "      \"ypos\": 300,\n" +
                "      \"width\": 100,\n" +
                "      \"height\": 20,\n" +
                "      \"solid\": false\n" +
                "    },\n" +
                "    {\n" +
                "      \"kind\": \"platform\",\n" +
                "      \"xpos\": 600,\n" +
                "      \"ypos\": 200,\n" +
                "      \"width\": 100,\n" +
                "      \"height\": 20,\n" +
                "      \"solid\": false\n" +
                "    },\n" +
                "    {\n" +
                "      \"kind\": \"platform\",\n" +
                "      \"xpos\": 100,\n" +
                "      \"ypos\": 200,\n" +
                "      \"width\": 100,\n" +
                "      \"height\": 20,\n" +
                "      \"solid\": false\n" +
                "    },\n" +
                "    {\n" +
                "      \"kind\": \"playerspawn\",\n" +
                "      \"xpos\": 250,\n" +
                "      \"ypos\": 210\n" +
                "    },\n" +
                "    {\n" +
                "      \"kind\": \"playerspawn\",\n" +
                "      \"xpos\": 150,\n" +
                "      \"ypos\": 210\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }
}
