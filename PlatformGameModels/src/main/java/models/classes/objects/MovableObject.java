package models.classes.objects;

import PlatformGameShared.Enums.SpriteUpdateType;
import PlatformGameShared.PlatformLogger;
import PlatformGameShared.Points.SpriteUpdate;
import PlatformGameShared.Points.Vector2;
import models.classes.GameObject;

import java.util.logging.Level;

public abstract class MovableObject extends GameObject {
    private Vector2 acceleration;
    private Vector2 velocity;
    private float maxHorizontalVelocity = 20;
    private float maxVerticalVelocity = 40;
    private boolean useGravity;
    private boolean shouldBeCleaned = false;
    private boolean isFacingLeft = false;
    private boolean isGrounded = false;
    private int timeInAir = 0;
    private int maxTimeInAir = 70; //the amount of update cycles it will take for us to reach terminal velocity
    private float weight = 0.2f;
    //private final float gravity = -9.81f;
    private final float gravity = -0.1f;
    private float friction = .75f;//the lower, the faster you'll stop!

    public MovableObject(float xPosition, float yPosition, float width, float height) {
        super(xPosition, yPosition, width, height);
        useGravity = true;
        velocity = Vector2.Zero();
        acceleration = Vector2.Zero();
    }

    /**
     * What to do when this object collides with another one
     *
     * @param other        The object we collide with
     * @param collidePoint The point where the collision took place
     */
    public abstract void onCollide(GameObject other, Vector2 collidePoint);

    public void setGrounded(boolean value) {
        this.isGrounded = value;
    }

    public boolean isGrounded() {
        return isGrounded;
    }

    /**
     * Applies all forces and changes that should take place this game tick
     */
    public void update() {
        Vector2 startPos = getPosition();
        if (!isGrounded() && isUseGravity()) doGravity();
        else {
            if (acceleration.getY() < 0) {
                timeInAir = 0;
                acceleration.setY(0);
                velocity.setY(0);
            }

        }

        //add acceleration
        velocity.setX(velocity.getX() + acceleration.getX());
        velocity.setY(velocity.getY() + acceleration.getY());

        //now we cap it
        if (velocity.getX() > maxHorizontalVelocity) velocity.setX(maxHorizontalVelocity);
        else if (velocity.getX() < -maxHorizontalVelocity) velocity.setX(-maxHorizontalVelocity);

        if (velocity.getY() > maxVerticalVelocity) velocity.setY(maxVerticalVelocity);
        else if (velocity.getY() < -maxVerticalVelocity) velocity.setY(-maxVerticalVelocity);

        Vector2 pos = getPosition();
        setPosition(pos.getX() + velocity.getX(), pos.getY() + velocity.getY());

        //apply friction
        velocity.setX(velocity.getX() * friction);
        if (velocity.getX() < 0.01f && velocity.getX() > -0.01f) velocity.setX(0);

        //Sprite change
        Vector2 endPos = getPosition();
        if (velocity.getX() < 0) isFacingLeft = true;
        else if (velocity.getX() > 0) isFacingLeft = false;//we want to keep the direction after we've stopped moving
        if (!startPos.equals(endPos)) {
            setChanged();
            notifyObservers(new SpriteUpdate(getObjectNr(), endPos, getSize(), SpriteUpdateType.MOVE, getSpriteType(), isFacingLeft, getLabel()));
        }
    }

    @Override
    public void setSize(float x, float y) {
        super.setSize(x, y);
        setChanged();
        notifyObservers(new SpriteUpdate(getObjectNr(), getPosition(), getSize(), SpriteUpdateType.MOVE, getSpriteType(), isFacingLeft, getLabel()));
    }


    public void setPosition(float x, float y, boolean forceUpdate) {
        super.setPosition(x, y);
        if(forceUpdate) {
            setChanged();
            notifyObservers(new SpriteUpdate(getObjectNr(), getPosition(), getSize(), SpriteUpdateType.MOVE, getSpriteType(), isFacingLeft, getLabel()));
        }
    }

    private void doGravity() {
        if (timeInAir < maxTimeInAir) timeInAir++;
        addAcceleration(0, weight + timeInAir * gravity);//make this increase or something
    }

    public void addAcceleration(float x, float y) {
        acceleration = new Vector2(acceleration.getX() + x, acceleration.getY() + y);
    }

    public void markForDeletion() {
        shouldBeCleaned = true;
        PlatformLogger.Log(Level.FINE, "Object " + toString() + " added to delete queue");
    }

    public void onOutOfBounds() {
        markForDeletion();
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

    public float getFriction() {
        return friction;
    }

    public void setFriction(float friction) {
        this.friction = friction;
    }

    public void setVelocity(float x, float y) {
        velocity = new Vector2(x, y);
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public float getVelocity(boolean isUpward) {
        return (isUpward) ? velocity.getY() : velocity.getX();
    }

    public boolean isUseGravity() {
        return useGravity;
    }

    public void setUseGravity(boolean useGravity) {
        this.useGravity = useGravity;
    }

    public boolean isShouldBeCleaned() {
        return shouldBeCleaned;
    }

    public boolean isFacingLeft() {
        return isFacingLeft;
    }

    public void setFacingLeft(boolean value) {
        this.isFacingLeft = value;
    }

    public String getLabel() {
        return this.toString();
    }

    @Override
    public String toString() {
        return this.getClass().toString();
    }

    public float getMaxHorizontalVelocity() {
        return maxHorizontalVelocity;
    }

    public void setMaxHorizontalVelocity(float maxHorizontalVelocity) {
        this.maxHorizontalVelocity = maxHorizontalVelocity;
    }

    public float getMaxVerticalVelocity() {
        return maxVerticalVelocity;
    }

    public void setMaxVerticalVelocity(float maxVerticalVelocity) {
        this.maxVerticalVelocity = maxVerticalVelocity;
    }

    public void invertVelocity() {
        velocity.setX(-velocity.getX());
        velocity.setY(-velocity.getY());
    }

    public void setTimeInAir(int timeInAir) {
        this.timeInAir = timeInAir;
    }
}
