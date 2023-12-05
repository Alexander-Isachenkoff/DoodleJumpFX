package doodle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Main.stage = stage;
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/game.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Doodle Jump");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

}
