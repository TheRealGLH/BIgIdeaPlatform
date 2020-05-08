package models.classes.objects.obstacles;

import PlatformGameShared.Enums.SpriteType;
import PlatformGameShared.Enums.SpriteUpdateType;
import PlatformGameShared.Points.SpriteUpdate;
import PlatformGameShared.Points.Vector2;
import models.classes.GameObject;
import models.classes.objects.MovableObject;
import models.classes.objects.Player;

public class ObstacleShockBall extends MovableObject {

    private int floatTimer = 0;
    private static final int floatMaxTime = 20;
    boolean movingUp = false;
    float moveDistPerTick = 2;

    public ObstacleShockBall(float xPosition, float yPosition, float width, float height) {
        super(xPosition, yPosition, width, height);
    }

    @Override
    public void update() {
        float willMoveDist = this.moveDistPerTick;
        if (!movingUp) {
            willMoveDist -= willMoveDist;
        }
        Vector2 pos = getPosition();
        pos.setX(pos.getX() + willMoveDist);
        floatTimer++;
        if (floatTimer >= floatMaxTime) {
            movingUp = !movingUp;
            floatTimer = 0;
        }
        setChanged();
        notifyObservers(new SpriteUpdate(getObjectNr(), getPosition(), getSize(), SpriteUpdateType.MOVE, getSpriteType(), false, getLabel()));
    }

    @Override
    public void onCollide(GameObject other, Vector2 collidePoint) {
        if (other instanceof Player) {
            ((Player) other).Kill();
        }
    }

    @Override
    public SpriteType getSpriteType() {
        return SpriteType.NONE;
    }
}
