package models.classes;

import Enums.InputType;
import Enums.SpriteUpdateType;
import SharedClasses.SpriteUpdate;
import SharedClasses.Vector2;
import models.classes.objects.MovableObject;
import models.classes.objects.Player;

import java.util.*;

public class Game implements Observer {


    private List<MovableObject> movableObjects = new ArrayList<>();
    private Map<Integer, Player> playerNrMap;
    private List<SpriteUpdate> spriteUpdates;
    private int spriteCount = 0;

    public Game() {
        playerNrMap = new HashMap<>();
        spriteUpdates = new ArrayList<>();
    }

    public void setUpGame() {
        movableObjects = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Player p = new Player(10 * i, 100 * i);
            createSprite(p);
            movableObjects.add(p);
            playerNrMap.put(i, p);
        }
    }


    public void updateState() {
        System.out.println("[Game.java] Update cycle: " + new java.util.Date());
        spriteUpdates.clear();
        for (MovableObject movableObject : movableObjects) {
            movableObject.update();
        }
    }

    public List<SpriteUpdate> getSpriteUpdates(){
        return spriteUpdates;
    }

    public void sendInput(int playerNr, InputType inputType) {
        playerNrMap.get(playerNr).handleInput(inputType);
    }

    @Override
    public void update(Observable observable, Object o) {

    }

    private void createSprite(GameObject gameObject){
        Vector2 pos = gameObject.getPosition();
        Vector2 scale = gameObject.getSize();
        spriteUpdates.add(new SpriteUpdate(spriteCount,pos,scale, SpriteUpdateType.CREATE,gameObject.getSpriteType(),false));
        spriteCount++;
    }
}
