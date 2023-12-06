package doodle.model;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class GameObject extends Group {

    private final Rectangle imgRect = new Rectangle();
    private final Rectangle colliderRect = new Rectangle();
    private final Image defaultImage;

    public GameObject(double width, double height, Image image) {
        this.defaultImage = image;
        imgRect.setWidth(width);
        imgRect.setHeight(height);
        getChildren().addAll(imgRect, colliderRect);
        imgRect.setFill(new ImagePattern(image));
        colliderRect.setFill(Color.TRANSPARENT);
        //colliderRect.setStroke(Color.RED);
        colliderRect.setWidth(width);
        colliderRect.setHeight(height);
    }

    public void addX(double x) {
        setTranslateX(getTranslateX() + x);
    }

    public void addY(double y) {
        setTranslateY(getTranslateY() + y);
    }

    Rectangle getImgRect() {
        return imgRect;
    }

    Rectangle getColliderRect() {
        return colliderRect;
    }

    private Bounds getColliderBounds() {
        return localToParent(colliderRect.getBoundsInParent());
    }

    public boolean intersects(GameObject gameObject) {
        return this.getColliderBounds().intersects(gameObject.getColliderBounds());
    }

    public double getWidth() {
        return imgRect.getWidth();
    }

    public double getHeight() {
        return imgRect.getHeight();
    }

    public Image getDefaultImage() {
        return defaultImage;
    }
}
