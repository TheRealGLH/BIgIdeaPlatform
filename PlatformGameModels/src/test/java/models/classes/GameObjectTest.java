package models.classes;

import PlatformGameShared.Enums.SpriteType;
import PlatformGameShared.Points.Vector2;
import models.classes.objects.Player;
import models.classes.objects.projectiles.ProjectileBullet;
import org.junit.Assert;
import org.junit.Test;

public class GameObjectTest {

    GameObject mock;
    GameObject collide;


    @Test
    public void collidesWithSameSpace() {
        System.out.println("Testing GameObject collision with same space");
        float x = 2;
        float y = 3;
        float width = 10;
        float height = 20;
        mock = new GameObjectMock(x, y, width, height);
        collide = new GameObjectMock(x, y, width, height);
        Assert.assertTrue(mock.collidesWith(collide));
    }

    @Test
    public void collidesWithOriginPoint() {
        System.out.println("Testing GameObject collision with origin point");
        mock = new GameObjectMock(5, 5, 10, 10);
        collide = new GameObjectMock(0, 0, 10, 10);
        Assert.assertTrue(mock.collidesWith(collide));
    }


    @Test
    public void collidesWithTopRight() {
        System.out.println("Testing GameObject collision with top right");
        mock = new GameObjectMock(0, 0, 10, 10);
        collide = new GameObjectMock(5, 5, 40, 40);
        Assert.assertTrue(mock.collidesWith(collide));
    }

    @Test
    public void collisionXCoordinatesMatchOrigin() {
        System.out.println("Testing GameObject collision with only X coordinate in origin");
        //test when origin x coords are in bounds but y coords aren't
        mock = new GameObjectMock(0, 0, 10, 10);
        collide = new GameObjectMock(5, 15, 10, 10);
        Assert.assertFalse(mock.collidesWith(collide));
    }

    @Test
    public void collisionYCoordinatesMatchOrigin() {
        System.out.println("Testing GameObject collision with only Y coordinate in origin");
        //test when origin y coords are in bounds but x coords aren't
        mock = new GameObjectMock(0, 0, 10, 10);
        collide = new GameObjectMock(15, 5, 10, 10);
        Assert.assertFalse(mock.collidesWith(collide));
    }

    @Test
    public void collisionXCoordinatesMatchTopRight() {
        System.out.println("Testing GameObject collision with only X coordinate in top right");
        //test when top right x coords are in bounds but y coords aren't
        mock = new GameObjectMock(5, 15, 10, 10);
        collide = new GameObjectMock(0, 0, 10, 10);
        Assert.assertFalse(mock.collidesWith(collide));
    }

    @Test
    public void collisionYCoordinatesMatchTopRight() {
        System.out.println("Testing GameObject collision with only Y coordinate in top right");
        //test when top right y coords are in bounds but x coords aren't
        mock = new GameObjectMock(15, 5, 10, 10);
        collide = new GameObjectMock(0, 0, 10, 10);
        Assert.assertFalse(mock.collidesWith(collide));
    }

    @Test
    public void collisionNoPointsInSquare() {
        System.out.println("Testing GameObject collision with no points in square");
        mock = new GameObjectMock(0, 0, 10, 10);
        collide = new GameObjectMock(15, 15, 10, 10);
        Assert.assertFalse(mock.collidesWith(collide));
    }

    @Test
    public void collisionSameInstance() {
        mock = new GameObjectMock(10, 10, 10, 10);
        Assert.assertFalse(mock.collidesWith(mock));
    }


    @Test
    public void getPosition() {
        System.out.println("Testing GameObject position getter");
        float x = 3;
        float y = 17;
        Vector2 expected = new Vector2(x, y);
        mock = new GameObjectMock(x, y, 5, 5);
        Assert.assertEquals(expected, mock.getPosition());
    }

    @Test
    public void getSize() {
        System.out.println("Testing GameObject size getter");
        float x = 3;
        float y = 17;
        Vector2 expected = new Vector2(x, y);
        mock = new GameObjectMock(14, 40, x, y);
        Assert.assertEquals(expected, mock.getSize());
    }

    @Test
    public void testTopRight() {
        System.out.println("Testing GameObject calculating top right");
        float width = 20;
        float height = 30;
        float x = 10;
        float y = 15;
        Vector2 expected = new Vector2(x + width, y + height);
        mock = new GameObjectMock(x, y, width, height);
        Vector2 actual = mock.getTopRight();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testPractical(){
        Player player = new Player(350,320);
        ProjectileBullet pickup = new ProjectileBullet(343.429f,324f,null);
        boolean status = pickup.collidesWith(player);
        Assert.assertTrue(status);


    }


}


/**
 * Mock class used for testing, due to GameObject's abstract nature
 */
class GameObjectMock extends GameObject {
    public GameObjectMock(float xPosition, float yPosition, float width, float height) {
        super(xPosition, yPosition, width, height);
    }

    @Override
    public SpriteType getSpriteType() {
        return SpriteType.NONE;
    }
}