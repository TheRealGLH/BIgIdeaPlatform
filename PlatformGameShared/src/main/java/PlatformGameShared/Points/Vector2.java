package PlatformGameShared.Points;

public class Vector2 {

    private float x;
    private float y;

    /**
     * @param x The X coordinate
     * @param y The Y coordinate
     */
    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 vector2){
        this.x = vector2.getX();
        this.y = vector2.getY();
    }

    public Vector2(){}

    /**
     * @return The X coordinate
     */
    public float getX() {
        return x;
    }

    /**
     * @param x The X coordinate to set
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * @return The Y coordinate
     */
    public float getY() {
        return y;
    }

    /**
     * @param y The Y coordinate to set
     */
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        Vector2 vector2 = (Vector2) other;
        if (other == null) return false;
        if (vector2.getX() == this.x && vector2.getY() == this.y) return true;

        return false;
    }

    /**
     * Calculates the midpoint between two vectors
     * @param other The other vector to compare it to
     * @return The midpoint
     */
    public Vector2 midpoint(Vector2 other) {
        float x = (this.x + other.getX()) / 2;
        float y = (this.y + other.getY()) / 2;

        return new Vector2(x, y);
    }

    public static Vector2 Zero(){
        return new Vector2(0,0);
    }

    @Override
    public String toString(){
        return "{"+x+","+y+"}";
    }
}
