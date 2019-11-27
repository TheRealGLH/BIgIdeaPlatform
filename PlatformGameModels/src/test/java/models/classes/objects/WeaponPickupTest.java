package models.classes.objects;

import models.enums.WeaponType;
import org.junit.Test;
import org.junit.Assert;

public class WeaponPickupTest{

    WeaponPickup pickup;

    @Test
    public void testPickUp() {
        WeaponType expected = WeaponType.GUN;
        Player player = new Player(0,0);
        pickup = new WeaponPickup(40,40,expected);
        pickup.pickUp(player);
        WeaponType actual = player.getCurrentWeapon();
        Assert.assertEquals(expected,actual);
    }


    @Test
    public void testGetWeaponType() {
    }

    @Test
    public void testOnCollidePlayer() {
    }

    @Test
    public void testOnCollideNotPlayer(){

    }
}
