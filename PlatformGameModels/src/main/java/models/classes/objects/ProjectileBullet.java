package models.classes.objects;

import Enums.SpriteType;
import SharedClasses.Vector2;
import models.classes.GameObject;

public class ProjectileBullet extends Projectile {
    public ProjectileBullet(float xPosition, float yPosition, float width, float height, Player owner) {
        super(xPosition, yPosition, width, height,30, owner);
    }

    @Override
    public void onCollide(GameObject other, Vector2 collidePoint) {
        super.onCollide(other,collidePoint);
    }

    @Override
    public SpriteType getSpriteType() {return SpriteType.BULLET;}


}
