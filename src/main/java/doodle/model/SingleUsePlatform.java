package doodle.model;

import doodle.FileUtils;
import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class SingleUsePlatform extends Platform {

    private static final Image IMAGE = FileUtils.loadImage("images/game-tiles.png", 0, 54, 58, 18);

    public SingleUsePlatform() {
        super(IMAGE);
    }

    public void disappear() {
        FadeTransition ft = new FadeTransition(Duration.millis(200), this);
        ft.setToValue(0);
        ft.setOnFinished(event -> {
            ((Pane) this.getParent()).getChildren().remove(this);
        });
        ft.play();
    }

}
