package PlatformGameShared.Messages.Response;

import PlatformGameShared.Enums.GameResponseMessageType;
import PlatformGameShared.Enums.LoginState;

public class PlatformGameResponseMessageLogin extends PlatformGameResponseMessage{

    private LoginState loginState;
    private String name;

    public PlatformGameResponseMessageLogin(LoginState loginState, String name) {
        super();
        this.name = name;
        this.setResponseMessageType(GameResponseMessageType.LoginState);
        this.loginState = loginState;
    }

    public PlatformGameResponseMessageLogin(){}

    public LoginState getLoginState() {
        return loginState;
    }

    public String getName() {
        return name;
    }

    public void setLoginState(LoginState loginState) {
        this.loginState = loginState;
    }

    public void setName(String name) {
        this.name = name;
    }
}
