package doodle.model;

import doodle.FileUtils;
import doodle.SpriteMap;
import javafx.scene.image.Image;

import java.util.List;

public class Rocket extends Booster {

    public Rocket() {
        super(0, -48, 72, 105, 10, 750, FileUtils.loadImage("images/rocket_static.png"));
        List<Image> images = SpriteMap.load("images/rocket.xml");
        initAnimation(images);
    }

}
