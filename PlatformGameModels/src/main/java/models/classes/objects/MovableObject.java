package models.classes.objects;

import models.classes.GameObject;

public abstract class MovableObject extends GameObject {
    public MovableObject(float xPosition, float yPosition, float width, float height) {
        super(xPosition, yPosition, width, height);
    }

    public void update(){
        throw new UnsupportedOperationException("MovableObject update() not yet implemented");
    }
}
