package keykey.component;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import keykey.models.KeyDesc;

public class TopSection extends HBox {
    public TopSection(String applicationName) {

        Text applicationTitle = new Text(applicationName);
        applicationTitle.setStyle("-fx-font-size: 25px; -fx-fill: white");

        Button addNewButton = new Button("Add _New");
        addNewButton.setOnAction(this::showKeyCombinationPopUp);

        Region spacer = new Region(); // Spacer takes all the available space in between the add new button and heading
        HBox.setHgrow(spacer, Priority.ALWAYS);
        this.getChildren().addAll(applicationTitle, spacer, addNewButton);
    }

    private void showKeyCombinationPopUp(ActionEvent event) {
        Node source = (Node) event.getSource();
        Scene rootScene =  source.getScene();
        Stage owner = (Stage) rootScene.getWindow();

        GaussianBlur blur = new GaussianBlur();
        blur.setRadius(3.0);
        Parent parent = rootScene.getRoot();
        parent.setEffect(blur);

        Stage popup = new Stage();
        popup.initOwner(owner);
        popup.initStyle(StageStyle.UNDECORATED); // No window buttons
        popup.initModality(Modality.NONE);

        KeyDesc keyDesc = new KeyDesc();

        KeyCombinationPopUp popUpContent = new KeyCombinationPopUp(parent, keyDesc);
        Scene popUpScene = new Scene(popUpContent, 400, 200);
        popup.setScene(popUpScene);
        popup.show();

        owner.getScene().addEventFilter(MouseEvent.MOUSE_PRESSED, evt -> {
            if (popup.isShowing()) {
                popup.close();
                parent.setEffect(null);
            }
        });
    }
}
