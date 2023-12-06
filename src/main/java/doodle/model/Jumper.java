package doodle.model;

import javafx.scene.image.Image;

public class Jumper extends GameObject {

    private final double jumpStartSpeed;

    public Jumper(double width, double height, double jumpStartSpeed, Image image) {
        super(width, height, image);
        this.jumpStartSpeed = jumpStartSpeed;
    }

    public double getJumpStartSpeed() {
        return jumpStartSpeed;
    }

}
