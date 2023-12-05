package doodle;

import javax.xml.bind.annotation.XmlAttribute;

public class Bounds {

    @XmlAttribute
    private int x;
    @XmlAttribute
    private int y;
    @XmlAttribute
    private int width;
    @XmlAttribute
    private int height;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
