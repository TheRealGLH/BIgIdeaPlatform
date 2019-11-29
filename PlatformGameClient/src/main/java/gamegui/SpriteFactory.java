package gamegui;

import Enums.SpriteUpdateType;
import SharedClasses.SpriteUpdate;
import SharedClasses.Vector2;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.security.InvalidParameterException;

public class SpriteFactory {

    private static Image placeHolder = new Image(SpriteFactory.class.getResource("/sprites/placeholder.png").toExternalForm());
    private static Image player = new Image(SpriteFactory.class.getResource("/sprites/player.png").toExternalForm());
    private static Image platform = new Image(SpriteFactory.class.getResource("/sprites/platform.png").toExternalForm());
    private static Image weaponpickup = new Image(SpriteFactory.class.getResource("/sprites/weaponpickup.png").toExternalForm());
    private static Image bullet = new Image(SpriteFactory.class.getResource("/sprites/bullet.png").toExternalForm());
    private static double spriteWidth = 2;
    private static double spriteHeight = 2;

    public static ImageView drawSprite(SpriteUpdate spriteUpdate) {
        if (spriteUpdate.getUpdateType() != SpriteUpdateType.CREATE)
            throw new InvalidParameterException("Only SpriteUpdates of type CREATE are allowed");
        ImageView imageView = new ImageView();
        Image image = null;
        switch (spriteUpdate.getSpriteType()) {
            case PLAYER:
                image = player;
                break;
            case PLATFORM:
                image = platform;
                break;
            case WEAPONPICKUP:
                image = weaponpickup;
                break;
            case BULLET:
                image = bullet;
                break;
            default:
                image = placeHolder;
                break;
        }
        imageView.setImage(image);
        Vector2 size = spriteUpdate.getSize();
        Vector2 pos = spriteUpdate.getPosition();
        imageView.setFitWidth(spriteWidth * size.getX());
        imageView.setFitHeight(spriteHeight * size.getY());
        imageView.setX(pos.getX());
        imageView.setY(pos.getY());
        if (spriteUpdate.isFacingLeft()) imageView.setFitWidth(-imageView.getFitWidth());//this will flip the image around
        return imageView;
    }

    public static void updateImage(ImageView imageView, SpriteUpdate spriteUpdate) {
        if (spriteUpdate.getUpdateType() != SpriteUpdateType.MOVE)
            throw new InvalidParameterException("Only SpriteUpdates of type MOVE are allowed");
        Vector2 size = spriteUpdate.getSize();
        Vector2 pos = spriteUpdate.getPosition();
        imageView.setFitWidth(spriteWidth * size.getX());
        imageView.setFitHeight(spriteHeight * size.getY());
        imageView.setX(pos.getX());
        imageView.setY(pos.getY());
        if (spriteUpdate.isFacingLeft()) imageView.setFitWidth(-imageView.getFitWidth());//this will flip the image around
    }
}
