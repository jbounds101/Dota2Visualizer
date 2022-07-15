package main.javafx;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;


public class MainController {
    // Used for all the intractable JavaFX objects
    @FXML
    LineChart<Number, Number> lineChart;
    @FXML
    TextField searchBar;


    private Stage stage;
    private Scene scene;
    private Parent root;
    private double windowWidth;
    private double windowHeight;



    public void labelButtonPress(MouseEvent event) throws IOException {
        Label requester = (Label)event.getSource();
        requester.requestFocus();
    }

    public void matchTextFieldSearch(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            System.out.println("check");
        }
    }

}
