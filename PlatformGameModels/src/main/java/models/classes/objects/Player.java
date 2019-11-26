package models.classes.objects;

import Enums.InputType;
import SharedClasses.Vector2;
import models.classes.GameObject;
import models.enums.WeaponType;

public class Player extends MovableObject {


    private WeaponType currentWeapon = WeaponType.NONE;

    public Player(float xPos, float yPos) {
        super(xPos, yPos, 10, 10);

    }

    public void handleInput(InputType inputType) {
        throw new UnsupportedOperationException("Method handleInput() has not yet been implemented");
    }

    public void useWeapon() {
        throw new UnsupportedOperationException("Method useWeapon() has not yet been implemented");
    }

    public void jump() {
        throw new UnsupportedOperationException("Method jump() has not yet been implemented");
    }

    public void setCurrentWeapon(WeaponType weaponType) {
        this.currentWeapon = weaponType;
    }

    public boolean isGrounded() {
        //TODO proper implementation
        return this.getPosition().getY() <= 0;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void onCollide(GameObject other, Vector2 collidePoint) {
        throw new UnsupportedOperationException("Method onCollide has not yet been implemented");
    }
}
