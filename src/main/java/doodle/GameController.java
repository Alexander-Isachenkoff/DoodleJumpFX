package doodle;

import doodle.model.GamePane;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

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
        gamePane.translateYProperty().addListener((observable, oldValue, newValue) -> updateBackground());
        gamePane.restart();
    }

    private void updateBackground() {
        BackgroundPosition position = new BackgroundPosition(Side.LEFT, 0, false, Side.TOP, gamePane.getTranslateY(), false);
        root.setBackground(new Background(new BackgroundImage(bgImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.REPEAT, position, BackgroundSize.DEFAULT)));
    }

}
