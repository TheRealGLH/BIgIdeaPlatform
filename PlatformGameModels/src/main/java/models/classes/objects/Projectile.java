package models.classes.objects;

import PlatformGameShared.Points.Vector2;
import models.classes.GameObject;

public abstract class Projectile extends MovableObject {

    private int currentLife = 0;
    public int maxLife;
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
                Delete();
                return;
            }
        }
    }

    public int getCurrentLife(){
        return currentLife;
    }
}
