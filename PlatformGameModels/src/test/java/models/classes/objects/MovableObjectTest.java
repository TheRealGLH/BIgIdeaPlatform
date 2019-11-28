package models.classes.objects;

import SharedClasses.Vector2;
import models.classes.GameObject;
import models.classes.MockGame;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
    public void getAccelerationUpward() {
        float x = 3;
        float y = 5;
        movableObject.setAcceleration(x, y);
        float actual = movableObject.getAcceleration(true);
        Assert.assertEquals(y, actual, 1);
    }

    @Test
    public void getAccelerationSideways() {
        float x = 3;
        float y = 5;
        movableObject.setAcceleration(x, y);
        float actual = movableObject.getAcceleration(false);
        Assert.assertEquals(x, actual, 1);
    }

    @Test
    public void getVelocityUpward() {
        float x = 3;
        float y = 5;
        movableObject.setVelocity(x, y);
        float actual = movableObject.getVelocity(true);
        Assert.assertEquals(y, actual, 1);
    }

    @Test
    public void getVelocitySideways() {
        float x = 3;
        float y = 5;
        movableObject.setVelocity(x, y);
        float actual = movableObject.getVelocity(false);
        Assert.assertEquals(x, actual, 1);
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
        Vector2 expected = new Vector2(expectedX, expectedY);
        movableObject.setVelocity(x, y);
        movableObject.update();
        Vector2 actual = movableObject.getPosition();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testPositionChangeAfterAcceleration() {

        float xAcc = 5;
        float yAcc = 12;
        movableObject.setAcceleration(xAcc, yAcc);
        movableObject.update();
        Vector2 expected = Vector2.Zero();
        Vector2 actual = movableObject.getPosition();
        Assert.assertEquals(expected,actual);
        throw new UnsupportedOperationException("This test needs to be finished to determine what the position is " +
                "going to be");
    }


    @Test
    public void setVelocity() {
        Vector2 expected = new Vector2(10, 10);
        movableObject.setVelocity(expected.getX(), expected.getY());
        Vector2 actual = movableObject.getVelocity();
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void notUsingGravitySetter() {
        boolean expected = false;
        movableObject.setUseGravity(expected);
        Assert.assertEquals(expected,movableObject.isUseGravity());
    }

    @Test
    public void notFallingNoGravity(){
        movableObject.setUseGravity(false);
        Vector2 expected = new Vector2(movableObject.getPosition());
        movableObject.update();
        Vector2 actual = new Vector2(movableObject.getPosition());
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testObjectAdding(){
        MockGame game = new MockGame();
        game.addObject(movableObject);
        Assert.assertTrue(game.objectStillInList(movableObject));
    }

    @Test
    public void kill() {
        MockGame game = new MockGame();
        game.addObject(movableObject);
        movableObject.Delete();
        game.update();
        Assert.assertFalse(game.objectStillInList(movableObject));
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