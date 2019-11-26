package Interfaces;

import SharedClasses.SpriteUpdate;
import Enums.GameState;
import Enums.InputType;

import java.util.List;

public interface IPlatformGameClient {

    public void updateScreen(List<SpriteUpdate> positions);

    public void joinGame();

    public void receiveGameState(GameState gameState);

    public void sendInput(InputType inputType);

    public List<SpriteUpdate> getAllSprites();


}
