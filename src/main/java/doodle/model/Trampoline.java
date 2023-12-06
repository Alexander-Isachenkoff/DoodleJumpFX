package doodle.model;

import doodle.FileUtils;

public class Trampoline extends Jumper {

    public Trampoline() {
        super(36, 13, 1000, FileUtils.loadImage("images/game-tiles.png", 188, 97, 36, 13));
    }

}
