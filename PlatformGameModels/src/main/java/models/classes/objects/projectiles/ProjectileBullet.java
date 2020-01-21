package models.classes.objects.projectiles;

import PlatformGameShared.Enums.SpriteType;
import PlatformGameShared.Points.Vector2;
import PlatformGameShared.PropertiesLoader;
import models.classes.GameObject;
import models.classes.objects.Player;

public class ProjectileBullet extends Projectile {
    public static float bulletAcceleration = Float.parseFloat(PropertiesLoader.getPropValues("projectileBullet.acceleration","weapons.properties"));
    private static int maxLife = Integer.parseInt(PropertiesLoader.getPropValues("projectileBullet.maxLife","weapons.properties"));
    private static float width = Float.parseFloat(PropertiesLoader.getPropValues("projectileBullet.width","weapons.properties"));
    private static float height = Float.parseFloat(PropertiesLoader.getPropValues("projectileBullet.height","weapons.properties"));
    public ProjectileBullet(float xPosition, float yPosition, Player owner) {
        super(xPosition, yPosition, width, height,maxLife, owner);
        setUseGravity(false);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void onCollide(GameObject other, Vector2 collidePoint) {
        super.onCollide(other, collidePoint);
    }

    @Override
    public Projectile clone() {
        Vector2 pos = getPosition();
        Vector2 acc = getAcceleration();
        Vector2 vel = getVelocity();
        ProjectileBullet clone = new ProjectileBullet(pos.getX(), pos.getY(), getOwner());
        clone.setAcceleration(acc.getX(), acc.getY());
        clone.setVelocity(vel.getX(), vel.getY());
        return clone;
    }

    @Override
    public SpriteType getSpriteType() {
        return SpriteType.PROJECTILEBULLET;
    }


}
