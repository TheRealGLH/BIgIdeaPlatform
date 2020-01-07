package models.classes.objects.projectiles;

import PlatformGameShared.Enums.SpriteType;
import PlatformGameShared.Points.Vector2;
import PlatformGameShared.PropertiesLoader;
import models.classes.objects.Player;

public class ProjectileSwordAttack extends Projectile {

    private static float sizeMod = Float.parseFloat(PropertiesLoader.getPropValues("projectileSwordAttack.sizeMod","weapons.properties"));
    private static int maxLife = Integer.parseInt(PropertiesLoader.getPropValues("projectileSwordAttack.maxLife","weapons.properties"));

    public ProjectileSwordAttack(float xPosition, float yPosition, Player owner) {
        super(xPosition, yPosition, sizeMod, sizeMod, maxLife, owner);
        setUseGravity(false);
    }

    @Override
    public void update() {
        Vector2 pos = getOwner().getPosition();
        Vector2 size = getOwner().getSize();
        this.setPosition(pos.getX() + size.getX(), pos.getY() + size.getY() / 2);
        super.update();
    }

    @Override
    public SpriteType getSpriteType() {
        return SpriteType.PROJECTILESWORD;
    }
}
