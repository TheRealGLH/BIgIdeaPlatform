package models.classes.objects;

import PlatformGameShared.Points.Vector2;
import models.enums.WeaponType;

public class ProjectileFactory {

    public static Projectile spawnProjectile(Player player){
        WeaponType weaponType = player.getCurrentWeapon();
        Projectile projectile = null;
        switch (weaponType){
            case SWORD:
                break;
            case GUN:
                Vector2 pos = player.getPosition();
                projectile = new ProjectileBullet(pos.getX(),pos.getY(),20,10,player);
                break;
        }
        return projectile;
    }

}
