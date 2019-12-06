package models.classes.objects;

import PlatformGameShared.Enums.SpriteType;
import PlatformGameShared.Points.Vector2;
import models.classes.GameObject;

public class Platform extends GameObject {

    private boolean isSolid;
    public Platform(float xPosition, float yPosition, float width, float height, boolean isSolid) {
        super(xPosition, yPosition, width, height);
        this.isSolid = isSolid;
    }

    public boolean isSolid(){
        return isSolid;
    }

    public void setSolid(boolean isSolid){
        this.isSolid = isSolid;
    }

    @Override
    public boolean collidesWith(GameObject other) {
        if(!isSolid) return isObjectOnTop(other);
        return super.collidesWith(other);
    }

    @Override
    public SpriteType getSpriteType() {return SpriteType.PLATFORM;}

    private boolean isObjectOnTop(GameObject other){
        //wanneer sta je ergens op?
        //other origin x is inclusief binnen platform origin x en topright x
        //other origin y is inclusief binnen platform origin y en topright y
        //other topright is niet binnen deze waarden
        Vector2 otherOrigin = other.getPosition();
        float  otherTopRight = other.getTopRight().getY();
        Vector2 pOrigin = getPosition();
        Vector2 pTopRight = getTopRight();
        return (between(pOrigin.getX(),pTopRight.getX(),otherOrigin.getX())&&between(pOrigin.getY(),pTopRight.getY(),otherOrigin.getY())&&otherTopRight> pTopRight.getY());
    }
}
