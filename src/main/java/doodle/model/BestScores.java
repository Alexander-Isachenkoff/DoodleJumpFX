package doodle.model;

import doodle.FileUtils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@XmlRootElement
public class BestScores {

    private static final String BEST_SCORES_FILE = "data/best_scores.xml";
    @XmlElement(name = "bestScore")
    private List<BestScore> bestScores = new ArrayList<>();

    public BestScores() {
    }

    public BestScores(List<BestScore> bestScores) {
        this.bestScores = bestScores;
    }

    public static List<BestScore> load() {
        if (!new File(BEST_SCORES_FILE).exists()) {
            new BestScores().save();
        }
        List<BestScore> bestScores = FileUtils.loadXmlObject(BEST_SCORES_FILE, BestScores.class).bestScores;
        bestScores.sort(Comparator.comparingInt(bestScore -> -bestScore.getScore()));
        bestScores.forEach(bestScore -> bestScore.setNumber(bestScores.indexOf(bestScore) + 1));
        return bestScores;
    }

    public void save() {
        FileUtils.saveXmlObject(this, BEST_SCORES_FILE);
    }

}
