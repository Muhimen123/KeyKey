package keykey.component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class KeyCombinationPopUp extends VBox {
    public KeyCombinationPopUp(Parent root) {
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

        this.setAlignment(Pos.CENTER_LEFT);
        this.setSpacing(30);

        Button continueButton = getButton(root, instruction);

        Button resetButton = new Button("Reset");
        resetButton.setOnAction(event -> {
            keyRecord.setLength(0);
            instruction.setText("");
        });

        Region space = new Region();
        space.setFocusTraversable(true); // This avoids autofocus on any button
        HBox.setHgrow(space, Priority.ALWAYS);

        // Adding the button inside a HBox along with a spacer to push the button on the right side
        HBox buttonHbox = new HBox(space, resetButton, continueButton);
        buttonHbox.setSpacing(10);

        Region dummy = new Region();
        dummy.setFocusTraversable(true); // This avoids autofocus on the text field so that the prompt text is visible
        dummy.setMaxHeight(0);
        dummy.setMaxWidth(0);
        this.getChildren().addAll(dummy, title, instruction, buttonHbox);
        this.setPadding(new Insets(15));

        BorderStroke borderStroke = new BorderStroke(
                Color.web("788192FF"),
                BorderStrokeStyle.SOLID,
                new CornerRadii(0),
                new BorderWidths(1)
        );
        this.setBorder(new Border(borderStroke));
    }

    private static Button getButton(Parent root, TextField instruction) {
        Button continueButton = new Button("Continue");
        continueButton.setOnAction(event -> {
            System.out.println("User Confirmed the key combination. Final key combination is:");
            System.out.println(instruction.getText());

            Node source = (Node) event.getSource();
            Scene scene = source.getScene();
            Stage owner = (Stage) scene.getWindow();
            Parent parent = scene.getRoot();

            root.setEffect(null);
            owner.close();
        });
        return continueButton;
    }
}
