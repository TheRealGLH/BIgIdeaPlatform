package SharedClasses;

import Enums.SpriteType;
import Enums.SpriteUpdateType;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SpriteUpdateTest {

    SpriteUpdate sprite;
    int objectNr = 1;
    Vector2 position = new Vector2(5, 5);
    Vector2 size = new Vector2(40, 40);
    SpriteUpdateType updateType = SpriteUpdateType.CREATE;
    SpriteType spriteType = SpriteType.PLATFORM;
    boolean isFacingLeft = false;

    @BeforeEach
    public void setUp() {
        sprite = new SpriteUpdate(objectNr, position, size, updateType, spriteType, isFacingLeft);
    }

    @Test
    public void getObjectNr() {
        Assert.assertEquals(objectNr, sprite.getObjectNr());
    }

    @Test
    public void getPosition() {
        Assert.assertEquals(position, sprite.getPosition());
    }

    @Test
    public void getSize() {
        Assert.assertEquals(size, sprite.getSize());
    }

    @Test
    public void getSpriteType() {
        Assert.assertEquals(spriteType, sprite.getSpriteType());
    }

    @Test
    public void getUpdateType() {
        Assert.assertEquals(updateType, sprite.getUpdateType());
    }

    @Test
    public void isFacingLeft() {
        Assert.assertEquals(isFacingLeft, sprite.isFacingLeft());
    }
}