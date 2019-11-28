package models.classes.objects;

import SharedClasses.Vector2;
import models.classes.GameObject;
import models.enums.WeaponType;

public class WeaponPickup extends MovableObject {



    private WeaponType weaponType;
    public WeaponPickup(float xPosition, float yPosition, WeaponType weaponType) {
        super(xPosition, yPosition, 10, 10);
        this.weaponType = weaponType;
    }

    public void pickUp(Player player){
        throw new UnsupportedOperationException("Method pickUp() has not yet been implemented");
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
}
