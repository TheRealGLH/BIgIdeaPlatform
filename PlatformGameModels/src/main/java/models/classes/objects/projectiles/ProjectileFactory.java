package models.classes.objects.projectiles;

import PlatformGameShared.Points.Vector2;
import models.classes.objects.Player;
import models.enums.WeaponType;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ProjectileFactory {

    private static final Map<WeaponType, Projectile> projectileMap = new HashMap<>() {{
        put(WeaponType.SWORD, new ProjectileSwordAttack(0, 0, null));
        put(WeaponType.GUN, new ProjectileBullet(0, 0, null) {{
            setAcceleration(bulletAcceleration, 0);
        }});
        put(WeaponType.GRENADELAUNCHER, new ProjectileBomb(0, 0, null) {{
            setVelocity(40, 5);
        }});
        put(WeaponType.THROWAXE, new ProjectileThrowingAxe(0, 0, null) {{
            setVelocity(horizontalVelocity, verticalVelocity);
        }});
    }};

    public static Projectile spawnProjectile(Player player) {
        WeaponType weaponType = player.getCurrentWeapon();
        Projectile projectile = null;
        Vector2 pos = player.getPosition();
        float xFactor = (player.isFacingLeft()) ? -1 : 1;
        projectile = projectileMap.get(weaponType).clone();
        if (projectile == null) {
            projectile = projectileMap.get(WeaponType.GUN);
            System.out.println("[ProjectileFactory] Tried spawning a projectile for a weapon that does not yet have one: " + weaponType);
        }
        projectile.setOwner(player);
        projectile.setPosition(pos.getX(), pos.getY());
        //Adjusting for players facing the left direction.
        projectile.setVelocity(projectile.getVelocity(false) * xFactor, projectile.getVelocity(false));
        projectile.setAcceleration(projectile.getAcceleration(false) * xFactor, projectile.getAcceleration(true));
        projectile.setFacingLeft(player.isFacingLeft());
        return projectile;
    }

}