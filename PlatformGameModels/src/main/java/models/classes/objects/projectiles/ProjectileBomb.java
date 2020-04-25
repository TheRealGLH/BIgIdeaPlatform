package models.classes.objects.projectiles;

import PlatformGameShared.Enums.SpriteType;
import PlatformGameShared.Points.Vector2;
import PlatformGameShared.PropertiesLoader;
import models.classes.GameObject;
import models.classes.objects.Player;

public class ProjectileBomb extends Projectile {
    //The timer to determine whether or not the bomb has already exploeded
    private int explodeTime = Integer.parseInt(PropertiesLoader.getPropValues("projectileBomb.explodeTime", "weapons.properties"));
    //We use this to determine how much an explosion should grow every game tick
    private static int explosionGrowth = Integer.parseInt(PropertiesLoader.getPropValues("projectileBomb.explosionGrowth", "weapons.properties"));

    private static int maxLife = Integer.parseInt(PropertiesLoader.getPropValues("projectileBomb.maxLife", "weapons.properties"));

    private static float size = Float.parseFloat(PropertiesLoader.getPropValues("projectileBomb.size", "weapons.properties"));

    private boolean hasExploded = false;

    public ProjectileBomb(float xPosition, float yPosition, Player owner) {
        super(xPosition, yPosition, size, size, maxLife, owner);
        explodeTime = maxLife - explodeTime;
        destroyOnHit = false;
    }

    public void explode() {
        setUseGravity(false);
        //explosions do not move, even if the projectile itself does
        setVelocity(0,0);
        setAcceleration(0,0);
        hasExploded = true;
    }

    @Override
    public void update() {
        explodeTime--;
        if (hasExploded) {
            Vector2 size = getSize();
            setSize(size.getX() + explosionGrowth, size.getY() + explosionGrowth);
        } else if (explodeTime <= 0) {
            explode();
        }
        super.update();
    }

    @Override
    public void onCollide(GameObject other, Vector2 collidePoint) {
        if (hasExploded) {
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

    /**
     * @return Has this bomb already exploded?
     */
    public boolean hasExploded() {
        return hasExploded;
    }


    @Override
    public SpriteType getSpriteType() {
        if (hasExploded()) {
            return SpriteType.PROJECTILEBOMBEXPLODE;
        }
        return SpriteType.PROJECTILEBOMB;
    }
}
