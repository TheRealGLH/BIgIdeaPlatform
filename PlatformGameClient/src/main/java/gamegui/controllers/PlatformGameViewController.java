package gamegui.controllers;

import Enums.InputType;
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
        controller.addSpriteEventListener(this);
    }


    @FXML
    void handleKeyReleased(KeyEvent event) {
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
                    float xPos = (float) random1.nextInt(1270);
                    float yPos = (float) random1.nextInt(720);
                    SpriteUpdate m = new SpriteUpdate(nr, new Vector2(xPos, yPos), size, SpriteUpdateType.MOVE, SpriteType.NONE, (Math.random() < 0.5));
                    spriteMove.add(m);
                }
                controller.updateScreen(spriteMove);
                break;

            case R:
                List<SpriteUpdate> spritesDelete = new ArrayList<>();
                for (int nr : spriteMap.keySet()) {
                    SpriteUpdate d = new SpriteUpdate(nr, Vector2.Zero(), Vector2.Zero(), SpriteUpdateType.DESTROY, SpriteType.NONE, false);
                    spritesDelete.add(d);
                }
                controller.updateScreen(spritesDelete);
                lastId = 1;
                break;
            //Game inputs
            case A:
                controller.sendInput(InputType.MOVELEFT);
                break;
            case D:
                controller.sendInput(InputType.MOVERIGHT);
                break;
            case SPACE:
                controller.sendInput(InputType.JUMP);
        }

    }

    @FXML
    void handleKeyDown(KeyEvent e) {
        KeyCode keyCode = e.getCode();
        switch (keyCode) {
            case A:
                controller.sendInput(InputType.MOVELEFT);
                break;
            case D:
                controller.sendInput(InputType.MOVERIGHT);
                break;
        }
    }

    @Override
    public void handleSpriteUpdate(List<SpriteUpdate> spriteUpdates) {
        for (SpriteUpdate spriteUpdate : spriteUpdates) {
            SpriteUpdateType updateType = spriteUpdate.getUpdateType();
            switch (updateType) {
                case MOVE:
                    ImageView imageView = spriteMap.get(spriteUpdate.getObjectNr());
                    if (imageView != null) {
                        System.out.println("[PlatformGameViewController.java] Moving image " + imageView);
                        SpriteFactory.updateImage(imageView, spriteUpdate, gamePane.getWidth(), gamePane.getHeight());
                    } else {
                        System.out.println("[PlatformGameViewController.java] Image didn't exist for nr " + spriteUpdate.getObjectNr() + " creating new one");
                        ImageView imageCreate = SpriteFactory.drawSprite(spriteUpdate, gamePane.getWidth(), gamePane.getHeight());
                        spriteMap.put(spriteUpdate.getObjectNr(), imageCreate);
                        gamePane.getChildren().add(imageCreate);
                    }
                    break;
                case CREATE:
                    System.out.println("[PlatformGameViewController.java] Creating sprite " + spriteUpdate);
                    ImageView imageCreate = SpriteFactory.drawSprite(spriteUpdate, gamePane.getWidth(), gamePane.getHeight());
                    spriteMap.put(spriteUpdate.getObjectNr(), imageCreate);
                    gamePane.getChildren().add(imageCreate);
                    break;
                case DESTROY:
                    Integer nrToDelete = spriteUpdate.getObjectNr();
                    ImageView imageViewToDelete = spriteMap.get(nrToDelete);
                    System.out.println("[PlatformGameViewController.java] Destroying ImageView " + imageViewToDelete + " for object nr " + nrToDelete);
                    gamePane.getChildren().remove(imageViewToDelete);
                    spriteMap.remove(spriteUpdate.getObjectNr());
                    break;
            }
        }
        System.out.println("[PlatformGameViewController.java] Finished updating sprites");
    }
}
