package models.classes;

import models.classes.objects.MovableObject;
import models.classes.objects.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {


    private List<MovableObject> movableObjects = new ArrayList<>();

    public Game() {

    }

    public void setUpGame() {
        movableObjects = new ArrayList<>();
        for (int i = 1; i <= 1; i++) {
            movableObjects.add(new Player(10 * i, 100 * i));
        }
    }


    public void updateState() {
        System.out.println("[Game.java] Update cycle: " + new java.util.Date());
        for (MovableObject movableObject : movableObjects) {
            movableObject.update();
        }
    }

    public void sendInput(int playerNr) {
        throw new UnsupportedOperationException("Method sendInput() has not yet been implemented");
    }
}
