package models.classes.objects.projectiles;

import PlatformGameShared.Enums.SpriteType;
import PlatformGameShared.Points.Vector2;
import PlatformGameShared.PropertiesLoader;
import models.classes.GameObject;
import models.classes.objects.Player;

public class ProjectileBombRocket extends ProjectileBomb {

    public static float rocketAcceleration = Float.parseFloat(PropertiesLoader.getPropValues("projectileRocket.acceleration", "weapons.properties"));

    public ProjectileBombRocket(float xPosition, float yPosition, Player owner) {
        super(xPosition, yPosition, owner);
        this.setUseGravity(false);
        this.allowsSelfHarm = true;
    }

    @Override
    public void onCollide(GameObject other, Vector2 collidePoint) {
        if (hasExploded()) super.onCollide(other, collidePoint);
        if (other instanceof Player) {
            Player otherPlayer = (Player) other;
            if (allowsSelfHarm | !otherPlayer.equals(getOwner())) explode();
        }
    }

    @Override
    public SpriteType getSpriteType() {
        if (this.hasExploded()) {
            return SpriteType.PROJECTILEBOMBEXPLODE;
        }
        return SpriteType.PROJECTILEROCKET;
    }

    @Override
    public Projectile clone() {
        Vector2 pos = getPosition();
        Vector2 acc = getAcceleration();
        Vector2 vel = getVelocity();
        ProjectileBomb clone = new ProjectileBombRocket(pos.getX(), pos.getY(), getOwner());
        clone.setAcceleration(acc.getX(), acc.getY());
        clone.setVelocity(vel.getX(), vel.getY());
        return clone;
    }
}
