package models.classes.objects.projectiles;

import PlatformGameShared.PlatformLogger;
import PlatformGameShared.Points.Vector2;
import models.classes.GameObject;
import models.classes.objects.MovableObject;
import models.classes.objects.Player;

import java.util.logging.Level;

public abstract class Projectile extends MovableObject {

    private int currentLife = 0;
    public int maxLife;
    public boolean destroyOnHit = true;
    public boolean allowsSelfHarm = false;
    private Player owner;

    public Projectile(float xPosition, float yPosition, float width, float height, int maxLife, Player owner) {
        super(xPosition, yPosition, width, height);
        this.maxLife = maxLife;
        this.owner = owner;
    }

    @Override
    public void update() {
        super.update();
        currentLife++;
        if (currentLife >= maxLife) {
            markForDeletion();
            return;
        }
    }

    @Override
    public void onCollide(GameObject other, Vector2 collidePoint) {
        if (other instanceof Player) {
            Player player = (Player) other;
            if (player != owner | this.allowsSelfHarm) {
                PlatformLogger.Log(Level.INFO, owner.getName() + " killed " + player.getName() + " with " + owner.getCurrentWeapon());
                player.Kill();
                if (destroyOnHit) markForDeletion();
                return;
            }
        }
    }

    public int getCurrentLife() {
        return currentLife;
    }

    @Override
    public String getLabel() {
        return "";
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

    public boolean isAllowsSelfHarm() {
        return allowsSelfHarm;
    }

    public void setAllowsSelfHarm(boolean allowsSelfHarm) {
        this.allowsSelfHarm = allowsSelfHarm;
    }

    public abstract Projectile clone();
}
