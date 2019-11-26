package models.classes;

import SharedClasses.Vector2;

public abstract class GameObject {
    private Vector2 position;
    private Vector2 size;

    public GameObject(float xPosition, float yPosition, float width, float height){
        position = new Vector2(xPosition,yPosition);
        size = new Vector2(width,height);
    }



    public boolean collidesWith(GameObject other){
        throw new UnsupportedOperationException("The method collidesWith() has not yet been implemented");
    }

    public abstract void onCollide(GameObject other, Vector2 collidePoint);

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getSize() {
        return size;
    }
}
