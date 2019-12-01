package models.classes.objects;

import Enums.InputType;
import Enums.SpriteType;
import SharedClasses.Vector2;
import models.classes.GameObject;
import models.enums.WeaponType;

public class Player extends MovableObject {


    private WeaponType currentWeapon = WeaponType.NONE;
    private boolean hasUsedInput = false;
    private InputType lastInput;
    private float startX, startY;


    private float walkAcceleration = 15;

    public Player(float xPos, float yPos) {
        super(xPos, yPos, 20, 20);
        this.startX = xPos;
        this.startY = yPos;
    }

    public void handleInput(InputType inputType) {
        System.out.println("[Player.java] got input "+inputType);
        hasUsedInput = true;
        lastInput = inputType;
    }

    public void useWeapon() {
        throw new UnsupportedOperationException("Method useWeapon() has not yet been implemented");
    }

    public void Kill() {
        setAcceleration(0, 0);
        setVelocity(0, 0);
        setPosition(startX, startY);
    }

    public void jump() {
        addAcceleration(0,5);
    }

    public void setCurrentWeapon(WeaponType weaponType) {
        this.currentWeapon = weaponType;
    }

    public WeaponType getCurrentWeapon() {
        return this.currentWeapon;
    }

    /**
     * @return The amount of acceleration we have when we're walking
     */
    public float getWalkAcceleration() {
        return walkAcceleration;
    }

    /**
     * Sets the amount of acceleration when walking.
     *
     * @param walkAcceleration The amount to set.
     */
    public void setWalkAcceleration(float walkAcceleration) {
        this.walkAcceleration = walkAcceleration;
    }

    @Override
    public void update() {
        if (hasUsedInput) {
            switch (lastInput) {
                case MOVELEFT:
                    addAcceleration(-walkAcceleration, 0);
                    break;
                case MOVERIGHT:
                    addAcceleration(walkAcceleration, 0);
                    break;
                case JUMP:
                    jump();
                case SHOOT:
                case DUCK:
                    System.out.println("[Player.java] Input has not yet been handled: " + lastInput);
                    break;
            }
        } else {
            //if we're not walking, we don't want to have any more X acceleration.
            setAcceleration(0, getAcceleration().getY());
        }
        hasUsedInput = false;
        super.update();
    }

    @Override
    public void onCollide(GameObject other, Vector2 collidePoint) {
        throw new UnsupportedOperationException("Method onCollide has not yet been implemented");
    }

    @Override
    public SpriteType getSpriteType() {return SpriteType.PLAYER;}
}
