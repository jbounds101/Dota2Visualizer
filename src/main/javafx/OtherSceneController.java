package main.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OtherSceneController {

    @FXML
    Label nameLabel;
    public void displayName(String username) {
        nameLabel.setText("Hello: " + username);
    }

}
