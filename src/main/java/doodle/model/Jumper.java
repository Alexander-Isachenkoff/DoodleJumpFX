package doodle.model;

import javafx.animation.PauseTransition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

public class Jumper extends GameObject {

    private final double jumpStartSpeed;
    private final Image activatedImage;

    public Jumper(double width, double height, double jumpStartSpeed, Image image, Image activatedImage) {
        super(width, height, image);
        this.jumpStartSpeed = jumpStartSpeed;
        this.activatedImage = activatedImage;
    }

    public double getJumpStartSpeed() {
        return jumpStartSpeed;
    }

    public void activate() {
        PauseTransition pause = new PauseTransition(Duration.millis(100));
        getImgRect().setFill(new ImagePattern(activatedImage));
        getImgRect().setWidth(activatedImage.getWidth());
        getImgRect().setHeight(activatedImage.getHeight());
        pause.setOnFinished(event -> {
            getImgRect().setFill(new ImagePattern(getDefaultImage()));
            getImgRect().setWidth(getDefaultImage().getWidth());
            getImgRect().setHeight(getDefaultImage().getHeight());
        });
        pause.play();
    }

}
