package models.classes.objects;

import PlatformGameShared.Points.Vector2;
import models.enums.WeaponType;

public class ProjectileFactory {

    public static Projectile spawnProjectile(Player player){
        WeaponType weaponType = player.getCurrentWeapon();
        Projectile projectile = null;
        Vector2 pos = player.getPosition();
        float xfact = (player.isFacingLeft()) ? -1 : 1;
        switch (weaponType){
            case SWORD:
                break;
            case GUN:
                projectile = new ProjectileBullet(pos.getX(),pos.getY(),20,10,player);
                projectile.setAcceleration(ProjectileBullet.bulletAcceleration * xfact,0);
                break;
            case GRENADELAUNCHER:
                projectile = new ProjectileBomb(pos.getX(),pos.getY(),player);
                projectile.setVelocity(10*xfact,5);
        }
        return projectile;
    }

}
