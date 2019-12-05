package models.classes.objects;

import PlatformGameShared.Enums.InputType;
import PlatformGameShared.Points.Vector2;
import models.enums.WeaponType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

    Player player;

    @Before
    public void setUp() {
        System.out.println("Setting up a Player test...");
        player = new Player(20, 20);
    }

    @Test
    public void handleInputMoveLeft() {
        player.handleInput(InputType.MOVELEFT);

    }

    @Test
    public void useWeapon() {
    }

    @Test
    public void jump() {
    }

    @Test
    public void setCurrentWeapon() {
        System.out.println("Testing Player setting weapontype");
        WeaponType expected = WeaponType.GUN;
        player.setCurrentWeapon(expected);
        WeaponType actual = player.getCurrentWeapon();
        Assert.assertEquals(expected, actual);
    }



    @Test
    public void setWalkAcceleration() {
        System.out.println("Testing Player setting walk acceleration");
        float expected = player.getWalkAcceleration() + 30;
        player.setWalkAcceleration(expected);
        float actual = player.getWalkAcceleration();
        Assert.assertEquals(expected,actual,0);
    }


    @Test
    public void killTestPosition() {
        System.out.println("Testing Player resetting position on death");
        Vector2 expected = player.getPosition();
        player.setPosition(expected.getX() + 30, expected.getY() + 40);
        player.Kill();
        Assert.assertEquals(expected, player.getPosition());
    }

    @Test
    public void killTestAcceleration() {
        System.out.println("Testing Player resetting acceleration on death");
        Vector2 expected = player.getAcceleration();
        player.setAcceleration(expected.getX() + 30, expected.getY() + 40);
        player.Kill();
        Assert.assertEquals(expected, player.getAcceleration());
    }

    @Test
    public void killTestVelocity() {
        System.out.println("Testing Player resetting velocity on death");
        Vector2 expected = player.getVelocity();
        player.setVelocity(expected.getX() + 30, expected.getY() + 40);
        player.Kill();
        Assert.assertEquals(expected, player.getVelocity());
    }
}