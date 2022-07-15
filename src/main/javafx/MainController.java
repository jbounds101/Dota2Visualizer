package main.javafx;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;


public class MainController {
    // Used for all the intractable JavaFX objects
    @FXML
    LineChart<Number, Number> lineChart;


    private Stage stage;
    private Scene scene;
    private Parent root;
    private double windowWidth;
    private double windowHeight;


    public void labelButtonPress(MouseEvent event) throws IOException {
        Label requester = (Label)event.getSource();
        requester.requestFocus();
    }

}
