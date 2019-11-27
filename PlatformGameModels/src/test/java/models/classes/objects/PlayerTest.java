package models.classes.objects;

import Enums.InputType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    Player player;

    @Before
    public void setUp(){
        player = new Player(20,20);
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
    }

    @Test
    public void getCurrentWeapon() {
    }

    @Test
    public void getWalkAcceleration() {
    }

    @Test
    public void setWalkAcceleration() {
    }

    @Test
    public void update() {
    }
}