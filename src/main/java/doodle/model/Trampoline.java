package doodle.model;

import doodle.FileUtils;
import javafx.scene.image.Image;

public class Trampoline extends Jumper {

    private static final Image IMAGE = FileUtils.loadImage("images/game-tiles.png", 188, 94, 36, 16);
    private static final Image IMAGE_2 = FileUtils.loadImage("images/game-tiles.png", 150, 94, 36, 16);

    public Trampoline() {
        super(36, 16, 1000, IMAGE, IMAGE_2);
        getColliderRect().setTranslateY(0.2 * 16);
        getColliderRect().setHeight(0.8 * 16);
    }

}
