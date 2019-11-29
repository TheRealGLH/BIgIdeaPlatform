package models.classes.objects;

import models.enums.WeaponType;
import org.junit.Assert;
import org.junit.Test;

public class WeaponPickupTest{

    WeaponPickup pickup;

    @Test
    public void testPickUp() {
        System.out.println("Testing WeaponPickup being picked up");
        WeaponType expected = WeaponType.GUN;
        Player player = new Player(0,0);
        pickup = new WeaponPickup(40,40,expected);
        pickup.pickUp(player);
        WeaponType actual = player.getCurrentWeapon();
        Assert.assertEquals(expected,actual);
    }


    @Test
    public void testGetWeaponType() {
        System.out.println("Testing WeaponPickup weaponType getter");
        WeaponType expected = WeaponType.GUN;
        pickup = new WeaponPickup(0,0,expected);
        Assert.assertEquals(expected,pickup.getWeaponType());
    }

    @Test
    public void testOnCollidePlayer() {
    }

    @Test
    public void testOnCollideNotPlayer(){

    }
}
