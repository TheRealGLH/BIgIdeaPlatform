package models.classes.objects;

import SharedClasses.Vector2;
import models.classes.GameObject;

public abstract class MovableObject extends GameObject {
    private Vector2 acceleration;
    private Vector2 speed;
    public MovableObject(float xPosition, float yPosition, float width, float height) {
        super(xPosition, yPosition, width, height);
    }

    public abstract void onCollide(GameObject other, Vector2 collidePoint);

    public boolean isGrounded() {
        //TODO proper implementation
        return this.getPosition().getY() <= 0;
    }

    public void update() {
        throw new UnsupportedOperationException("MovableObject update() not yet implemented");
    }
}
