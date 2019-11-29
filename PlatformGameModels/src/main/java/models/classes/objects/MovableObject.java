package models.classes.objects;

import SharedClasses.Vector2;
import models.classes.GameObject;

public abstract class MovableObject extends GameObject {
    private Vector2 acceleration;
    private Vector2 velocity;
    private float maxHorizontalVelocity = 20, maxVerticalVelocity = 20;
    private Vector2 maxVelocity = new Vector2(maxHorizontalVelocity, maxVerticalVelocity);
    private boolean useGravity;
    private boolean shouldBeCleaned = false;
    private int timeInAir = 0;
    private int maxTimeInAir = 70;
    private float weight = 4;
    private float friction = .75f;//the lower, the faster you'll stop!

    public MovableObject(float xPosition, float yPosition, float width, float height) {
        super(xPosition, yPosition, width, height);
        useGravity = true;
        velocity = Vector2.Zero();
        acceleration = Vector2.Zero();
    }

    public abstract void onCollide(GameObject other, Vector2 collidePoint);

    public boolean isGrounded() {
        //TODO proper implementation
        return this.getPosition().getY() <= 0;
    }

    public void update() {
        if (isGrounded() && isUseGravity()) doGravity();
        else timeInAir = 0;

        //add acceleration
        velocity.setX(velocity.getX()+acceleration.getX());
        velocity.setY(velocity.getY()+acceleration.getY());

        Vector2 pos = getPosition();
        setPosition(pos.getX()+velocity.getX(),pos.getY()+velocity.getY());

        //friction
        velocity.setX(velocity.getX()*friction);
        if(velocity.getX() < 0.001f) velocity.setX(0);
    }

    private void doGravity() {
        if (timeInAir < maxTimeInAir) timeInAir++;
            addAcceleration(0, weight * timeInAir * 9.81f);//make this increase or something
    }

    public void addAcceleration(float x, float y) {
        acceleration = new Vector2(acceleration.getX() + x, acceleration.getY() + y);
    }

    public void Delete() {
        shouldBeCleaned = true;
        System.out.println("[MovableObject.java] Send event for DELETE SpriteUpdate");
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

    public float getFriction(){return friction;}

    public void setFriction(float friction){this.friction = friction;}

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
}
