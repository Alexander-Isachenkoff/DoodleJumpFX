package doodle.model;

import doodle.FileUtils;
import javafx.scene.image.Image;

public class StaticPlatform extends Platform {

    private static final Image IMAGE = FileUtils.loadImage("images/game-tiles.png", 0, 1, 58, 18);

    public StaticPlatform() {
        super(IMAGE);
    }

}
