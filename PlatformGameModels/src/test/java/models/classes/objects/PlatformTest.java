package models.classes.objects;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PlatformTest {

    Platform platform;

    @Test
    public void testConstructor(){
        //
    }

    @Test
    public void isSolid() {
        System.out.println("Testing Platform solidness");
        platform = new Platform(10,10,10,10,false);
        platform.setSolid(true);
        assertTrue(platform.isSolid());
    }


}