package models.classes.objects;

public abstract class Projectile extends MovableObject {

    private int currentLife = 0;
    public int maxLife = 0;
    public Projectile(float xPosition, float yPosition, float width, float height) {
        super(xPosition, yPosition, width, height);
    }

    @Override
    public void update() {
        super.update();
        currentLife++;
        if(currentLife >= maxLife) {
            Kill();
            return;
        }
    }
}
