package gamegui.controllers;

import Enums.SpriteType;
import Enums.SpriteUpdateType;
import SharedClasses.SpriteUpdate;
import SharedClasses.Vector2;
import gamegui.GUIScreenController;
import gamegui.Interfaces.ISpriteUpdateEventListener;
import gamegui.ScreenController;
import gamegui.SpriteFactory;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.*;

public class PlatformGameViewController implements ISpriteUpdateEventListener {

    public Pane gamePane;
    private int lastId = 1;
    ScreenController controller = GUIScreenController.getInstance();
    Map<Integer, ImageView> spriteMap = new HashMap<>();

    @FXML
    protected void initialize() {
        controller.addEventListener(this);
    }


    @FXML
    void handleKeyInput(KeyEvent event) {
        KeyCode keyCode = event.getCode();
        float widthHeight = 10;
        Vector2 size = new Vector2(widthHeight, widthHeight);
        switch (keyCode) {
            case C:
                List<SpriteUpdate> sprites = new ArrayList<>();
                Random random = new Random();
                for (int i = 1; i < 5; i++) {
                    SpriteType spriteType = SpriteType.values()[random.nextInt(SpriteType.values().length)];
                    Vector2 position = new Vector2(widthHeight, (i + lastId) * widthHeight);
                    SpriteUpdate u1 = new SpriteUpdate(lastId, position, size, SpriteUpdateType.CREATE, spriteType, false);
                    sprites.add(u1);
                    lastId++;
                }
                controller.updateScreen(sprites);
                break;
            case M:
                Random random1 = new Random();
                List<SpriteUpdate> spriteMove = new ArrayList<>();
                for (int nr : spriteMap.keySet()) {
                    float x = (float) random1.nextInt(400);
                    float y = (float) random1.nextInt(200);
                    SpriteUpdate m = new SpriteUpdate(nr, new Vector2(x, y), size, SpriteUpdateType.MOVE, SpriteType.NONE, false);
                    spriteMove.add(m);
                }
                controller.updateScreen(spriteMove);
                break;

            case R:
                List<SpriteUpdate> spritesDelete = new ArrayList<>();
                for (int nr : spriteMap.keySet()) {
                    SpriteUpdate d = new SpriteUpdate(nr, Vector2.Zero(), Vector2.Zero(), SpriteUpdateType.DESTROY, SpriteType.NONE, (Math.random() < 0.5));
                    spritesDelete.add(d);
                }
                controller.updateScreen(spritesDelete);
                lastId = 1;
                break;
        }

    }

    @Override
    public void handleSpriteUpdate(List<SpriteUpdate> spriteUpdates) {
        for (SpriteUpdate spriteUpdate : spriteUpdates) {
            SpriteUpdateType updateType = spriteUpdate.getUpdateType();
            switch (updateType) {
                case MOVE:
                    ImageView imageView = spriteMap.get((Integer) spriteUpdate.getObjectNr());
                    System.out.println("[PlatformGamesViewController] Moving image " + imageView);
                    SpriteFactory.updateImage(imageView,spriteUpdate);
                    break;
                case CREATE:
                    System.out.println("[PlatformGameViewController] Creating sprite " + spriteUpdate);
                    ImageView imageCreate = SpriteFactory.drawSprite(spriteUpdate);
                    spriteMap.put((Integer) spriteUpdate.getObjectNr(), imageCreate);
                    gamePane.getChildren().add(imageCreate);
                    break;
                case DESTROY:
                    Integer nrToDelete = (Integer) spriteUpdate.getObjectNr();
                    ImageView imageViewToDelete = spriteMap.get(nrToDelete);
                    System.out.println("[PlatformGameViewController] Destroying ImageView " + imageViewToDelete);
                    gamePane.getChildren().remove(imageViewToDelete);
                    spriteMap.remove((Integer) spriteUpdate.getObjectNr());
                    break;
            }
        }
        System.out.println("[PlatformGameViewController] Finished updating sprites");
    }
}
