package doodle;

import doodle.model.BestScore;
import doodle.model.BestScores;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class BestScoresController {

    @FXML
    private TableView<BestScore> table;
    @FXML
    private TableColumn<BestScore, Integer> numberColumn;
    @FXML
    private TableColumn<BestScore, String> nameColumn;
    @FXML
    private TableColumn<BestScore, Integer> scoreColumn;

    @FXML
    private void initialize() {
        List<BestScore> data = BestScores.load();

        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

        table.getItems().setAll(data);
    }

    @FXML
    private void onMenu() {
        Main.toMainMenu();
    }

}
