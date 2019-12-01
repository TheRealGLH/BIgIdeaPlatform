package gamegui.controllers;

import gamegui.GUIScreenController;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Date;


public class PlatformInputTestController {

    public Rectangle rectLeft;
    public Rectangle rectShoot;
    public Rectangle rectJump;
    public Rectangle rectRight;
    public Rectangle rectOther;
    private Color red = Color.RED;
    private Color white = Color.WHITE;

    @FXML
    void handleKeyDown(KeyEvent e) {
        System.out.println(new Date()+ " :"+e.getCode());
        getRect(e.getCode()).setFill(red);
    }


    @FXML
    void handleKeyReleased(KeyEvent e) {
        getRect(e.getCode()).setFill(white);
    }

    @FXML
    void backClicked(){
        GUIScreenController.getInstance().showMainMenu();
    }

    Rectangle getRect(KeyCode c) {
        Rectangle rectangle = null;
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
