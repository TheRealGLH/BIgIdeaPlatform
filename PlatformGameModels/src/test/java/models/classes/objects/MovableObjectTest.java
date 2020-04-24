package models.classes.objects;

import PlatformGameShared.Enums.SpriteType;
import PlatformGameShared.Points.Vector2;
import models.classes.GameObject;
import models.classes.MockGame;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class MovableObjectTest {

    MockMovableObject movableObject;

    @Before
    public void setMovableObject() {
        System.out.println("Setting up a MovableObject test...");
        movableObject = new MockMovableObject(50, 50, 20, 20);
    }

    @Test
    public void setAcceleration() {
        System.out.println("Testing MovableObject acceleration setter");
        float x = 3;
        float y = 0;
        movableObject.setAcceleration(x, y);
        Assert.assertEquals(new Vector2(x, y), movableObject.getAcceleration());
    }

    @Test
    public void testAccelerationUpward() {
        System.out.println("Testing MovableObject getting upward acceleration");
        float x = 3;
        float y = 5;
        movableObject.setAcceleration(x, y);
        float actual = movableObject.getAcceleration(true);
        Assert.assertEquals(y, actual, 1);
    }

    @Test
    public void testAccelerationSideways() {
        System.out.println("Testing MovableObject getting sideways acceleration");
        float x = 3;
        float y = 5;
        movableObject.setAcceleration(x, y);
        float actual = movableObject.getAcceleration(false);
        Assert.assertEquals(x, actual, 1);
    }

    @Test
    public void testVelocityUpward() {
        System.out.println("Testing MovableObject getting upward velocity");
        float x = 3;
        float y = 5;
        movableObject.setVelocity(x, y);
        float actual = movableObject.getVelocity(true);
        Assert.assertEquals(y, actual, 1);
    }

    @Test
    public void testVelocitySideways() {
        System.out.println("Testing MovableObject getting sideways velocity");
        float x = 3;
        float y = 5;
        movableObject.setVelocity(x, y);
        float actual = movableObject.getVelocity(false);
        Assert.assertEquals(x, actual, 1);
    }

    @Test
    public void addAcceleration() {
        System.out.println("Testing MovableObject adding acceleration");
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
        System.out.println("Testing MovableObject position change after velocity change");
        float x = 10;
        float y = 15;
        float expectedX = movableObject.getPosition().getX() + x;
        float expectedY = movableObject.getPosition().getY() + y;
        Vector2 expected = new Vector2(expectedX, expectedY);
        movableObject.setVelocity(x, y);
        movableObject.setUseGravity(false);
        movableObject.update();
        Vector2 actual = movableObject.getPosition();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testPositionChangeAfterAcceleration() {
        System.out.println("Testing MovableObject position change after acceleration change");
        float xAcc = 5;
        float yAcc = 12;
        Vector2 expected = new Vector2(movableObject.getPosition().getX() + xAcc, movableObject.getPosition().getY() + yAcc);
        movableObject.setAcceleration(xAcc, yAcc);
        movableObject.setUseGravity(false);
        movableObject.update();
        Vector2 actual = movableObject.getPosition();
        Assert.assertEquals(expected, actual);
    }

    @Ignore("testPositionChangeAfterAccelerationMultipleUpdates: Will redo this after proper grounded implementation")
    @Test
    public void testPositionChangeAfterAccelerationMultipleUpdates() {
        System.out.println("Testing MovableObject position change after acceleration change");
        int amountOfUpdates = 10;
        float xAcc = 5;
        float yAcc = 12;
        float xExpected = xAcc * amountOfUpdates + movableObject.getPosition().getX();
        float yExpected = yAcc * amountOfUpdates + movableObject.getPosition().getY();
        movableObject.setFriction(1);
        movableObject.setAcceleration(xAcc, yAcc);
        for (int i = 0; i < amountOfUpdates; i++) {
            movableObject.update();
        }

        Vector2 expected = new Vector2(xExpected,yExpected);
        Vector2 actual = movableObject.getPosition();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void showFrictionEffect(){
        System.out.println("Showing MovableObject friction effect");
        movableObject.setUseGravity(false);
        movableObject.setVelocity(40,0);
        for (int i = 0; i < 40; i++) {
            System.out.println("Velocity: "+movableObject.getVelocity(false));
            movableObject.update();
        }
    }


    @Test
    public void setVelocity() {
        System.out.println("Testing MovableObject manually setting velocity");
        Vector2 expected = new Vector2(10, 10);
        movableObject.setVelocity(expected.getX(), expected.getY());
        Vector2 actual = movableObject.getVelocity();
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void notUsingGravitySetter() {
        System.out.println("Testing MovableObject disabling gravity");
        boolean expected = false;
        movableObject.setUseGravity(expected);
        Assert.assertEquals(expected, movableObject.isUseGravity());
    }

    @Test
    public void notFallingNoGravity() {
        System.out.println("Testing MovableObject not falling when there is no gravity");
        movableObject.setUseGravity(false);
        Vector2 expected = new Vector2(movableObject.getPosition());
        movableObject.update();
        Vector2 actual = new Vector2(movableObject.getPosition());
        Assert.assertEquals(expected, actual);
    }


    @Ignore("MovableObject notFallingGrounded test skipped until grounded implementation will be determined")
    @Test
    public void notFallingGrounded() {
        System.out.println("Testing MovableObject not falling when its grounded");
        movableObject.setPosition(40, 40);
        Vector2 expected = new Vector2(movableObject.getPosition());
        movableObject.update();
        Vector2 actual = new Vector2(movableObject.getPosition());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testObjectAdding() {
        System.out.println("Testing MockGame adding of object");
        MockGame game = new MockGame();
        game.addObject(movableObject);
        Assert.assertTrue(game.objectStillInList(movableObject));
    }

    @Test
    public void kill() {
        System.out.println("Testing MovableObject deletion");
        MockGame game = new MockGame();
        game.addObject(movableObject);
        movableObject.markForDeletion();
        game.update();
        Assert.assertFalse(game.objectStillInList(movableObject));
    }

    @Test
    public void isShouldBeCleaned() {
        System.out.println("Testing MovableObject marking for cleaning.");
        movableObject.markForDeletion();
        Assert.assertTrue(movableObject.isShouldBeCleaned());
    }

    @Test
    public void hasCollided(){
        MockMovableObject object2 = new MockMovableObject(50, 50, 20, 20);
        if(movableObject.collidesWith(object2)) movableObject.onCollide(object2,movableObject.getPosition());
        Assert.assertTrue(movableObject.isHasCollided());

    }
}

class MockMovableObject extends MovableObject {

    private boolean hasCollided = false;

    public MockMovableObject(float xPosition, float yPosition, float width, float height) {
        super(xPosition, yPosition, width, height);
    }

    @Override
    public SpriteType getSpriteType() {
        return SpriteType.NONE;
    }

    @Override
    public void onCollide(GameObject other, Vector2 collidePoint) {
        hasCollided = true;
    }

    public boolean isHasCollided() {
        return hasCollided;
    }
}