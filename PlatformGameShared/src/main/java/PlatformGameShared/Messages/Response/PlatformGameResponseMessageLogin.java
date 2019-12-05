package PlatformGameShared.Messages.Response;

import PlatformGameShared.Enums.GameResponseMessageType;
import PlatformGameShared.Enums.LoginState;

public class PlatformGameResponseMessageLogin extends PlatformGameResponseMessage{

    private LoginState loginState;

    public PlatformGameResponseMessageLogin(LoginState loginState) {
        this.setResponseMessageType(GameResponseMessageType.LoginState);
        this.loginState = loginState;
    }

    public LoginState getLoginState() {
        return loginState;
    }
}
