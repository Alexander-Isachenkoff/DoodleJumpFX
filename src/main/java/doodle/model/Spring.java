package doodle.model;

import doodle.FileUtils;
import javafx.scene.image.Image;

public class Spring extends Jumper {

    private static final Image IMAGE = FileUtils.loadImage("images/game-tiles.png", 405, 83, 16, 28);
    private static final Image IMAGE_2 = FileUtils.loadImage("images/game-tiles.png", 405, 114, 16, 28);

    public Spring() {
        super(16, 28, 800, IMAGE, IMAGE_2);
        getColliderRect().setTranslateY(0.54 * 28);
        getColliderRect().setHeight(0.46 * 28);
    }

}
