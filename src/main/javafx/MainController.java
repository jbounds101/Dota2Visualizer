package main.javafx;

import dotaobject.*;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import main.DotaJsonParser;
import org.apache.hc.client5.http.utils.DateUtils;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalTime;


public class MainController {
    // Used for all the intractable JavaFX objects


    @FXML
    StackPane stackPane;
    @FXML
    TextField searchBar;
    @FXML
    ImageView searchIcon;
    @FXML
    HBox errorMatchPane; // This is the match id couldn't be found error
    @FXML
    ImageView loadingPane;
    @FXML
    ScrollPane overviewPane;
    @FXML
    ScrollPane graphPane;
    @FXML
    Label overviewButtonLabel;
    @FXML
    Label graphButtonLabel;

    // -----Overview-----
    @FXML
    Label radiantScoreLabel;
    @FXML
    Label direScoreLabel;
    @FXML
    Label timeLabel;
    @FXML
    VBox overviewGrid;
    @FXML
    HBox victoryLabelBackground;
    @FXML
    Label victoryLabel;
    @FXML
    Label matchIDLabel;
    @FXML
    HBox subBar;
    @FXML
    HBox subBarButtons;
    // -----

    // -----Graphs-----
    @FXML
    LineChart<Number, Number> lineChart;
    // -----

    private Match match;
    boolean matchLoaded = false;

    // #c177a8 : color of search icon


    public void labelButtonPress(MouseEvent event) {
        Label requester = (Label)event.getSource();
        requester.requestFocus();
        setSubButtonLabelSelected(requester);
        if (requester == overviewButtonLabel) {
            openOnStackPane(overviewPane);
        } else if (requester == graphButtonLabel) {
            openOnStackPane(graphPane);
        }
    }

    public void matchTextFieldSearch(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            matchLoaded = false;
            openOnStackPane(loadingPane);
            try {
                long matchID = Long.parseLong(searchBar.getText());
                searchBar.setText("");
                Task task = new Task<Void>() {
                    @Override
                    protected Void call() {
                        loadMatchDetails(matchID);
                        return null;
                    }
                };
                new Thread(task).start();
            } catch (NumberFormatException e) {
                findMatchError();
            }
        }
    }

    public void loadMatchDetails(Long matchID) {
        try {
            showSubBar(false);
            match = DotaJsonParser.readMatch(matchID);
            if (match.getDuration() == 0) throw new MatchNotFoundException();
            loadMatch();
            setSubButtonLabelSelected(overviewButtonLabel);
            openOnStackPane(overviewPane);
            showSubBar(true);
        } catch (MatchNotFoundException e) {
            findMatchError();
            matchLoaded = false;
        }
    }

    public void loadMatch() {
        // This applies all the visuals to the GUI and prepares each pane in the stackPane to be shown


        Platform.runLater(() -> {
            // Sub-bar
            radiantScoreLabel.setText(Integer.toString(match.getRadiantScore()));
            int duration = match.getDuration();
            long hours = duration / 3600;
            long minutes = (duration % 3600) / 60;
            long seconds = duration % 60;
            if (hours > 0) {
                timeLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
            } else {
                timeLabel.setText(String.format("%02d:%02d", minutes, seconds));
            }
            direScoreLabel.setText(Integer.toString(match.getDireScore()));
            matchIDLabel.setText(Long.toString(match.getMatchID()));
            if (match.isRadiantWin()) {
                victoryLabel.setTextFill(Paint.valueOf("#A0FF9C"));
                victoryLabel.setText("Radiant Victory");
                victoryLabelBackground.setBackground(new Background(
                        new BackgroundFill(Paint.valueOf("rgba(37, 150, 190, 0.1)"),
                                new CornerRadii(6), new Insets(10, 6, 10, 6))));
            } else {
                victoryLabel.setTextFill(Paint.valueOf("#ff3d3d"));
                victoryLabel.setText("Dire Victory");
                victoryLabelBackground.setBackground(new Background(
                        new BackgroundFill(Paint.valueOf("rgba(255, 61, 61, 0.1)"),
                                new CornerRadii(6), new Insets(10, 6, 10, 6))));

            }
            //

            // Overview pane
            Player[] players = match.getPlayers();
            int heroesPrinted = 0;
            for (Node child : overviewGrid.getChildren()) {
                ObservableList<String> styleClasses = child.getStyleClass();
                boolean isStatsBar = false;
                for (int i = 0; i < styleClasses.size(); i++) {
                    String style = styleClasses.get(i);
                    if (style.equals("stats-bar")) {
                        isStatsBar = true;
                        break;
                    }
                }
                if (isStatsBar) {
                    ObservableList<Node> children = ((HBox) child).getChildren();
                    ImageView heroImgNode = (ImageView) children.get(0);
                    Label nameNode = (Label) children.get(1);
                    Label lvlNode = (Label) children.get(2);
                    Label killsNode = (Label) children.get(3);
                    Label deathNode = (Label) children.get(4);
                    Label assistsNode = (Label) children.get(5);
                    Label lastHitsDeniesNode = (Label) children.get(6);
                    Label netWorthNode = (Label) children.get(7);
                    Label gpmXPMNode = (Label) children.get(8);
                    ImageView[] itemImageNodes = new ImageView[6];
                    itemImageNodes[0] = (ImageView) children.get(9);
                    itemImageNodes[1] = (ImageView) children.get(10);
                    itemImageNodes[2] = (ImageView) children.get(11);
                    itemImageNodes[3] = (ImageView) children.get(12);
                    itemImageNodes[4] = (ImageView) children.get(13);
                    itemImageNodes[5] = (ImageView) children.get(14);
                    ImageView neutralItemNode = (ImageView) children.get(15);

                    Player currentPlayer = players[heroesPrinted];

                    Platform.runLater(() -> {

                        nameNode.setText(currentPlayer.getPlayerName());
                        heroImgNode.setImage(SwingFXUtils.toFXImage(currentPlayer.getHero().getImg(), null));
                        lvlNode.setText(Integer.toString(currentPlayer.getLevel()));
                        killsNode.setText(Integer.toString(currentPlayer.getKills()));
                        deathNode.setText(Integer.toString(currentPlayer.getDeaths()));
                        assistsNode.setText(Integer.toString(currentPlayer.getAssists()));
                        int playerLastHits = currentPlayer.getLastHits();
                        int playerDenies = currentPlayer.getDenies();
                        lastHitsDeniesNode.setText(playerLastHits + "/" + playerDenies);
                        netWorthNode.setText(createThousandSuffix(currentPlayer.getNetWorth()));
                        int playerGPM = currentPlayer.getGpm();
                        int playerXPM = currentPlayer.getXpm();
                        gpmXPMNode.setText(playerGPM + "/" + playerXPM);

                        int itemIndex = 0;
                        Item[] items = currentPlayer.getItems();
                        for (Item item : items) {
                            if (item == null) {
                                itemImageNodes[itemIndex].setImage(
                                        new Image("/main/javafx/static_images/transparentItem.png"));
                            } else {
                                BufferedImage image = item.loadImage();
                                itemImageNodes[itemIndex].setImage(SwingFXUtils.toFXImage(image, null));
                            }

                            itemIndex++;
                        }
                        Item neutralItem = currentPlayer.getNeutralItem();
                        if (neutralItem == null) {
                            neutralItemNode.setImage(new Image("/main/javafx/static_images/transparentItem.png"));
                        } else {
                            BufferedImage image = neutralItem.loadImage();
                            neutralItemNode.setImage(SwingFXUtils.toFXImage(image, null));
                        }


                        boolean radiantLabel = true;
                        for (Node child1 : overviewGrid.getChildren()) {
                            ObservableList<String> styleClasses1 = child1.getStyleClass();
                            boolean isLabel = false;
                            for (int i = 0; i < styleClasses1.size(); i++) {
                                String style = styleClasses1.get(i);
                                if (style.equals("label")) {
                                    isLabel = true;
                                    break;
                                }
                            }
                            if (isLabel) {
                                if (radiantLabel) {
                                    radiantLabel = false;
                                    Label radiantTextLabel = (Label) child1;
                                    if (match.isRadiantWin()) {
                                        radiantTextLabel.setText("Radiant Overview (Winner)");
                                    } else {
                                        radiantTextLabel.setText("Radiant Overview");
                                    }
                                } else {
                                    Label direTextLabel = (Label) child1;
                                    if (!match.isRadiantWin()) {
                                        direTextLabel.setText("Dire Overview (Winner)");
                                    } else {
                                        direTextLabel.setText("Dire Overview");
                                    }
                                }
                            }
                        }
                    });
                    heroesPrinted++;
                }
            }
            //

            // Graph pane
            lineChart.getData().clear();
            int[] radiantGoldAdvantage = match.getRadiantGoldAdvantage();
            minutes = match.getMinutesPlayed(); // Override from earlier, could be different from the value we show
            // (at least I think)
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            for (int i = 0; i < minutes; i++) {
                series.getData().add(new XYChart.Data<>(i, radiantGoldAdvantage[i]));
            }
            lineChart.getData().add(series);
            //


        });
        //

        matchLoaded = true;

    }

    public static String createThousandSuffix(long count) {
        if (count < 1000) return "" + count;
        int exp = (int) (Math.log(count) / Math.log(1000));
        return String.format("%.1f%c",
                count / Math.pow(1000, exp),
                "kMGTPE".charAt(exp-1));
    }

    public void openOnStackPane(Node node) {
        for (Node child : stackPane.getChildren()) {
            child.setVisible(false);
        }
        node.setVisible(true);
    }

    public void showSubBar(boolean b) {
        for (Node child : subBar.getChildren()) {
            child.setVisible(b);
        }
        subBarButtons.setVisible(b);
    }

    public void findMatchError() {
        openOnStackPane(errorMatchPane);
        showSubBar(false);
    }

    public void setSubButtonLabelSelected(Label buttonLabel) {
        Platform.runLater(() -> {
        });
    }
}
