package models.classes.objects.projectiles;

import PlatformGameShared.Points.Vector2;
import models.classes.GameObject;
import models.classes.objects.MovableObject;
import models.classes.objects.Player;

import java.lang.reflect.InvocationTargetException;

public abstract class Projectile extends MovableObject implements Cloneable {

    private int currentLife = 0;
    public int maxLife;
    public boolean destroyOnHit = true;
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
        if(currentLife >= maxLife) {
            Delete();
            return;
        }
    }

    @Override
    public void onCollide(GameObject other, Vector2 collidePoint) {
        if(other instanceof Player){
            Player player = (Player) other;
            if(player != owner) {
                player.Kill();
                if(destroyOnHit)Delete();
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

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
