package main.resources;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.IOException;

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
        Group root = new Group(); // Root node is how a scene is organized
        Scene scene = new Scene(root, Color.BLACK); // Must pass a root node, arranged in different type based on root
        stage.setTitle("This is the stage!");
        Image icon = new Image("/javafx_images/icon.png"); // Image from javafx (don't include source,
        // it assumes src is the starting dir)
        stage.getIcons().add(icon); // Set icon at top left corner
        stage.setWidth(400);
        stage.setHeight(400);
        stage.setResizable(false);


        stage.setScene(scene); // Add scene to stage
        stage.show();
    }
}