package models.classes.objects;

import SharedClasses.Vector2;
import models.classes.GameObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovableObjectTest {

    MovableObject movableObject;

    @Before
    public void setMovableObject() {
        movableObject = new MockMovableObject(50, 50, 20, 20);
    }

    @Test
    public void setAcceleration() {
        float x = 3;
        float y = 0;
        movableObject.setAcceleration(x, y);
        Assert.assertEquals(new Vector2(x, y), movableObject.getAcceleration());
    }

    @Test
    public void addAcceleration() {
        float x = 3;
        float y = 0;
        float xToAdd = 10;
        float yToAdd = 0;
        movableObject.setAcceleration(x, y);
        movableObject.addAcceleration(xToAdd, yToAdd);
        Assert.assertEquals(new Vector2(x + xToAdd, y + yToAdd), movableObject.getAcceleration());
    }


    @Test
    public void testPositionChangeAfterSpeedChange() {
        float x = 10;
        float y = 15;
        float expectedX = movableObject.getPosition().getX() + x;
        float expectedY = movableObject.getPosition().getY() + y;
        Vector2 expected = new Vector2(expectedX,expectedY);
        movableObject.setVelocity(x,y);
        movableObject.update();
        Vector2 actual = movableObject.getPosition();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testPositionChangeAfterAcceleration() {

    }


    @Test
    public void getVelocity() {
    }
}

class MockMovableObject extends MovableObject {

    public MockMovableObject(float xPosition, float yPosition, float width, float height) {
        super(xPosition, yPosition, width, height);
    }

    @Override
    public void onCollide(GameObject other, Vector2 collidePoint) {
        //Does nothing
    }
}