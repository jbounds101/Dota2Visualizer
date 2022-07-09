package main.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginSceneController {
    // Used for all the intractable JavaFX objects

    @FXML
    TextField nameTextField;

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void login(ActionEvent event) throws IOException {

        String username = nameTextField.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/javafx/otherscene.fxml"));
        root = loader.load();

        OtherSceneController otherSceneController = loader.getController();
        otherSceneController.displayName(username);

        //root = FXMLLoader.load(getClass().getResource("/main/javafx/otherscene.fxml")); // Read from file
        stage = (Stage)((Node)event.getSource()).getScene().getWindow(); // Get the current stage
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
