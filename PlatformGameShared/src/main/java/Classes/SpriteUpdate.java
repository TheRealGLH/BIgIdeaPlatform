package Classes;

import Enums.SpriteType;
import Enums.SpriteUpdateType;

public class SpriteUpdate {

    private Vector2 position;
    private Vector2 size;
    private SpriteUpdateType updateType;
    private SpriteType spriteType;
    private boolean isFacingLeft;

    public SpriteUpdate(int objectNr, Vector2 position, Vector2 size, SpriteUpdateType updateType, SpriteType spriteType, boolean isFacingLeft) {
        this.objectNr = objectNr;
        this.position = position;
        this.size = size;
        this.updateType = updateType;
        this.spriteType = spriteType;
        this.isFacingLeft = isFacingLeft;
    }

    private int objectNr;

    public int getObjectNr() {
        return objectNr;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getSize() {
        return size;
    }

    public SpriteUpdateType getUpdateType() {
        return updateType;
    }

    public SpriteType getSpriteType() {
        return spriteType;
    }

    public boolean isFacingLeft() {
        return isFacingLeft;
    }

    @Override
    public boolean equals(Object other){
        if(! (other instanceof SpriteUpdate)) return false;
        SpriteUpdate update = (SpriteUpdate) other;
        if(this.objectNr == update.getObjectNr()) return true;
        return false;
    }

}
