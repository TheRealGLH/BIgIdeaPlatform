package models.classes.objects;

import PlatformGameShared.Enums.SpriteType;
import PlatformGameShared.Points.Vector2;
import models.classes.MockGame;
import models.classes.objects.projectiles.Projectile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProjectileTest {


    MockGame game = new MockGame();
    Projectile projectile;
    int maxLife = 30;

    @Before
    public void setUp() {
        System.out.println("Setting up a Projectile test");
        game.reset();
        projectile = new MockProjectile(10, 10, 10, 10, maxLife);
        game.addObject(projectile);
    }

    @Test
    public void updateIncreaseLife() {
        System.out.println("Testing Projectile life increase");
        int expectedLife = 1;
        game.update();
        int actualLife = projectile.getCurrentLife();
        Assert.assertEquals(expectedLife, actualLife);
    }

    @Test
    public void updateExceedMaxLife() {
        System.out.println("Testing Projectile reaching its max life");
        for (int i = 0; i <= maxLife; i++) {
            game.update();
        }
        Assert.assertFalse(game.objectStillInList(projectile));
    }

    @Test
    public void onCollidePlayerKill() {
        System.out.println("Testing Projectile killing player on contact");
        //using these positions will ensure that we dont start by hitting the projectile
        Vector2 playerStartPoint = new Vector2(projectile.getPosition().getX() + 20, projectile.getPosition().getY() + 20);
        Player player = new Player(playerStartPoint.getX(), playerStartPoint.getY());
        int expectedLives = player.getCurrentLives() - 1;
        player.setUseGravity(false);
        game.addObject(player);
        Vector2 projPos = projectile.getPosition();
        player.setPosition(projPos.getX(), projPos.getY());
        game.update();
        int actualLives = player.getCurrentLives();
        Assert.assertEquals(expectedLives, actualLives);
    }
}

class MockProjectile extends Projectile {

    public MockProjectile(float xPosition, float yPosition, float width, float height, int maxLife) {
        super(xPosition, yPosition, width, height, maxLife, null);
    }

    @Override
    public SpriteType getSpriteType() {
        return SpriteType.NONE;
    }

    @Override
    public Projectile clone() {
        throw new UnsupportedOperationException("The method <> has not yet been implemented");
    }
}