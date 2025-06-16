package keykey.component;

import atlantafx.base.theme.Styles;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import keykey.utils.StorageUtil;

import java.io.IOException;

public class NewApplicationPopUp extends VBox {
    public NewApplicationPopUp(Parent popupParent) {
        NameInput nameInput = new NameInput();
        InputButtonGroup inputButtonGroup = new InputButtonGroup(popupParent, nameInput);

        this.getChildren().addAll(nameInput, inputButtonGroup);
        this.setSpacing(25);
        this.setPadding(new Insets(15));

        BorderStroke borderStroke = new BorderStroke(
                Color.web("788192FF"),
                BorderStrokeStyle.SOLID,
                new CornerRadii(0),
                new BorderWidths(1)
        );
        this.setBorder(new Border(borderStroke));
    }
}

class NameInput extends VBox {
    TextField applicationNameField;
    public NameInput() {
        Text applicationName = new Text("Application Name");
        applicationNameField = new TextField();

        this.getChildren().addAll(applicationName, applicationNameField);
        this.setSpacing(15);
    }

    public TextField getTextField() {
        return this.applicationNameField;
    }
}

class InputButtonGroup extends HBox {
    public InputButtonGroup(Parent popupParent, NameInput nameInput) {
        Button confirm = new Button("Confirm");
        confirm.getStyleClass().addAll(Styles.BUTTON_OUTLINED, Styles.SUCCESS);

        confirm.setOnMouseClicked(event -> {
            System.out.println("Confirm clicked");
            String appName = nameInput.getTextField().getText();
            StorageUtil storageUtil = null;
            try {
                System.out.println("No error");
                storageUtil = new StorageUtil();
                storageUtil.AddApplication(appName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            closePopup(event, popupParent);
        });

        Button cancel = new Button("Cancel");
        cancel.getStyleClass().addAll(Styles.BUTTON_OUTLINED, Styles.DANGER);

        cancel.setOnMouseClicked(event -> {
            closePopup(event, popupParent);
        });

        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);

        this.getChildren().addAll(region, cancel, confirm);
        this.setSpacing(15);
    }

    void closePopup(MouseEvent event, Parent popupParent) {
        Node source = (Node) event.getSource();
        Scene scene = source.getScene();
        Stage owner = (Stage) scene.getWindow();

        popupParent.setEffect(null);
        owner.close();
    }
}
