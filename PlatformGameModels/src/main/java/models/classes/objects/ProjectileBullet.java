package models.classes.objects;

import PlatformGameShared.Enums.SpriteType;
import PlatformGameShared.Points.Vector2;
import models.classes.GameObject;

public class ProjectileBullet extends Projectile {
    public static float bulletAcceleration = 10;
    public ProjectileBullet(float xPosition, float yPosition, float width, float height, Player owner) {
        super(xPosition, yPosition, width, height,30, owner);
        setUseGravity(false);
    }

    @Override
    public void update(){
        super.update();
    }

    @Override
    public void onCollide(GameObject other, Vector2 collidePoint) {
        super.onCollide(other,collidePoint);
    }

    @Override
    public SpriteType getSpriteType() {return SpriteType.PROJECTILEBULLET;}


}
