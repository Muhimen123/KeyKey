package keykey.component;

import atlantafx.base.controls.Tile;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import keykey.models.KeyDesc;
import keykey.utils.StorageUtil;

import java.io.IOException;


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

        this.setOnContextMenuRequested(event -> {
            ContextMenu contextMenu = new ContextMenu();
            MenuItem deleteItem = new MenuItem("Delete");
            deleteItem.setOnAction(e -> {
                try {
                    StorageUtil storageUtil = new StorageUtil();
                    storageUtil.DeleteApplication(title);

                    BorderPane borderPane = (BorderPane) this.getScene().getRoot().lookup("#keybinding");
                    borderPane.setCenter(new Text("Application Deleted"));

                    Sidebar sidebar = (Sidebar) this.getScene().getRoot().lookup("#sidebar");
                    sidebar.reloadSidebar();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });


            contextMenu.getItems().add(deleteItem);
            contextMenu.show(this.getScene().getWindow(), event.getScreenX(), event.getScreenY());
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
