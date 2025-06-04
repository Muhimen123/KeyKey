package keykey.component;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Sidebar extends ScrollPane {

    public Sidebar(ArrayList<String> applications) {
        VBox box = new VBox();
        box.setId("applications");

        for(String application : applications) {
            ApplicationTile applicationTile = new ApplicationTile(application);
            box.getChildren().add(applicationTile);
        }

        this.setStyle("-fx-background: -color-dark; -fx-background-color: -color-dark;");
        this.setContent(box);
    }

}


