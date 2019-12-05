package PlatformGameShared.Messages.Client;

import PlatformGameShared.Enums.GameClientMessageType;

public class PlatformGameMessageRegister extends PlatformGameMessage{

    private String name;
    private String password;

    public PlatformGameMessageRegister(String name, String password) {
        this.setMessageType(GameClientMessageType.Register);
        this.name = name;
        this.password = password;
    }

    public PlatformGameMessageRegister(){};


    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
