package models.classes;

import SharedClasses.Vector2;

public abstract class GameObject {
    private Vector2 position;
    private Vector2 size;

    public GameObject(float xPosition, float yPosition, float width, float height) {
        position = new Vector2(xPosition, yPosition);
        size = new Vector2(width, height);
    }


    public boolean collidesWith(GameObject other) {
        if (this.equals(other)) return false;
        Vector2 otherTopRight = other.getTopRight();
        if (between(position, getTopRight(), other.getPosition())) return true;
        if (between(position, getTopRight(), other.getTopRight())) return true;
        return false;
    }


    public Vector2 getPosition() {
        return new Vector2(position);
    }

    public void setPosition(float x, float y) {
        position.setX(x);
        position.setY(y);
    }

    public Vector2 getSize() {
        return size;
    }

    public Vector2 getTopRight() {
        float x = position.getX() + size.getX();
        float y = position.getY() + size.getY();
        return new Vector2(x, y);
    }

    private static boolean between(Vector2 origin, Vector2 topRight, Vector2 toCompare) {
        return between(origin.getX(), topRight.getY(), toCompare.getX()) && between(origin.getY(), topRight.getY(), toCompare.getY());
    }

    private static boolean between(float min, float max, float toCompare) {
        return (toCompare >= min && toCompare <= max);
    }
}
