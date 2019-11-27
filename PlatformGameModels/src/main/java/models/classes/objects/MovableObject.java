package models.classes.objects;

import SharedClasses.Vector2;
import models.classes.GameObject;

public abstract class MovableObject extends GameObject {
    private Vector2 acceleration;
    private Vector2 velocity;
    private float maxHorizontalVelocity, maxVerticalVelocity = 20;
    private Vector2 maxVelocity = new Vector2(maxHorizontalVelocity, maxVerticalVelocity);

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

    public void addAcceleration(float x, float y) {
        throw new UnsupportedOperationException("Method addAcceleration() has not yet been implemented");
    }

    public void setAcceleration(float x, float y) {
        acceleration = new Vector2(x, y);
    }

    public Vector2 getAcceleration() {
        return acceleration;
    }

    public float getAcceleration(boolean isUpward) {
        return (isUpward) ? acceleration.getY() : acceleration.getX();
    }

    public void setVelocity(float x, float y) {
        velocity = new Vector2(x, y);
    }

    public float getVelocity(boolean isUpward) {
        return (isUpward) ? velocity.getY() : velocity.getX();
    }

}
