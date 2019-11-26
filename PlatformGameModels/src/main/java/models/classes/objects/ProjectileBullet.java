package models.classes.objects;

import SharedClasses.Vector2;
import models.classes.GameObject;

public class ProjectileBullet extends Projectile {
    public ProjectileBullet(float xPosition, float yPosition, float width, float height) {
        super(xPosition, yPosition, width, height);
    }

    @Override
    public void onCollide(GameObject other, Vector2 collidePoint) {
        throw new UnsupportedOperationException("Method onCollide has not yet been implemented");
    }
}
