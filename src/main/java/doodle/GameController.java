package doodle;

import doodle.model.BestScore;
import doodle.model.BestScores;
import doodle.model.GamePane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.stream.Collectors;

public class GameController {

    private final Image bgImage = FileUtils.loadImage("images/bck.png");
    private final GamePane gamePane = new GamePane();
    private final DecimalFormat format = getDecimalFormatter();
    public AnchorPane root;
    public Label scoreLabel;

    private static DecimalFormat getDecimalFormatter() {
        DecimalFormat format = new DecimalFormat();
        format.setGroupingSize(3);
        DecimalFormatSymbols symbols = format.getDecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        format.setDecimalFormatSymbols(symbols);
        return format;
    }

    @FXML
    private void initialize() {
        root.getChildren().add(0, gamePane);
        gamePane.scoreProperty().addListener((observable, oldValue, newValue) -> {
            String text = format.format(newValue.intValue());
            scoreLabel.setText(text);
        });
        gamePane.setOnGameOver(this::onGameOver);
        gamePane.translateYProperty().addListener((observable, oldValue, newValue) -> updateBackground());
        gamePane.restart();
    }

    private void updateBackground() {
        BackgroundPosition position = new BackgroundPosition(Side.LEFT, 0, false, Side.TOP, gamePane.getTranslateY(), false);
        root.setBackground(new Background(new BackgroundImage(bgImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.REPEAT, position, BackgroundSize.DEFAULT)));
    }

    private void onGameOver() {
        List<BestScore> bestScores = BestScores.load();
        int score = gamePane.scoreProperty().get();
        int order = (int) bestScores.stream()
                .filter(bestScore -> bestScore.getScore() >= score)
                .count();
        int limit = 10;
        if (order < limit) {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/input.fxml"));
            Parent load;
            try {
                load = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            InputNameController controller = loader.getController();

            controller.setOnSave(name -> {
                root.getChildren().remove(load);
                bestScores.add(order, new BestScore(order + 1, name, score));
                new BestScores(bestScores.stream().limit(limit).collect(Collectors.toList())).save();
                Main.toBestScores();
            });

            showPopupPane(load);
        } else {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/game_over.fxml"));
            Parent load;
            try {
                load = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            GameOverController controller = loader.getController();

            controller.setOnMenu(this::onMenu);
            controller.setOnRestart(() -> {
                root.getChildren().remove(load);
                gamePane.restart();
            });

            showPopupPane(load);
        }
    }

    private void showPopupPane(Parent parent) {
        root.getChildren().add(parent);
        AnchorPane.setTopAnchor(parent, 0.0);
        AnchorPane.setBottomAnchor(parent, 0.0);
        AnchorPane.setLeftAnchor(parent, 0.0);
        AnchorPane.setRightAnchor(parent, 0.0);
    }

    @FXML
    private void onMenu() {
        gamePane.stop();
        Main.toMainMenu();
    }

}
