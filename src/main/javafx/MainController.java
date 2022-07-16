package main.javafx;

import dotaobject.Match;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.DotaJsonParser;

import java.io.IOException;


public class MainController {
    // Used for all the intractable JavaFX objects
    @FXML
    LineChart<Number, Number> lineChart;
    @FXML
    TextField searchBar;
    @FXML
    ImageView loadingIcon;
    @FXML
    ImageView searchIcon;
    @FXML
    HBox errorMessage; // This is the match id couldn't be found error


    private Stage stage;
    private Scene scene;
    private Parent root;
    private double windowWidth;
    private double windowHeight;
    private Match match;

    // #c177a8 : color of search icon


    public void labelButtonPress(MouseEvent event) throws IOException {
        Label requester = (Label)event.getSource();
        requester.requestFocus();
    }

    public void matchTextFieldSearch(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            errorMessage.setVisible(false);
            loadingIcon.setVisible(true);
            try {
                long matchID = Long.parseLong(searchBar.getText());
                new Thread(() -> {
                    loadMatchDetails(matchID);
                }).start();
            } catch (NumberFormatException e) {
                searchBar.setText("");
                errorMessage.setVisible(true);
                loadingIcon.setVisible(false);
            }

        }
    }

    public void loadMatchDetails(Long matchID) {
        match = DotaJsonParser.readMatch(matchID);
        loadingIcon.setVisible(false);
    }


}
