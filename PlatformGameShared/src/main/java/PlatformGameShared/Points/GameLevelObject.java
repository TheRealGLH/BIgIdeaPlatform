package PlatformGameShared.Points;

public class GameLevelObject {

    public enum KindEnum{
        platform,
        playerspawn,
        weaponspawn
    }

    private KindEnum kind;
    private int xpos;
    private int ypos;
    private int width;
    private int height;
    private boolean solid;

    public GameLevelObject(KindEnum kind, int xpos, int ypos, int width, int height, boolean solid) {
        this.kind = kind;
        this.xpos = xpos;
        this.ypos = ypos;
        this.width = width;
        this.height = height;
        this.solid = solid;
    }

    public GameLevelObject(){}

    public KindEnum getKind() {
        return kind;
    }

    public void setKind(KindEnum kind) {
        this.kind = kind;
    }

    public int getXpos() {
        return xpos;
    }

    public void setXpos(int xpos) {
        this.xpos = xpos;
    }

    public int getYpos() {
        return ypos;
    }

    public void setYpos(int ypos) {
        this.ypos = ypos;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isSolid() {
        return solid;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }
}
