package doodle.model;

import doodle.FileUtils;
import javafx.scene.image.Image;

import java.util.Arrays;
import java.util.List;

public class Jetpack extends Booster {

    public Jetpack() {
        super(34, 18, 24, 35, 8, 500, FileUtils.loadImage("images/game-tiles.png", 198, 266, 24, 35));
        List<Image> images = Arrays.asList(
                FileUtils.loadImage("images/jetpack.png", 0, 0, 32, 64),
                FileUtils.loadImage("images/jetpack.png", 32, 0, 32, 64),
                FileUtils.loadImage("images/jetpack.png", 64, 0, 32, 64),
                FileUtils.loadImage("images/jetpack.png", 96, 0, 32, 64),
                FileUtils.loadImage("images/jetpack.png", 0, 64, 32, 64),
                FileUtils.loadImage("images/jetpack.png", 32, 64, 32, 64),
                FileUtils.loadImage("images/jetpack.png", 64, 64, 32, 64),
                FileUtils.loadImage("images/jetpack.png", 96, 64, 32, 64),
                FileUtils.loadImage("images/jetpack.png", 0, 128, 32, 64),
                FileUtils.loadImage("images/jetpack.png", 32, 128, 32, 64)
        );
        initAnimation(images);
    }

}
