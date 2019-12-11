package models.classes.objects;

import PlatformGameShared.Enums.SpriteType;
import PlatformGameShared.Points.Vector2;
import PlatformGameShared.PropertiesLoader;
import models.classes.GameObject;

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
    public SpriteType getSpriteType() {
        if (explodeTime <= 0) {
            return SpriteType.PROJECTILEBOMBEXPLODE;
        }
        return SpriteType.PROJECTILEBOMB;
    }
}
