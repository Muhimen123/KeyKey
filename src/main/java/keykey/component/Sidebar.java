package keykey.component;

import atlantafx.base.controls.Tile;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class Sidebar extends ScrollPane {

    public Sidebar() {
        VBox box = new VBox();
        box.setId("applications");

        ApplicationTile tile1 = new ApplicationTile("Obsidian");
        ApplicationTile tile2 = new ApplicationTile("Glass");
        ApplicationTile tile3 = new ApplicationTile("Jetbrains");

        box.getChildren().addAll(tile1, tile2, tile3);

        this.setStyle("-fx-background: -color-dark; -fx-background-color: -color-dark;");
        this.setContent(box);
    }

}


