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
    private boolean shotLastUpdate = false;
    private InputType lastMove;
    private float startX, startY;
    private String name = "undefinedplayer";
    private List<IPlayerEventListener> shootEventListenerList = new ArrayList<>();
    private float standingHeight = 20;
    private float width;
    private boolean ducked = false;


    private float walkAcceleration = 1;
    private float maxHorizontalAcceleration = 5;

    public Player(float xPos, float yPos) {
        super(xPos, yPos, 20, 20);
        standingHeight = 20;
        width = 20;
        this.startX = xPos;
        this.startY = yPos;
    }

    public void handleInput(InputType inputType) {
        System.out.println(this + "received input " + inputType);
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
        if(!shotLastUpdate) {
            shotLastUpdate = true;
            for (IPlayerEventListener iPlayerEventListener : shootEventListenerList) {
                iPlayerEventListener.onShootEvent(this);
            }
        }
    }

    public void Kill() {
        setAcceleration(0, 0);
        setVelocity(0, 0);
        setPosition(startX, startY);
    }

    public void jump() {
        if (isGrounded()) {
            addAcceleration(getAcceleration(false), 2.5f);
        }
    }

    public void duck() {
        setSize(width, standingHeight / 1);
        ducked = true;
    }

    public void unDuck() {
        if (ducked) {
            setSize(width, standingHeight);
            ducked = false;
        }
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
            float acc = walkAcceleration;
            if(!isGrounded()) acc = walkAcceleration/2;
            switch (lastMove) {
                case MOVELEFT:
                    //setAcceleration(-acc,getAcceleration().getY());
                    addAcceleration(-acc, 0);
                    setFacingLeft(true);
                    break;
                case MOVERIGHT:
                    //setAcceleration(acc,getAcceleration().getY());
                    addAcceleration(acc, 0);
                    setFacingLeft(false);
                    break;
                case DUCK:
                    //TODO ducking
                    break;
            }
        } else {
            //if we're not walking, we don't want to have any more X acceleration.
            setAcceleration(0, getAcceleration().getY());
        }
        if (willJump) jump();
        if (willShoot) useWeapon();
        else shotLastUpdate = false;
        hasInputMove = false;
        willJump = false;
        willShoot = false;
        Vector2 acc = getAcceleration();
        //Cap acceleration
        if(acc.getX() > maxHorizontalAcceleration) setAcceleration(maxHorizontalAcceleration,acc.getY());
        if(acc.getX() < -maxHorizontalAcceleration) setAcceleration(-maxHorizontalAcceleration,acc.getY());
        super.update();
    }

    @Override
    public void onCollide(GameObject other, Vector2 collidePoint) {
        //Nobody here but us chickens
    }

    @Override
    public void onOutOfBounds() {
        Kill();
    }

    @Override
    public SpriteType getSpriteType() {
        return SpriteType.PLAYER;
    }

    @Override
    public String toString() {
        return "Player@" + hashCode() + " Pos " + getPosition();
    }

    /*
    @Override
    public String getLabel() {
        return name + " " +currentWeapon +" G "+isGrounded() + " P: "+getPosition();
    }
     */

    @Override
    public String getLabel() {
        return name + " " + currentWeapon + " P ; " + getPosition() +
                "\n V " + getVelocity() +
                "\n A " + getAcceleration() +
                "\n G " + isGrounded();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addShootEventListener(IPlayerEventListener shootEventListener) {
        shootEventListenerList.add(shootEventListener);
    }
}
