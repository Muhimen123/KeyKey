package keykey;

import atlantafx.base.theme.NordDark;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Application.setUserAgentStylesheet(new NordDark().getUserAgentStylesheet());
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.setTitle("Key Key - What was the key again?");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}