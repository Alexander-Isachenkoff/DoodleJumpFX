package doodle.model;

import doodle.FileUtils;
import javafx.scene.image.Image;

public class Platform extends GameObject {

    public static final Image IMAGE = FileUtils.loadImage("images/game-tiles.png", 0, 0, 58, 16);

    public Platform() {
        super(58, 16, IMAGE);
    }

}
