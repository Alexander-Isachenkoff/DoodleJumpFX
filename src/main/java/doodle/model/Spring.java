package doodle.model;

import doodle.FileUtils;

public class Spring extends Jumper {

    public Spring() {
        super(16, 13, 800, FileUtils.loadImage("images/game-tiles.png", 405, 98, 16, 13));
    }

}
