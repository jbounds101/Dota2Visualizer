package main.resources;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;

public class Controller {
    // Used for all the intractable JavaFX objects

    @FXML
    private Circle circle;
    private double x;
    private double y;

    public void right(ActionEvent e) {
        System.out.println("right");
        circle.setCenterX(x += 10);
    }
    public void up(ActionEvent e) {
        System.out.println("up");
        circle.setCenterY(y -= 10);
    }
    public void left(ActionEvent e) {
        System.out.println("left");
        circle.setCenterX(x -= 10);
    }
    public void down(ActionEvent e) {
        System.out.println("down");
        circle.setCenterY(y += 10);
    }


}
