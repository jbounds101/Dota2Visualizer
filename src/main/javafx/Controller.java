package main.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    // Used for all the intractable JavaFX objects


    @FXML
    ImageView imageViewFirst;
    Button myButton;

    Image myImage = new Image(getClass().getResourceAsStream("..\\..\\hero_images\\2img.png"));

    public void displayImage() {
        imageViewFirst.setImage(myImage);
    }



}
