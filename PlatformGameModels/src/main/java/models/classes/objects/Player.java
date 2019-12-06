package models.classes.objects;

import PlatformGameShared.Enums.InputType;
import PlatformGameShared.Enums.SpriteType;
import PlatformGameShared.Points.Vector2;
import models.classes.GameObject;
import models.enums.WeaponType;

import java.util.ArrayList;
import java.util.List;

public class Player extends MovableObject {


    private WeaponType currentWeapon = WeaponType.GRENADELAUNCHER;
    private boolean hasInputMove = false;
    private boolean willJump = false;
    private boolean willShoot = false;
    private InputType lastMove;
    private float startX, startY;
    private String name = "undefinedplayer";
    private List<IShootEventListener> shootEventListenerList = new ArrayList<>();


    private float walkAcceleration = 3;

    public Player(float xPos, float yPos) {
        super(xPos, yPos, 20, 20);
        this.startX = xPos;
        this.startY = yPos;
    }

    public void handleInput(InputType inputType) {
        switch (inputType) {
            case MOVELEFT:
            case MOVERIGHT:
            case DUCK:
                lastMove = inputType;
                hasInputMove = true;
                break;
            case JUMP:
                willJump = true;
                break;
            case SHOOT:
                willShoot = true;
                break;
        }
    }

    public void useWeapon() {
        for (IShootEventListener iShootEventListener : shootEventListenerList) {
            iShootEventListener.onShootEvent(this);
        }
    }

    public void Kill() {
        setAcceleration(0, 0);
        setVelocity(0, 0);
        setPosition(startX, startY);
    }

    public void jump() {
        if (isGrounded()) addAcceleration(getAcceleration(false), 2.5f);
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
        if (hasInputMove) {
            switch (lastMove) {
                case MOVELEFT:
                    addAcceleration(-walkAcceleration, 0);
                    break;
                case MOVERIGHT:
                    addAcceleration(walkAcceleration, 0);
                    break;
                case DUCK:
                    //TODO ducking
                    break;
            }
        } else {
            //if we're not walking, we don't want to have any more X acceleration.
            setAcceleration(0, getAcceleration().getY());
        }
        if(willJump) jump();
        if(willShoot) useWeapon();
        hasInputMove = false;
        willJump = false;
        willShoot = false;
        super.update();
    }

    @Override
    public void onCollide(GameObject other, Vector2 collidePoint) {
        //Nobody here but us chickens
    }

    @Override
    public SpriteType getSpriteType() {
        return SpriteType.PLAYER;
    }

    @Override
    public String toString() {
        return name + " :" + currentWeapon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addShootEventListener(IShootEventListener shootEventListener){
        shootEventListenerList.add(shootEventListener);
    }
}
