package doodle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.function.Consumer;

public class InputNameController {

    @FXML
    private TextField textField;
    @FXML
    private Button saveButton;

    private Consumer<String> onSave = s -> {
    };

    @FXML
    private void initialize() {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            saveButton.setDisable(textField.getText().trim().isEmpty());
        });
    }

    @FXML
    private void onSave() {
        onSave.accept(textField.getText());
    }

    public void setOnSave(Consumer<String> onSave) {
        this.onSave = onSave;
    }
}
