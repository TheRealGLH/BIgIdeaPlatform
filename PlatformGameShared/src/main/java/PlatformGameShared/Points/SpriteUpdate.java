package PlatformGameShared.Points;

import PlatformGameShared.Enums.SpriteType;
import PlatformGameShared.Enums.SpriteUpdateType;

public class SpriteUpdate {

    private int objectNr;
    private Vector2 position;
    private Vector2 size;
    private SpriteUpdateType updateType;
    private SpriteType spriteType;
    private boolean isFacingLeft;
    private String label;

    public SpriteUpdate(int objectNr, Vector2 position, Vector2 size, SpriteUpdateType updateType, SpriteType spriteType, boolean isFacingLeft, String label) {
        this.objectNr = objectNr;
        this.position = position;
        this.size = size;
        this.updateType = updateType;
        this.spriteType = spriteType;
        this.isFacingLeft = isFacingLeft;
        this.label = label;
    }

    public SpriteUpdate(int objectNr, Vector2 position, Vector2 size, SpriteUpdateType updateType, SpriteType spriteType, boolean isFacingLeft){
        this(objectNr,position,size,updateType,spriteType,isFacingLeft,"");
    }

    public SpriteUpdate(){}


    public int getObjectNr() {
        return objectNr;
    }

    public Vector2 getPosition() {
        return new Vector2(position);
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

    public String getLabel(){ return label;}

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    public void setUpdateType(SpriteUpdateType updateType) {
        this.updateType = updateType;
    }

    public void setSpriteType(SpriteType spriteType) {
        this.spriteType = spriteType;
    }

    public void setFacingLeft(boolean facingLeft) {
        isFacingLeft = facingLeft;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setObjectNr(int objectNr) {
        this.objectNr = objectNr;
    }

    @Override
    public boolean equals(Object other){
        if(! (other instanceof SpriteUpdate)) return false;
        SpriteUpdate update = (SpriteUpdate) other;
        if(this.objectNr == update.getObjectNr()) return true;
        return false;
    }

    @Override
    public String toString(){
        return "SpriteUpdate: "+updateType+" "+spriteType + " nr" + objectNr;
    }

}
