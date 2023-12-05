package doodle.model;

import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

import java.util.List;

public class Booster extends GameObject {

    protected final Timeline animation = new Timeline();
    protected final PauseTransition pause = new PauseTransition();
    private final double playerX;
    private final double playerY;
    private final double speed;
    private Runnable onEnd = () -> {
    };

    public Booster(double playerX, double playerY, double width, double height, double duration, double speed, Image image) {
        super(width, height, image);
        this.playerX = playerX;
        this.playerY = playerY;
        this.speed = speed;
        pause.setDuration(Duration.seconds(duration));
    }

    public double getPlayerX() {
        return playerX;
    }

    public double getPlayerY() {
        return playerY;
    }

    public void start() {
        if (isActive()) {
            stop();
        }
        animation.play();
        pause.play();
    }

    void stop() {
        pause.stop();
        animation.stop();
        onEnd.run();
    }

    public boolean isActive() {
        return animation.getStatus() == Animation.Status.RUNNING;
    }

    public void setOnEnd(Runnable onEnd) {
        this.onEnd = onEnd;
    }

    public double getSpeed() {
        return speed;
    }

    protected void initAnimation(List<Image> images) {
        animation.setCycleCount(Animation.INDEFINITE);
        animation.setAutoReverse(true);
        for (int i = 0; i < images.size(); i++) {
            Image image = images.get(i);
            KeyFrame keyFrame = new KeyFrame(Duration.millis(50 * i), event -> {
                getImgRect().setFill(new ImagePattern(image));
                getImgRect().setWidth(image.getWidth());
                getImgRect().setHeight(image.getHeight());
            });
            animation.getKeyFrames().add(keyFrame);
        }
        pause.setOnFinished(event -> stop());
    }
}
