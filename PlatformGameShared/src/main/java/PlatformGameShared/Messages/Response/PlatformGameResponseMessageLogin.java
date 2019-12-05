package PlatformGameShared.Messages.Response;

import PlatformGameShared.Enums.GameResponseMessageType;
import PlatformGameShared.Enums.LoginState;

public class PlatformGameResponseMessageLogin extends PlatformGameResponseMessage{

    private LoginState loginState;
    private String name;

    public PlatformGameResponseMessageLogin(LoginState loginState, String name) {
        this.name = name;
        this.setResponseMessageType(GameResponseMessageType.LoginState);
        this.loginState = loginState;
    }

    public LoginState getLoginState() {
        return loginState;
    }

    public String getName() {
        return name;
    }
}
