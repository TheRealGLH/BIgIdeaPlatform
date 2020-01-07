package models.classes.objects.projectiles;

import PlatformGameShared.Enums.SpriteType;
import PlatformGameShared.Points.Vector2;
import PlatformGameShared.PropertiesLoader;
import models.classes.GameObject;
import models.classes.objects.Player;


public class ProjectileThrowingAxe extends Projectile {

    private static float size = Float.parseFloat(PropertiesLoader.getPropValues("projectileThrowingAxe.size","weapons.properties"));
    private static int maxLife = Integer.parseInt(PropertiesLoader.getPropValues("projectileThrowingAxe.maxLife","weapons.properties"));
    public static float horizontalVelocity = Float.parseFloat(PropertiesLoader.getPropValues("projectileThrowingAxe.velocityX","weapons.properties"));
    public static float verticalVelocity = Float.parseFloat(PropertiesLoader.getPropValues("projectileThrowingAxe.velocityY","weapons.properties"));

    public ProjectileThrowingAxe(float xPosition, float yPosition,   Player owner) {
        super(xPosition, yPosition, size, size, maxLife, owner);
        setMaxHorizontalVelocity(horizontalVelocity);
    }

    @Override
    public void onCollide(GameObject other, Vector2 pos){
        if(!isGrounded()) super.onCollide(other,pos);
    }

    @Override
    public SpriteType getSpriteType() {
        return SpriteType.AXE;
    }
}
