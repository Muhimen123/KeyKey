package keykey.component;

import atlantafx.base.theme.Styles;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import keykey.utils.StorageUtil;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.material2.Material2MZ;

import java.io.IOException;
import java.util.ArrayList;

public class Sidebar extends VBox {
    ScrollPane scrollpane;
    public Sidebar(ArrayList<String> applications) {
        scrollpane = new ScrollPane();
        VBox box = new VBox();
        box.setId("applications");

        for(String application : applications) {
            ApplicationTile applicationTile = new ApplicationTile(application);
            box.getChildren().add(applicationTile);
        }

        Button addApplication = new AddApplicationButton();

        Region region = new Region();
        VBox.setVgrow(region, Priority.ALWAYS);

        scrollpane.setContent(box);
        this.getChildren().addAll(scrollpane, region, addApplication);
        this.setStyle("-fx-background: -color-dark; -fx-background-color: -color-dark;");
        this.setId("sidebar");
    }

    public void reloadSidebar() throws IOException {
        StorageUtil storageUtil = new StorageUtil();
        ArrayList<String> applications = storageUtil.ReadAllApplications();
        VBox box = new VBox();

        for(String application : applications) {
            ApplicationTile applicationTile = new ApplicationTile(application);
            box.getChildren().add(applicationTile);
        }

        scrollpane.setContent(box);
    }
}

class AddApplicationButton extends Button {
    public AddApplicationButton() {
        super("Add Application", new FontIcon(Material2MZ.PLUS));
        this.setPrefWidth(200);
        this.getStyleClass().addAll(Styles.BUTTON_OUTLINED, Styles.SUCCESS);
        this.setOnMouseClicked(event -> {
            Node source = (Node) event.getSource();
            Scene scene = source.getScene();
            Stage owner = (Stage) scene.getWindow();

            GaussianBlur blur = new GaussianBlur();
            blur.setRadius(3.0);
            Parent parent = scene.getRoot();
            parent.setEffect(blur);

            Stage popup = new Stage();
            popup.initOwner(owner);
            popup.initStyle(StageStyle.UNDECORATED);
            popup.initModality(Modality.NONE);

            NewApplicationPopUp newApplicationPopUp = new NewApplicationPopUp(parent);
            Scene popupScene = new Scene(newApplicationPopUp, 300, 150);
            popup.setScene(popupScene);
            popup.show();

            owner.getScene().addEventFilter(MouseEvent.MOUSE_PRESSED, _ -> {
                if (popup.isShowing()) {
                    popup.close();
                    parent.setEffect(null);
                }
            });

        });
    }
}