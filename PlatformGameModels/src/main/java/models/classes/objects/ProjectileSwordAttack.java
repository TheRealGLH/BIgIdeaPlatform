package models.classes.objects;

import PlatformGameShared.Enums.SpriteType;
import PlatformGameShared.Points.Vector2;
import PlatformGameShared.PropertiesLoader;

public class ProjectileSwordAttack extends Projectile {

    private static float sizeMod = Float.parseFloat(PropertiesLoader.getPropValues("projectileSwordAttack.sizeMod","weapons.properties"));
    private static int maxLife = Integer.parseInt(PropertiesLoader.getPropValues("projectileSwordAttack.maxLife","weapons.properties"));
    Player owner;

    public ProjectileSwordAttack(float xPosition, float yPosition, Player owner) {
        super(xPosition, yPosition, owner.getSize().getX() * sizeMod, owner.getSize().getY() * sizeMod, maxLife, owner);
        setUseGravity(false);
        this.owner = owner;
    }

    @Override
    public void update() {
        Vector2 pos = owner.getPosition();
        Vector2 size = owner.getSize();
        this.setPosition(pos.getX() + size.getX(), pos.getY() + size.getY() / 2);
        super.update();
    }

    @Override
    public SpriteType getSpriteType() {
        return SpriteType.PROJECTILESWORD;
    }
}