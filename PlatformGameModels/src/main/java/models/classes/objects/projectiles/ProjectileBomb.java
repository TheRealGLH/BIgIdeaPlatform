package models.classes.objects.projectiles;

import PlatformGameShared.Enums.SpriteType;
import PlatformGameShared.Points.Vector2;
import PlatformGameShared.PropertiesLoader;
import models.classes.GameObject;
import models.classes.objects.Player;

public class ProjectileBomb extends Projectile {
    private int explodeTime = Integer.parseInt(PropertiesLoader.getPropValues("projectileBomb.explodeTime","weapons.properties"));;
    private static int explosionGrowth = Integer.parseInt(PropertiesLoader.getPropValues("projectileBomb.explosionGrowth","weapons.properties"));;
    private static int maxLife = Integer.parseInt(PropertiesLoader.getPropValues("projectileBomb.maxLife","weapons.properties"));;
    private static float size = Float.parseFloat(PropertiesLoader.getPropValues("projectileBomb.size","weapons.properties"));

    public ProjectileBomb(float xPosition, float yPosition, Player owner) {
        super(xPosition, yPosition, size, size, maxLife, owner);
        explodeTime = maxLife - explodeTime;
        destroyOnHit = false;
    }

    @Override
    public void update() {
        explodeTime--;
        if (explodeTime <= 0) {
            setUseGravity(false);
            Vector2 size = getSize();
            setSize(size.getX() + explosionGrowth, size.getY() + explosionGrowth);
        }
        super.update();
    }

    @Override
    public void onCollide(GameObject other, Vector2 collidePoint) {
        if (explodeTime <= 0) {
            super.onCollide(other, collidePoint);
        }
    }

    @Override
    public Projectile clone() {
        Vector2 pos = getPosition();
        Vector2 acc = getAcceleration();
        Vector2 vel = getVelocity();
        ProjectileBomb clone = new ProjectileBomb(pos.getX(), pos.getY(), getOwner());
        clone.setAcceleration(acc.getX(), acc.getY());
        clone.setVelocity(vel.getX(), vel.getY());
        return clone;
    }


    @Override
    public SpriteType getSpriteType() {
        if (explodeTime <= 0) {
            return SpriteType.PROJECTILEBOMBEXPLODE;
        }
        return SpriteType.PROJECTILEBOMB;
    }
}
