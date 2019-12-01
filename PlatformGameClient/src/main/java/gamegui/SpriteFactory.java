package gamegui;

import Enums.SpriteUpdateType;
import SharedClasses.SpriteUpdate;
import SharedClasses.Vector2;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.security.InvalidParameterException;

public class SpriteFactory {

    private static Image placeHolder = new Image(SpriteFactory.class.getResource("/sprites/placeholder.png").toExternalForm());
    private static Image player = new Image(SpriteFactory.class.getResource("/sprites/player.png").toExternalForm());
    private static Image platform = new Image(SpriteFactory.class.getResource("/sprites/platform.png").toExternalForm());
    private static Image weaponpickup = new Image(SpriteFactory.class.getResource("/sprites/weaponpickup.png").toExternalForm());
    private static Image bullet = new Image(SpriteFactory.class.getResource("/sprites/bullet.png").toExternalForm());
    private static double spriteWidth = 2;
    private static double spriteHeight = 2;

    public static ImageView drawSprite(SpriteUpdate spriteUpdate, double screenWidth, double screenHeight) {
        if (spriteUpdate.getUpdateType() != SpriteUpdateType.CREATE && spriteUpdate.getUpdateType() != SpriteUpdateType.MOVE)
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
        prepareImage(imageView,spriteUpdate, screenWidth, screenHeight);
        return imageView;
    }

    public static void updateImage(ImageView imageView, SpriteUpdate spriteUpdate, double screenWidth, double screenHeight) {
        if (spriteUpdate.getUpdateType() != SpriteUpdateType.MOVE)
            throw new InvalidParameterException("Only SpriteUpdates of type MOVE are allowed");
        prepareImage(imageView,spriteUpdate, screenWidth, screenHeight);
    }

    private static void prepareImage(ImageView imageView, SpriteUpdate spriteUpdate, double screenWidth, double screenHeight){
        Vector2 size = spriteUpdate.getSize();
        Vector2 pos = spriteUpdate.getPosition();
        imageView.setFitWidth(spriteWidth * size.getX());
        imageView.setFitHeight(spriteHeight * size.getY());
        imageView.setX(pos.getX());
        imageView.setY(screenHeight - pos.getY() - imageView.getFitHeight());
        double x = (spriteUpdate.isFacingLeft()) ? -imageView.getScaleX() : imageView.getScaleX() ;
        imageView.setScaleX(x);
    }

    public static Label drawLabel(SpriteUpdate spriteUpdate, double screenWidth, double screenHeight){
        if (spriteUpdate.getUpdateType() != SpriteUpdateType.CREATE && spriteUpdate.getUpdateType() != SpriteUpdateType.MOVE)
            throw new InvalidParameterException("Only SpriteUpdates of type CREATE are allowed");
        Label label = new Label();
        prepareLabel(label,spriteUpdate,screenWidth,screenHeight);
        return label;
    }

    public static void updateLabel(Label label, SpriteUpdate spriteUpdate, double screenWidth, double screenHeight){
        if (spriteUpdate.getUpdateType() != SpriteUpdateType.MOVE)
            throw new InvalidParameterException("Only SpriteUpdates of type MOVE are allowed");
        prepareLabel(label,spriteUpdate, screenWidth, screenHeight);
    }



    private static void prepareLabel(Label label, SpriteUpdate spriteUpdate, double screenWidth, double screenHeight){
        label.textProperty().setValue(spriteUpdate.getLabel());
        Vector2 pos = spriteUpdate.getPosition();
        Vector2 size = spriteUpdate.getSize();
        label.setLayoutX(pos.getX());
        label.setLayoutY(screenHeight - (pos.getY() + spriteHeight * size.getY()));
    }
}
