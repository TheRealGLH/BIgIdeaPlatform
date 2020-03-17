package PlatformGameShared.Messages.Response;

import PlatformGameShared.Enums.GameResponseMessageType;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class PlatformGameResponseMessage {

    private String hash;

    public PlatformGameResponseMessage() {
        byte[] array = new byte[7];
        new Random().nextBytes(array);
        this.hash = new String(array, StandardCharsets.US_ASCII);
    }

    private GameResponseMessageType responseMessageType;

    public String getHash() {
        return this.hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public GameResponseMessageType getResponseMessageType() {
        return responseMessageType;
    }

    public void setResponseMessageType(GameResponseMessageType responseMessageType) {
        this.responseMessageType = responseMessageType;
    }
}
