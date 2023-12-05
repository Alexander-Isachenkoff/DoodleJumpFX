package doodle.model;

import javafx.animation.AnimationTimer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GamePane extends Pane {

    public static final int HEIGHT = 512;
    public static final int WIDTH = 320;
    private final AnimationTimer timer;
    private final Doodle doodle = new Doodle();
    private final Set<Platform> platforms = new HashSet<>();
    private final Set<GameObject> gameObjects = new HashSet<>();
    private final IntegerProperty score = new SimpleIntegerProperty(-1);
    private final IntegerProperty bestScore = new SimpleIntegerProperty(-1);
    private final Set<KeyCode> keysPressed = new HashSet<>();
    private final Random random = new Random();
    private long lastUpdateTime;
    private double maxHeight = -500;
    private double nextSpawnPlatformsY;

    public GamePane() {
        setPrefWidth(WIDTH);
        setPrefHeight(HEIGHT);

        score.addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() > bestScore.get()) {
                bestScore.set(newValue.intValue());
            }
        });
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (lastUpdateTime == 0) {
                    lastUpdateTime = now;
                }
                update(now);
            }
        };

        sceneProperty().addListener((observable, oldValue, scene) -> {
            if (scene != null) {
                scene.setOnKeyPressed(event -> {
                    keysPressed.add(event.getCode());
                });
                scene.setOnKeyReleased(event -> {
                    keysPressed.remove(event.getCode());
                });
            }
        });

        spawnNewPlatforms();
        spawnNewPlatforms();
        spawnPlatform(0, 550);

        getChildren().add(doodle);
        doodle.setTranslateY(500);

        doodle.translateYProperty().addListener((observable, oldValue, newValue) -> {
            int height = -newValue.intValue();
            if (height > maxHeight) {
                maxHeight = height;
            }
            score.set((int) maxHeight / 10);
            setTranslateY(maxHeight + 300);

            if (-getTranslateY() - getHeight() < nextSpawnPlatformsY) {
                spawnNewPlatforms();
            }
        });
    }

    private void spawnNewPlatforms() {
        int count = (int) Math.round(25 / (maxHeight / 20000 + 1));
        for (int i = 1; i <= count; i++) {
            spawnRandomPlatform();
        }
        nextSpawnPlatformsY = nextSpawnPlatformsY - HEIGHT;
    }

    public void restart() {
        score.set(-1000);
        lastUpdateTime = 0;
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    private void update(long now) {
        double dtSeconds = (now - lastUpdateTime) / 1e9;

        if (keysPressed.contains(KeyCode.LEFT)) {
            doodle.moveLeft(dtSeconds);
        }
        if (keysPressed.contains(KeyCode.RIGHT)) {
            doodle.moveRight(dtSeconds);
        }

        doodle.move(dtSeconds);
        if (!doodle.isBoosted()) {
            for (Platform platform : platforms) {
                if (doodle.intersects(platform)) {
                    if (doodle.getTranslateY() + doodle.getHeight() < platform.getTranslateY() + platform.getHeight()) {
                        if (doodle.getVSpeed() > 0) {
                            doodle.jump();
                        }
                    }
                }
            }
        }
        for (GameObject gameObject : new HashSet<>(gameObjects)) {
            if (doodle.intersects(gameObject)) {
                gameObjects.remove(gameObject);
                getChildren().remove(gameObject);
                if (gameObject instanceof Booster) {
                    doodle.startBooster((Booster) gameObject);
                }
            }
        }

        lastUpdateTime = now;
    }

    private void spawnRandomPlatform() {
        int x = random.nextInt(WIDTH - 58);
        int y = (int) (random.nextInt(HEIGHT - 10) + nextSpawnPlatformsY);
        spawnPlatform(x, y);
    }

    private void drawBooster(int x, int y) {
        if (random.nextDouble() < 1. / 200) {
            Propeller propeller = new Propeller();
            spawnBooster(propeller, x + 20, (int) (y - propeller.getHeight()));
        }
        if (random.nextDouble() < 1. / 250) {
            Jetpack jetpack = new Jetpack();
            spawnBooster(jetpack, x + 20, (int) (y - jetpack.getHeight()));
        }
        if (random.nextDouble() < 1. / 400) {
            Rocket rocket = new Rocket();
            spawnBooster(rocket, x, (int) (y - rocket.getHeight()));
        }
    }

    private void spawnBooster(Booster booster, int x, int y) {
        booster.setTranslateX(x);
        booster.setTranslateY(y);
        getChildren().add(0, booster);
        gameObjects.add(booster);
    }

    private void spawnPlatform(int x, int y) {
        Platform platform;
        if (random.nextDouble() > (1 / (maxHeight / 20000 + 1))) {
            platform = new MovingPlatform();
            int toX;
            do {
                toX = random.nextInt(WIDTH - 58);
            } while (Math.abs(x - toX) < 50);
            ((MovingPlatform) platform).run(toX);
        } else {
            platform = new StaticPlatform();
            drawBooster(x, y);
        }
        platform.setTranslateX(x);
        platform.setTranslateY(y);
        getChildren().add(0, platform);
        platforms.add(platform);
    }

    public ReadOnlyIntegerProperty scoreProperty() {
        return score;
    }

}
