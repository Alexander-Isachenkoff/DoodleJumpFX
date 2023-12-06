package doodle.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class BestScore {

    @XmlAttribute
    private int number;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private int score;

    public BestScore() {
    }

    public BestScore(int number, String name, int score) {
        this.number = number;
        this.name = name;
        this.score = score;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

}
