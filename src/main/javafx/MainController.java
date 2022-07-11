package main.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;

import javafx.stage.Stage;

import java.io.IOException;


public class MainController {
    // Used for all the intractable JavaFX objects
    @FXML
    LineChart<Number, Number> lineChart;

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void matchAnalysisButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/main/javafx/matchAnalysis.fxml")); // Read from file
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
