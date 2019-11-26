package SharedClasses;


import org.junit.Assert;
import org.junit.Test;

public class Vector2Test {

    @Test
    public void testConstruction() {
        float x = 10;
        float y = 15;
        Vector2 vector2 = new Vector2(x, y);
        boolean toAssert = (vector2.getX()== x && vector2.getY() == y);
        Assert.assertTrue(toAssert);
    }

    @Test
    public void testEqualsSameObject() {
        Vector2 a = new Vector2(10,10);
        Vector2 b = a;
        Assert.assertEquals(a, b);
    }

    @Test(expected = ClassCastException.class)
    public void testEqualsDifferentType(){
        Vector2 a = new Vector2(10,10);
        Object b = new Object();
        Assert.assertNotEquals(a, b);
    }

    @Test
    public void testEqualsNull(){
        Vector2 a = new Vector2(10,10);
        Vector2 b = null;
        Assert.assertNotEquals(a, b);
    }

    @Test
    public void testEqualsCoordinatesMatch(){
        Vector2 a = new Vector2(10,10);
        Vector2 b = new Vector2(10,10);
        Assert.assertEquals(a,b);
    }

    @Test
    public void testEqualsCoordinatesDoNotMatch(){
        Vector2 a = new Vector2(10,10);
        Vector2 b = new Vector2(11,11);
        Assert.assertNotEquals(a,b);
    }
}