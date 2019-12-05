package PlatformGameShared.Messages.Client;

import PlatformGameShared.Enums.GameClientMessageType;

public class PlatformGameMessageLogin extends PlatformGameMessage{


    String name;
    String password;

    public PlatformGameMessageLogin(String name, String password){
        this.setMessageType(GameClientMessageType.Login);
        this.name = name;
        this.password = password;
    }

    public PlatformGameMessageLogin(){}

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
