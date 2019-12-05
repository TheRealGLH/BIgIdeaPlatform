package PlatformGameShared.Messages.Client;

import PlatformGameShared.Enums.GameClientMessageType;
import PlatformGameShared.Enums.InputType;

public class PlatformGameMessageInput extends PlatformGameMessage{

    private InputType inputType;

    public PlatformGameMessageInput(InputType inputType){
        this.setMessageType(GameClientMessageType.Input);
        this.inputType = inputType;
    }

    public PlatformGameMessageInput(){}

    public InputType getInputType(){
        return inputType;
    }

    public void setInputType(InputType inputType) {
        this.inputType = inputType;
    }
}
