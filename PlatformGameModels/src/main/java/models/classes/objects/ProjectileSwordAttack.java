package models.classes.objects;

import PlatformGameShared.Enums.SpriteType;
import PlatformGameShared.Points.Vector2;
import models.classes.GameObject;

public class ProjectileSwordAttack extends Projectile {

    private static float sizeMod = 1.5f;
    Player owner;
    public ProjectileSwordAttack(float xPosition, float yPosition, Player owner) {
        super(xPosition, yPosition, owner.getSize().getX()*sizeMod, owner.getSize().getY()*sizeMod, 5, owner);
        setUseGravity(false);
        this.owner = owner;
    }

    @Override
    public void update(){
        Vector2 pos = owner.getPosition();
        this.setPosition(pos.getX(),pos.getY());
        super.update();
    }

    @Override
    public SpriteType getSpriteType() {
        return SpriteType.NONE;
    }
}
