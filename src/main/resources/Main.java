package main.resources;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {
    /*  1. Stage (the window essentially)
        2. Scene (goes onto stage)
        3. Elements
     */

    public static void main(String[] args) {
        Application.launch(args); // launch is a static method inherited from the Application class

    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/main/resources/main.fxml")); // Read from file
        stage.setTitle("Hello!");
        stage.setScene(new Scene(root));
        stage.show();
    }
}