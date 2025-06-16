package keykey.component;

import atlantafx.base.controls.Tile;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import keykey.models.KeyDesc;


/**
 * Application tile is for showing individual application.
 */

public class ApplicationTile extends Tile {
    public ApplicationTile(String title) {
        this.setTitle(title.toUpperCase());
        this.setPrefWidth(200);
        this.getStyleClass().add("application-tile");

        this.setOnMouseClicked(event -> {
            BorderPane borderPane = (BorderPane) this.getScene().getRoot().lookup("#keybinding");

            TableView<KeyDesc> tableView = null;
            try {
                tableView = new KeyTable(title);
            } catch (Exception e) {
                System.err.println("Failed to create table for the application: " + title + " || Error:" + e.getMessage());
            }

            TopSection topSection = new TopSection(title);

            VBox content = new VBox();
            content.getChildren().add(topSection);
            content.getChildren().add(tableView);
            VBox.setVgrow(tableView, Priority.ALWAYS);

            content.setPadding(new Insets(20));
            content.setSpacing(20);

            borderPane.setCenter(content);

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
