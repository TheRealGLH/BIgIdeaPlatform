package models.classes.objects;

import Enums.SpriteType;
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
    public SpriteType getSpriteType() {return SpriteType.PLATFORM;}
}
