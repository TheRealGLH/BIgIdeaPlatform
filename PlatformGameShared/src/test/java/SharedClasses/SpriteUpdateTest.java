package SharedClasses;

import Enums.SpriteType;
import Enums.SpriteUpdateType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import static org.junit.jupiter.api.Assertions.*;

public class SpriteUpdateTest {

    SpriteUpdate sprite;
    int objectNr = 1;
    Vector2 position = new Vector2(5, 5);
    Vector2 size = new Vector2(40, 40);
    SpriteUpdateType updateType = SpriteUpdateType.CREATE;
    SpriteType spriteType = SpriteType.PLATFORM;
    boolean isFacingLeft = false;

    @Before
    public void setUp() {
        System.out.println("Setting up a SpriteUpdate test...");
        sprite = new SpriteUpdate(objectNr, position, size, updateType, spriteType, isFacingLeft);
    }

    @Test
    public void getObjectNr() {
        System.out.println("Testing SpriteUpdate objectNr getter");
        Assert.assertEquals(objectNr, sprite.getObjectNr());
    }

    @Test
    public void getPosition() {
        System.out.println("Testing SpriteUpdate position getter");
        Assert.assertEquals(position, sprite.getPosition());
    }

    @Test
    public void getSize() {
        System.out.println("Testing SpriteUpdate size getter");
        Assert.assertEquals(size, sprite.getSize());
    }

    @Test
    public void getSpriteType() {
        System.out.println("Testing SpriteUpdate spritetype getter");
        Assert.assertEquals(spriteType, sprite.getSpriteType());
    }

    @Test
    public void getUpdateType() {
        System.out.println("Testing SpriteUpdate UpdateType getter");
        Assert.assertEquals(updateType, sprite.getUpdateType());
    }

    @Test
    public void isFacingLeft() {
        System.out.println("Testing SpriteUpdate isFacingLeft getter");
        Assert.assertEquals(isFacingLeft, sprite.isFacingLeft());
    }

    @Test
    public void testEqualsDifferentType() {
        System.out.println("Testing SpriteUpdate equals() different type");
        Object other = new Object();
        Assert.assertNotEquals(sprite, other);
    }

    @Test
    public void testEqualsSameId() {
        System.out.println("Testing SpriteUpdate equals() same ID");
        SpriteUpdate other = new SpriteUpdate(sprite.getObjectNr(), Vector2.Zero(), Vector2.Zero(), SpriteUpdateType.CREATE, SpriteType.PLATFORM, true);
        Assert.assertEquals(sprite, other);
    }

    @Test
    public void testEqualsDifferentId() {
        System.out.println("Testing SpriteUpdate equals() different ID");
        SpriteUpdate other = new SpriteUpdate(sprite.getObjectNr() + 2, Vector2.Zero(), Vector2.Zero(), SpriteUpdateType.CREATE, SpriteType.PLATFORM, true);
        Assert.assertNotEquals(sprite, other);
    }
}