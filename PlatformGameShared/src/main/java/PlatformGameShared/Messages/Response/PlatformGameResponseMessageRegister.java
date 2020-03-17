package PlatformGameShared.Messages.Response;

import PlatformGameShared.Enums.GameResponseMessageType;
import PlatformGameShared.Enums.RegisterState;

public class PlatformGameResponseMessageRegister extends PlatformGameResponseMessage{

    public PlatformGameResponseMessageRegister(RegisterState registerState, String name) {
        super();
        this.name = name;
        this.setResponseMessageType(GameResponseMessageType.RegisterState);
        this.registerState = registerState;
    }

    public PlatformGameResponseMessageRegister(){}

    public RegisterState getRegisterState() {
        return registerState;
    }

    private RegisterState registerState;
    private String name;

    public String getName() {
        return name;
    }

    public void setRegisterState(RegisterState registerState) {
        this.registerState = registerState;
    }

    public void setName(String name) {
        this.name = name;
    }
}
