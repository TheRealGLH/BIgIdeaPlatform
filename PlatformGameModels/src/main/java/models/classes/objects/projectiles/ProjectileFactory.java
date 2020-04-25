package models.classes.objects.projectiles;

import PlatformGameShared.PlatformLogger;
import PlatformGameShared.Points.Vector2;
import models.classes.objects.Player;
import models.enums.WeaponType;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class ProjectileFactory {

    private static final Map<WeaponType, Projectile> projectileMap = new HashMap<>() {{
        put(WeaponType.SWORD, new ProjectileSwordAttack(0, 0, null));
        put(WeaponType.GUN, new ProjectileBullet(0, 0, null) {{
            setAcceleration(bulletAcceleration, 0);
        }});
        put(WeaponType.GRENADELAUNCHER, new ProjectileBomb(0, 0, null) {{
            setVelocity(40, 5);
        }});
        put(WeaponType.ROCKET, new ProjectileBombRocket(0, 0, null) {{
            setAcceleration(rocketAcceleration, 0);
        }});
        put(WeaponType.THROWAXE, new ProjectileThrowingAxe(0, 0, null) {{
            setVelocity(horizontalVelocity, verticalVelocity);
        }});
    }};

    public static Projectile spawnProjectile(Player player) {
        WeaponType weaponType = player.getCurrentWeapon();
        Projectile projectile = null;
        Vector2 pos = player.getPosition();
        //we want the position to not be exactly the same as the player's, but in front of them
        Vector2 playerSize = player.getSize();
        //object origin is bottom left. so if player is facing left, something right in front of them would be at their horizontal 0
        float xModifier = player.isFacingLeft() ? 0.1f : playerSize.getX() + 0.1f;
        pos.setX(pos.getX() + xModifier);
        //mid point of their vertical size
        pos.setY(pos.getY() + (playerSize.getY() / 2));
        //we want to reverse the movement (velocity and/ or acceleration) if the player faces left
        float movementXFactor = player.isFacingLeft() ? -1 : 1;

        //now we attempt to get the projectile from our nice little map
        projectile = projectileMap.get(weaponType).clone();
        if (projectile == null) {
            //fallback for projectiles that somehow spawned but don't have an entry in the map
            projectile = projectileMap.get(WeaponType.GUN);
            PlatformLogger.Log(Level.SEVERE, "Tried spawning a projectile for a weapon that does not yet have one: " + weaponType);
        }
        //now we assign or overwrite data for our newly cloned projectile.
        projectile.setOwner(player);
        projectile.setPosition(pos.getX(), pos.getY());
        projectile.setVelocity(projectile.getVelocity(false) * movementXFactor, projectile.getVelocity(false));
        projectile.setAcceleration(projectile.getAcceleration(false) * movementXFactor, projectile.getAcceleration(true));
        projectile.setFacingLeft(player.isFacingLeft());
        return projectile;
    }

}