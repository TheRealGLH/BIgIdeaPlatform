package models.classes;

import SharedClasses.Vector2;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class GameObjectTest {

    GameObject mock;
    GameObject collide;



    @Test
    public void collidesWithSameSpace() {
        float x = 2;
        float y = 3;
        float width = 10;
        float height = 20;
        mock = new GameObjectMock(x,y,width,height);
        collide = new GameObjectMock(x,y,width,height);
        Assert.assertTrue(mock.collidesWith(collide));
    }

    @Test
    public void collidesWithOriginPoint(){
        mock = new GameObjectMock(5,5,10,10);
        collide = new GameObjectMock(0,0,10,10);
        Assert.assertTrue(mock.collidesWith(collide));
    }


    @Test
    public void collidesWithTopRight(){
        mock = new GameObjectMock(0,0,10,10);
        collide = new GameObjectMock(5,5,10,10);
        Assert.assertTrue(mock.collidesWith(collide));
    }

    @Test
    public void collisionXCoordinatesMatchOrigin(){
        //test when origin x coords are in bounds but y coords aren't
        mock = new GameObjectMock(0,0,10,10);
        collide = new GameObjectMock(5,15,10,10);
        Assert.assertFalse(mock.collidesWith(collide));
    }

    @Test
    public void collisionYCoordinatesMatchOrigin(){
        //test when origin y coords are in bounds but x coords aren't
        mock = new GameObjectMock(0,0,10,10);
        collide = new GameObjectMock(15,5,10,10);
        Assert.assertFalse(mock.collidesWith(collide));
    }

    @Test
    public void collisionXCoordinatesMatchTopRight(){
        //test when top right x coords are in bounds but y coords aren't
        mock = new GameObjectMock(5,15,10,10);
        collide = new GameObjectMock(0,0,10,10);
        Assert.assertFalse(mock.collidesWith(collide));
    }

    @Test
    public void collisionYCoordinatesMatchTopRight(){
        //test when top right y coords are in bounds but x coords aren't
        mock = new GameObjectMock(15,5,10,10);
        collide = new GameObjectMock(0,0,10,10);
        Assert.assertFalse(mock.collidesWith(collide));
    }

    @Test
    public void collisionNoPointsInSquare(){
        mock = new GameObjectMock(0,0,10,10);
        collide = new GameObjectMock(15,15,10,10);
        Assert.assertFalse(mock.collidesWith(collide));
    }




    @Test
    public void getPosition() {
        float x = 3;
        float y = 17;
        Vector2 expected = new Vector2(x,y);
        mock = new GameObjectMock(x,y,5,5);
        Assert.assertEquals(expected,mock.getPosition());
    }

    @Test
    public void getSize() {
        float x = 3;
        float y = 17;
        Vector2 expected = new Vector2(x,y);
        mock = new GameObjectMock(14,40,x,y);
        Assert.assertEquals(expected,mock.getSize());
    }
}


/**
 * Mock class used for testing, due to GameObject's abstract nature
 */
class GameObjectMock extends GameObject {
    public GameObjectMock(float xPosition, float yPosition, float width, float height) {
        super(xPosition, yPosition, width, height);
    }
}