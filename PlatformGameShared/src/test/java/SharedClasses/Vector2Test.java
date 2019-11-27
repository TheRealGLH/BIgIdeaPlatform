package SharedClasses;


import org.junit.Assert;
import org.junit.Test;

public class Vector2Test {

    @Test
    public void testConstruction() {
        float x = 10;
        float y = 15;
        Vector2 vector2 = new Vector2(x, y);
        boolean toAssert = (vector2.getX() == x && vector2.getY() == y);
        Assert.assertTrue(toAssert);
    }

    @Test
    public void testEqualsSameObject() {
        Vector2 a = new Vector2(10, 10);
        Vector2 b = a;
        Assert.assertEquals(a, b);
    }

    @Test
    public void testConstructorFromOther() {
        Vector2 expected = new Vector2(10, 10);
        Vector2 actual = new Vector2(expected);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = ClassCastException.class)
    public void testEqualsDifferentType() {
        Vector2 a = new Vector2(10, 10);
        Object b = new Object();
        Assert.assertNotEquals(a, b);
    }

    @Test
    public void testEqualsNull() {
        Vector2 a = new Vector2(10, 10);
        Vector2 b = null;
        Assert.assertNotEquals(a, b);
    }

    @Test
    public void testEqualsCoordinatesMatch() {
        Vector2 a = new Vector2(10, 10);
        Vector2 b = new Vector2(10, 10);
        Assert.assertEquals(a, b);
    }

    @Test
    public void testEqualsCoordinatesDoNotMatch() {
        Vector2 a = new Vector2(10, 10);
        Vector2 b = new Vector2(11, 11);
        Assert.assertNotEquals(a, b);
    }

    @Test
    public void midpoint() {
        float width = 10;
        float height = 20;
        Vector2 expected = new Vector2(width / 2, height / 2);
        Vector2 zero = Vector2.Zero();
        Vector2 corner = new Vector2(width, height);
        Vector2 actual = zero.midpoint(corner);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void zero() {
        Vector2 expected = new Vector2(0, 0);
        Vector2 actual = Vector2.Zero();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSetX() {
        float expected = 20;
        Vector2 vector2 = Vector2.Zero();
        vector2.setX(expected);
        float actual = vector2.getX();
        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void testSetY() {
        float expected = 20;
        Vector2 vector2 = Vector2.Zero();
        vector2.setY(expected);
        float actual = vector2.getY();
        Assert.assertEquals(expected, actual, 0);
    }
}