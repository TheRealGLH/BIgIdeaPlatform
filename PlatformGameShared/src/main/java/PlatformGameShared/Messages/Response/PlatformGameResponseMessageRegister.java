package PlatformGameShared.Messages.Response;

import PlatformGameShared.Enums.GameResponseMessageType;
import PlatformGameShared.Enums.RegisterState;

public class PlatformGameResponseMessageRegister extends PlatformGameResponseMessage{

    public PlatformGameResponseMessageRegister(RegisterState registerState) {
        this.setResponseMessageType(GameResponseMessageType.RegisterState);
        this.registerState = registerState;
    }

    public RegisterState getRegisterState() {
        return registerState;
    }

    private RegisterState registerState;

}
