package main.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    // Used for all the intractable JavaFX objects

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchToOtherScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/main/javafx/otherscene.fxml")); // Read from file
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMainScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/main/javafx/main.fxml")); // Read from file
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
