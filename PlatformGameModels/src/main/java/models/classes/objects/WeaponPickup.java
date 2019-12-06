package models.classes.objects;

import PlatformGameShared.Enums.SpriteType;
import PlatformGameShared.Points.Vector2;
import models.classes.GameObject;
import models.enums.WeaponType;

public class WeaponPickup extends MovableObject {



    private WeaponType weaponType;
    public WeaponPickup(float xPosition, float yPosition, WeaponType weaponType) {
        super(xPosition, yPosition, 7.5f, 7.5f);
        this.weaponType = weaponType;
    }

    public void pickUp(Player player){
        player.setCurrentWeapon(this.weaponType);
        this.Delete();
    }
    public WeaponType getWeaponType() {
        return weaponType;
    }

   /*
    @Override
    public void update() {
        super.update();
    }
*/
    @Override
    public void onCollide(GameObject other, Vector2 collidePoint) {
        if(other instanceof Player) pickUp((Player) other);
    }

    @Override
    public SpriteType getSpriteType() {return SpriteType.WEAPONPICKUP;}

    @Override
    public String toString(){return weaponType.toString() + " Pickup";}

    @Override
    public String getLabel(){return weaponType.toString();}
}
