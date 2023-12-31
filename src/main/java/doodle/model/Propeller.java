package doodle.model;

import doodle.FileUtils;
import doodle.SpriteMap;
import javafx.scene.image.Image;

import java.util.List;

public class Propeller extends Booster {

    public Propeller() {
        super(15, -7, 32, 32, 5, 300, FileUtils.loadImage("images/propeller.png", 0, 0, 32, 32));
        List<Image> images = SpriteMap.load("images/propeller.xml");
        initAnimation(images);
    }

}
