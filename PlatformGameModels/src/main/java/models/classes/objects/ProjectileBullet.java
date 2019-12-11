package models.classes.objects;

import PlatformGameShared.Enums.SpriteType;
import PlatformGameShared.Points.Vector2;
import PlatformGameShared.PropertiesLoader;
import models.classes.GameObject;

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
    public void update(){
        super.update();
    }

    @Override
    public void onCollide(GameObject other, Vector2 collidePoint) {
        super.onCollide(other,collidePoint);
    }

    @Override
    public SpriteType getSpriteType() {return SpriteType.NONE;}


}
