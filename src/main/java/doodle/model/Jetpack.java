package doodle.model;

import doodle.FileUtils;
import doodle.SpriteMap;
import javafx.scene.image.Image;

import java.util.List;

public class Jetpack extends Booster {

    public Jetpack() {
        super(34, 18, 24, 35, 8, 500, FileUtils.loadImage("images/game-tiles.png", 198, 266, 24, 35));
        List<Image> images = SpriteMap.load("images/jetpack.xml");
        initAnimation(images);
    }

}
