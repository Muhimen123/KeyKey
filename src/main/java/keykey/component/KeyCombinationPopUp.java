package keykey.component;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import keykey.models.KeyDesc;

public class KeyCombinationPopUp extends VBox {
    public KeyCombinationPopUp(String application, Parent root, KeyDesc keyDesc) {
        Text title = new Text("Record Key Combination");
        TextField instruction = new TextField();
        instruction.setPromptText("Click & start typing one key after another to record");
        instruction.setEditable(false);

        StringBuilder keyRecord = new StringBuilder();
        instruction.setOnKeyPressed(event -> {
            instruction.setText("");
            if(!keyRecord.isEmpty()) {
                keyRecord.append(" + ");
            }
            keyRecord.append(event.getCode().toString());
            instruction.setText(keyRecord.toString());
        });

        this.setSpacing(25);

        Button continueButton = getButton(application, root, instruction, keyDesc);

        Button resetButton = new Button("Reset");
        resetButton.setOnAction(event -> {
            keyRecord.setLength(0);
            instruction.setText("");
        });

        Region space = new Region();
        HBox.setHgrow(space, Priority.ALWAYS);

        // Adding the button inside a HBox along with a spacer to push the button on the right side
        HBox buttonHbox = new HBox(space, resetButton, continueButton);
        buttonHbox.setSpacing(10);

        this.getChildren().addAll(title, instruction, buttonHbox);
        Platform.runLater(title::requestFocus);
        this.setPadding(new Insets(15));

        BorderStroke borderStroke = new BorderStroke(
                Color.web("788192FF"),
                BorderStrokeStyle.SOLID,
                new CornerRadii(0),
                new BorderWidths(1)
        );
        this.setBorder(new Border(borderStroke));
    }

    private static Button getButton(String application, Parent root, TextField instruction, KeyDesc keyDesc) {
        Button continueButton = new Button("Continue");
        continueButton.setOnAction(event -> {
            Node source = (Node) event.getSource();
            Scene scene = source.getScene();
            Stage owner = (Stage) scene.getWindow();

            keyDesc.setKeys(instruction.getText());

            Stage popup = new Stage();
            popup.initOwner(owner);
            popup.initStyle(StageStyle.UNDECORATED);
            popup.initModality(Modality.NONE);

            CombDescriptionPopUp combDescriptionPopUp = new CombDescriptionPopUp(application, keyDesc);
            Scene popUpScene = new Scene(combDescriptionPopUp, 400, 300);
            popup.setScene(popUpScene);
            popup.showAndWait();

            root.setEffect(null);
            owner.close(); // Closes this pop up
        });
        return continueButton;
    }
}
