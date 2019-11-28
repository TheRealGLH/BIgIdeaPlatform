package models.classes.objects;

import SharedClasses.Vector2;
import models.classes.MockGame;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProjectileTest {


    MockGame game = new MockGame();
    Projectile projectile;
    int maxLife = 30;

    @Before
    public void setUp(){
        game.reset();
        projectile = new MockProjectile(10,10,10,10,maxLife);
        game.addObject(projectile);
    }

    @Test
    public void updateIncreaseLife() {
        int expectedLife = 1;
        game.update();
        int actualLife = projectile.getCurrentLife();
        Assert.assertEquals(expectedLife,actualLife);
    }

    @Test
    public void updateExceedMaxLife(){
        for (int i = 0; i <= maxLife; i++) {
            game.update();
        }
        Assert.assertFalse(game.objectStillInList(projectile));
    }

    @Test
    public void onCollidePlayerKill() {
        //using these positions will ensure that we dont start by hitting the projectile
        Vector2 playerStartPoint = new Vector2(projectile.getPosition().getX() + 20, projectile.getPosition().getY() + 20);
        Player player = new Player(playerStartPoint.getX(),playerStartPoint.getY());
        game.addObject(player);
        Vector2 projPos = projectile.getPosition();
        player.setPosition(projPos.getX(),projPos.getY());
        game.update();
        Vector2 actual = player.getPosition();
        Assert.assertEquals(playerStartPoint,actual);
    }
}

class MockProjectile extends Projectile{

    public MockProjectile(float xPosition, float yPosition, float width, float height, int maxLife) {
        super(xPosition, yPosition, width, height, maxLife, null);
    }
}