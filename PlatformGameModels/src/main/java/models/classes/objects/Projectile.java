package models.classes.objects;

public abstract class Projectile extends MovableObject {
    public Projectile(float xPosition, float yPosition, float width, float height) {
        super(xPosition, yPosition, width, height);
    }

    @Override
    public void update() {
        super.update();
    }
}
