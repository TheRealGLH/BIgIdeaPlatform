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
        float xfact = (player.isFacingLeft()) ? -1 : 1;
        projectile = (Projectile) projectileMap.get(player.getCurrentWeapon()).clone();
        projectile.setOwner(player);
        projectile.setPosition(player.getPosition().getX(), player.getPosition().getY());
        projectile.setVelocity(projectile.getVelocity(false) * xfact, projectile.getVelocity(false));
        projectile.setAcceleration(projectile.getAcceleration(false) * xfact, projectile.getAcceleration(true));
        projectile.setFacingLeft(player.isFacingLeft());
        return projectile;
    }

}