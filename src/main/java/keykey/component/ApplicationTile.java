package keykey.component;

import atlantafx.base.controls.Tile;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import keykey.models.KeyDesc;

/**
 * Application tile is for showing individual application.
 */

public class ApplicationTile extends Tile {
    public ApplicationTile(String title) {
        this.setTitle(title);
        this.setPrefWidth(200);
        this.getStyleClass().add("application-tile");

        this.setOnMouseClicked(event -> {
            System.out.println("Mouse clicked");
            BorderPane borderPane = (BorderPane) this.getScene().getRoot().lookup("#keybinding");

            TableView<KeyDesc> tableView = new KeyTable();
            borderPane.setCenter(tableView);

            this.handleColorChange(event);
        });
    }

    private void handleColorChange(MouseEvent event) {
        VBox vbox = (VBox) this.getScene().getRoot().lookup("#applications");

        for(Node node : vbox.getChildren()) {
            if(node instanceof ApplicationTile) {
                ((ApplicationTile) node).unselectColor();
            }
        }

        this.selectColor();
    }

    public void selectColor() {
        getStyleClass().remove("unselected");
        if (!getStyleClass().contains("selected")) {
            getStyleClass().add("selected");
        }
    }

    public void unselectColor() {
        getStyleClass().remove("selected");
        if (!getStyleClass().contains("unselected")) {
            getStyleClass().add("unselected");
        }
    }
}
