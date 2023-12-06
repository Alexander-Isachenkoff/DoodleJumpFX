package doodle.model;

import doodle.FileUtils;
import javafx.animation.PauseTransition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

public class Doodle extends GameObject {

    private static final double JUMP_SPEED = -600;
    private static final double SIDE_ACC = 600;
    private static final double SIDE_RESISTANCE = 200;
    private static final double MAX_SIDE_SPEED = 250;
    private static final double GRAVITY = 600;

    private static final Image image = FileUtils.loadImage("images/lik-left.png");
    private static final Image jumpImage = FileUtils.loadImage("images/lik-left-odskok.png");
    private Booster booster;

    private double vSpeed = 0;
    private double hSpeed = 0;

    public Doodle() {
        super(62, 60, image);

        getColliderRect().setTranslateX(0.25 * getWidth());
        getColliderRect().setWidth(0.5 * getWidth());
        getColliderRect().setTranslateY(0.25 * getHeight());
        getColliderRect().setHeight(0.75 * getHeight());
    }

    public boolean isBoosted() {
        return booster != null && booster.isActive();
    }

    public void startBooster(Booster booster) {
        if (isBoosted()) {
            this.booster.stop();
        }
        this.booster = booster;
        booster.setTranslateX(booster.getPlayerX());
        booster.setTranslateY(booster.getPlayerY());
        booster.setOnEnd(() -> getChildren().remove(this.booster));
        getChildren().add(booster);
        booster.start();
    }

    public boolean isJumpFromTop(GameObject gameObject) {
        if (this.intersects(gameObject)) {
            if (this.getTranslateY() + this.getHeight() < gameObject.getTranslateY() + gameObject.getHeight()) {
                return this.getVSpeed() > 0;
            }
        }
        return false;
    }

    public void move(double seconds) {
        if (booster != null && booster.isActive()) {
            setVSpeed(-booster.getSpeed());
        } else {
            this.setVSpeed(this.getVSpeed() + GRAVITY * seconds);
        }

        double res1 = ((getHSpeed() > 0) ? -SIDE_RESISTANCE : SIDE_RESISTANCE);
        this.setHSpeed(this.getHSpeed() + res1 * seconds);

        this.addY(this.getVSpeed() * seconds);
        this.addX(this.getHSpeed() * seconds);
    }

    public void jump() {
        this.setVSpeed(JUMP_SPEED);
        getImgRect().setFill(new ImagePattern(jumpImage));
        PauseTransition tr = new PauseTransition(Duration.millis(100));
        tr.setOnFinished(event -> getImgRect().setFill(new ImagePattern(image)));
        tr.play();
    }

    public void moveLeft(double seconds) {
        this.setHSpeed(getHSpeed() - SIDE_ACC * seconds);
        setScaleX(1);
    }

    public void moveRight(double seconds) {
        this.setHSpeed(getHSpeed() + SIDE_ACC * seconds);
        setScaleX(-1);
    }

    public double getHSpeed() {
        return hSpeed;
    }

    public void setHSpeed(double speed) {
        this.hSpeed = Math.max(-MAX_SIDE_SPEED, Math.min(MAX_SIDE_SPEED, speed));
    }

    public double getVSpeed() {
        return vSpeed;
    }

    public void setVSpeed(double vSpeed) {
        this.vSpeed = vSpeed;
    }

}
