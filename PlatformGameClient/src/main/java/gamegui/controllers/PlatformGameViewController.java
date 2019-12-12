package gamegui.controllers;

import PlatformGameShared.Enums.InputType;
import PlatformGameShared.Enums.SpriteUpdateType;
import PlatformGameShared.Points.SpriteUpdate;
import gamegui.GUIScreenController;
import gamegui.Interfaces.ISpriteUpdateEventListener;
import gamegui.ScreenController;
import gamegui.SpriteFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlatformGameViewController implements ISpriteUpdateEventListener {

    public Pane gamePane;
    ScreenController controller = GUIScreenController.getInstance();
    Map<Integer, ImageView> spriteMap = new HashMap<>();
    Map<Integer, Label> labelMap = new HashMap<>();
    private boolean walkLeftHeld = false;
    private boolean walkRightHeld = false;
    private boolean jumpHeld = false;
    private boolean shootHeld = false;
    private boolean duckHeld = false;

    @FXML
    protected void initialize() {
        controller.addSpriteEventListener(this);
    }


    @FXML
    void handleKeyReleased(KeyEvent event) {
        KeyCode keyCode = event.getCode();
        switch (keyCode) {
            case A:
                walkLeftHeld = false;
                break;
            case D:
                walkRightHeld = false;
                break;
            case W:
                jumpHeld = false;
                break;
            case S:
                duckHeld = false;
                break;
            case SPACE:
                shootHeld = false;
                break;
        }

    }

    @FXML
    void handleKeyDown(KeyEvent e) {
        KeyCode c = e.getCode();
        switch (c) {
            case A:
                walkLeftHeld = true;
                break;
            case D:
                walkRightHeld = true;
                break;
            case W:
                jumpHeld = true;
                break;
            case S:
                duckHeld = true;
                break;
            case SPACE:
                shootHeld = true;
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
                    Label label = labelMap.get(spriteUpdate.getObjectNr());
                    if (imageView != null) {
                        SpriteFactory.updateImage(imageView, spriteUpdate, gamePane.getWidth(), gamePane.getHeight());
                    } else {
                        System.out.println("[PlatformGameViewController.java] Image didn't exist for nr " + spriteUpdate.getObjectNr() + " creating new one");
                        ImageView imageCreate = SpriteFactory.drawSprite(spriteUpdate, gamePane.getWidth(), gamePane.getHeight());
                        spriteMap.put(spriteUpdate.getObjectNr(), imageCreate);
                        gamePane.getChildren().add(imageCreate);
                    }
                    if (label != null) {
                        SpriteFactory.updateLabel(label, spriteUpdate, gamePane.getWidth(), gamePane.getHeight());
                    } else {
                        Label labelCreate = SpriteFactory.drawLabel(spriteUpdate, gamePane.getWidth(), gamePane.getHeight());
                        labelMap.put(spriteUpdate.getObjectNr(), labelCreate);
                        gamePane.getChildren().add(labelCreate);
                    }
                    break;
                case CREATE:
                    System.out.println("[PlatformGameViewController.java] Creating sprite " + spriteUpdate);
                    ImageView imageCreate = SpriteFactory.drawSprite(spriteUpdate, gamePane.getWidth(), gamePane.getHeight());
                    spriteMap.put(spriteUpdate.getObjectNr(), imageCreate);
                    gamePane.getChildren().add(imageCreate);
                    Label labelCreate = SpriteFactory.drawLabel(spriteUpdate, gamePane.getWidth(), gamePane.getHeight());
                    labelMap.put(spriteUpdate.getObjectNr(), labelCreate);
                    gamePane.getChildren().add(labelCreate);
                    break;
                case DESTROY:
                    Integer nrToDelete = spriteUpdate.getObjectNr();
                    ImageView imageViewToDelete = spriteMap.get(nrToDelete);
                    Label labelToDelete = labelMap.get(nrToDelete);
                    gamePane.getChildren().remove(imageViewToDelete);
                    gamePane.getChildren().remove(labelToDelete);
                    spriteMap.remove(spriteUpdate.getObjectNr());
                    labelMap.remove(spriteUpdate.getObjectNr());
                    break;
            }
        }
        System.out.println("[PlatformGameViewController.java] Finished updating sprites");
    }

    @Override
    public void allowSendInput() {
        sendInput();
    }

    public void sendInput(){
        if (walkLeftHeld) controller.sendInput(InputType.MOVELEFT);
        else if (walkRightHeld) controller.sendInput(InputType.MOVERIGHT);
        if (duckHeld) controller.sendInput(InputType.DUCK);
        if (jumpHeld) controller.sendInput(InputType.JUMP);
        if (shootHeld) controller.sendInput(InputType.SHOOT);
    }
}
