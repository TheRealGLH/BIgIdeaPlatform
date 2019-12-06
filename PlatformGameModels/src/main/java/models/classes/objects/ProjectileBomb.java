package models.classes.objects;

import PlatformGameShared.Enums.SpriteType;
import PlatformGameShared.Points.Vector2;
import models.classes.GameObject;

public class ProjectileBomb extends Projectile {
    private int explodeTime = 30;
    private static int explosionGrowth = 10;

    public ProjectileBomb(float xPosition, float yPosition, Player owner) {
        super(xPosition, yPosition, 5, 5, 60, owner);
        explodeTime = 60 - explodeTime;
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
