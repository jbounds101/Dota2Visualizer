package main.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
        Parent root = FXMLLoader.load(getClass().getResource("/main/javafx/main.fxml")); // Read from file
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMinWidth(1280);
        stage.setMinHeight(720);
        stage.setTitle("Dota 2 Statistics Visualizer");
        Label heroStatsLabel = (Label)scene.lookup("#overviewButtonLabel");
        heroStatsLabel.requestFocus();


        stage.show();

    }
}