package models.classes;

import PlatformGameShared.Points.Vector2;
import models.classes.objects.MovableObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A mock class that updates objects and does the collision for them.
 * Used in place of the actual Game class, because this will allow us to freely add objects to our desire
 * whilst still getting all of the output from our Objects
 */
public class MockGame {
    private List<MovableObject> objectList = new ArrayList<>();

    public void addObject(MovableObject object){
        objectList.add(object);
    }

    public void update(){
        for (MovableObject object : objectList){
            object.update();
            for (MovableObject collide : objectList){
                if(object.collidesWith(collide)) object.onCollide(collide, Vector2.Zero());
            }
        }
        cleanUp();
    }

    public boolean objectStillInList(MovableObject object){
        return objectList.contains(object);
    }

    private void cleanUp(){
        Iterator i = objectList.iterator();
        while (i.hasNext()) {
            MovableObject object = (MovableObject) i.next();
            if (object.isShouldBeCleaned()) i.remove();
        }
    }

    public void reset(){
        objectList = new ArrayList<>();
    }

}
