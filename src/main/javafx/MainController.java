package main.javafx;

import dotaobject.Heroes;
import dotaobject.Match;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.DotaJsonParser;


public class MainController {
    // Used for all the intractable JavaFX objects
    @FXML
    LineChart<Number, Number> lineChart;

    public void button(ActionEvent event) {
        lineChart.getData().clear();
        Match match = DotaJsonParser.readMatch(6643139986L);
        assert match != null;
        int[] radiantGoldAdvantage = match.getRadiantGoldAdvantage();
        int minutes = match.getMinutesPlayed();
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        for (int i = 0; i < minutes; i++) {
            series.getData().add(new XYChart.Data<>(i, radiantGoldAdvantage[i]));
        }
        lineChart.getData().add(series);
    }

}
