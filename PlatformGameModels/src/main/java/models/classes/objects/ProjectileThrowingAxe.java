package models.classes.objects;

import PlatformGameShared.Enums.SpriteType;
import PlatformGameShared.Points.Vector2;
import models.classes.GameObject;

public class ProjectileThrowingAxe extends Projectile {
    public ProjectileThrowingAxe(float xPosition, float yPosition,   Player owner) {
        super(xPosition, yPosition, 30, 30, 60, owner);
        setMaxHorizontalVelocity(40);
    }

    @Override
    public void onCollide(GameObject other, Vector2 pos){
        if(!isGrounded()) super.onCollide(other,pos);
    }

    @Override
    public SpriteType getSpriteType() {
        return SpriteType.AXE;
    }
}
