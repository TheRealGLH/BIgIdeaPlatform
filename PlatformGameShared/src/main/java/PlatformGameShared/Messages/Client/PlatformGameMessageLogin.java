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

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

}
