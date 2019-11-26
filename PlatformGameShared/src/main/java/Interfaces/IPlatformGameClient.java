package Interfaces;

import Classes.SpriteUpdate;
import Enums.GameState;
import Enums.InputType;

import java.util.List;

public interface IPlatformGameClient {

    public void receiveSpriteUpdates(List<SpriteUpdate> positions);

    public void receiveGameState(GameState gameState);

    public void sendInput(InputType inputType);

    public List<SpriteUpdate> getAllSprites();



}
