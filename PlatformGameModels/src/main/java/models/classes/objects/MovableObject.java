package models.classes.objects;

import SharedClasses.Vector2;
import models.classes.GameObject;

public abstract class MovableObject extends GameObject {
    public MovableObject(float xPosition, float yPosition, float width, float height) {
        super(xPosition, yPosition, width, height);
    }

    public abstract void onCollide(GameObject other, Vector2 collidePoint);

    public void update() {
        throw new UnsupportedOperationException("MovableObject update() not yet implemented");
    }
}
