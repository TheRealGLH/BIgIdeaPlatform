package gamegui.controllers;

import PlatformGameShared.Enums.InputType;
import gamegui.GUIScreenController;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PlatformInputTestController {

    public Rectangle rectLeft;
    public Rectangle rectShoot;
    public Rectangle rectJump;
    public Rectangle rectRight;
    public Rectangle rectOther;
    private Color red = Color.RED;
    private Color white = Color.WHITE;
    private boolean walkLeftHeld = false;
    private boolean walkRightHeld = false;
    private boolean jumpHeld = false;
    private boolean shootHeld = false;
    private boolean duckHeld = false;


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
        getRect(c).setFill(red);
        System.out.println("Left: " + walkLeftHeld
                + "\nRight: " + walkRightHeld
                + "\nJump: " + jumpHeld
                + "\nShoot: " + shootHeld
                + "\nDuck: " + duckHeld
        );
    }


    @FXML
    void handleKeyReleased(KeyEvent e) {
        KeyCode c = e.getCode();
        switch (c) {
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
        getRect(c).setFill(white);
    }

    @FXML
    void backClicked() {
        GUIScreenController.getInstance().showMainMenu();
    }

    Rectangle getRect(KeyCode c) {
        Rectangle rectangle;
        switch (c) {
            case A:
                rectangle = rectLeft;
                break;
            case D:
                rectangle = rectRight;
                break;
            case W:
                rectangle = rectJump;
                break;
            case SPACE:
                rectangle = rectShoot;
                break;
            default:
                rectangle = rectOther;
                break;
        }
        return rectangle;
    }



}
