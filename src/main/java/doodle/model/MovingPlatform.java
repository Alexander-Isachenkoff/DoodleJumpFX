package doodle.model;

import doodle.FileUtils;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class MovingPlatform extends Platform {

    private static final Image IMAGE = FileUtils.loadImage("images/game-tiles.png", 0, 18, 58, 18);
    private final TranslateTransition tt;

    public MovingPlatform() {
        super(IMAGE);
        tt = new TranslateTransition();
        tt.setNode(this);
        tt.setAutoReverse(true);
        tt.setCycleCount(Animation.INDEFINITE);
        tt.setInterpolator(Interpolator.LINEAR);
    }

    public void run(double fromX, double toX) {
        tt.setFromX(fromX);
        tt.setToX(toX);
        tt.setDuration(Duration.seconds(Math.abs(fromX - toX) / 100));
        tt.play();
    }

}
