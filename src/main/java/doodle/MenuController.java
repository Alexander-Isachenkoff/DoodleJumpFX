package doodle;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class MenuController {

    @FXML
    private VBox root;

    @FXML
    private void onPlay() {
        Main.play();
    }

    @FXML
    private void onBestScores() {
        Main.toBestScores();
    }

    @FXML
    private void onExit() {
        root.getScene().getWindow().hide();
    }

}
