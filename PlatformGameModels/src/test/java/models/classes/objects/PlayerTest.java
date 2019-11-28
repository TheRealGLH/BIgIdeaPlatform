package models.classes.objects;

import Enums.InputType;
import SharedClasses.Vector2;
import models.enums.WeaponType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    Player player;

    @Before
    public void setUp() {
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
        WeaponType expected = WeaponType.GUN;
        player.setCurrentWeapon(expected);
        WeaponType actual = player.getCurrentWeapon();
        Assert.assertEquals(expected, actual);
    }



    @Test
    public void setWalkAcceleration() {
        float expected = player.getWalkAcceleration() + 30;
        player.setWalkAcceleration(expected);
        float actual = player.getWalkAcceleration();
        Assert.assertEquals(expected,actual,0);
    }


    @Test
    public void killTestPosition() {
        Vector2 expected = player.getPosition();
        player.setPosition(expected.getX() + 30, expected.getY() + 40);
        player.Kill();
        Assert.assertEquals(expected, player.getPosition());
    }

    @Test
    public void killTestAcceleration() {
        Vector2 expected = player.getAcceleration();
        player.setAcceleration(expected.getX() + 30, expected.getY() + 40);
        player.Kill();
        Assert.assertEquals(expected, player.getAcceleration());
    }

    @Test
    public void killTestVelocity() {
        Vector2 expected = player.getVelocity();
        player.setVelocity(expected.getX() + 30, expected.getY() + 40);
        player.Kill();
        Assert.assertEquals(expected, player.getVelocity());
    }
}