package models.classes;

import PlatformGameShared.Enums.InputType;
import PlatformGameShared.Enums.SpriteUpdateType;
import PlatformGameShared.Points.SpriteUpdate;
import PlatformGameShared.Points.Vector2;
import models.classes.objects.MovableObject;
import models.classes.objects.Platform;
import models.classes.objects.Player;
import models.classes.objects.WeaponPickup;
import models.enums.WeaponType;

import java.util.*;

public class Game implements Observer {


    private List<MovableObject> movableObjects = new ArrayList<>();
    private Map<Integer, Player> playerNrMap;
    private List<SpriteUpdate> spriteUpdates;
    private int spriteCount = 0;
    private boolean firstUpdateComplete = false;

    public Game() {
        playerNrMap = new HashMap<>();
        spriteUpdates = new ArrayList<>();
    }

    public void setUpGame() {
        movableObjects = new ArrayList<>();
        for (int i = 1; i <= 1; i++) {
            Player p = new Player(10 * i, 100 * i);
            createSprite(p);
            movableObjects.add(p);
            playerNrMap.put(i, p);
        }
        for (int i = 1; i < 5; i++) {
            WeaponPickup pickup = new WeaponPickup(10*i,200*i, WeaponType.GUN);
            pickup.setVelocity(20*i,0);
            createSprite(pickup);
            movableObjects.add(pickup);
        }
        Platform plat = new Platform(100,50,200,50,true);
        createSprite(plat);
    }


    public void updateState() {
        System.out.println("[Game.java] Update cycle: " + new java.util.Date());
        if(firstUpdateComplete) {
            spriteUpdates.clear();
        }
        firstUpdateComplete = true;
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
        if(o instanceof SpriteUpdate){
            spriteUpdates.add((SpriteUpdate) o);
        }
    }

    private void createSprite(GameObject gameObject){
        Vector2 pos = gameObject.getPosition();
        Vector2 scale = gameObject.getSize();
        spriteUpdates.add(new SpriteUpdate(spriteCount,pos,scale, SpriteUpdateType.CREATE,gameObject.getSpriteType(),false));
        gameObject.addObserver(this);
        gameObject.setObjectNr(spriteCount);
        spriteCount++;
    }
}
