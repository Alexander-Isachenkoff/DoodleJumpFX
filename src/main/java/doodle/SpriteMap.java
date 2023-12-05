package doodle;

import javafx.scene.image.Image;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement
public class SpriteMap {

    @XmlAttribute
    private String filePath;
    @XmlElement(name = "bounds")
    private List<Bounds> bounds;

    public static List<Image> load(String filePath) {
        SpriteMap spriteMap = FileUtils.loadXmlObject(filePath, SpriteMap.class);
        return spriteMap.load();
    }

    public String getFilePath() {
        return filePath;
    }

    public List<Bounds> getBounds() {
        return bounds;
    }

    private List<Image> load() {
        return getBounds().stream()
                .map(bounds -> FileUtils.loadImage(getFilePath(), bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight()))
                .collect(Collectors.toList());
    }

}
