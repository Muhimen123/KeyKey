package keykey.component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class KeyCombinationPopUp extends VBox {
    public KeyCombinationPopUp(Parent root) {
        Text title = new Text("Record Key Combination");
        TextField instruction = new TextField();
        instruction.setPromptText("Click & start typing to record");

        this.setAlignment(Pos.CENTER_LEFT);
        this.setSpacing(30);

        Button continueButton = new Button("Continue");
        continueButton.setOnAction(event -> {
            System.out.println("User Confirmed the key combination");

            Node source = (Node) event.getSource();
            Scene scene = source.getScene();
            Stage owner = (Stage) scene.getWindow();
            Parent parent = scene.getRoot();

            root.setEffect(null);
            owner.close();
        });

        Region space = new Region();
        HBox.setHgrow(space, Priority.ALWAYS);

        // Adding the button inside a HBox along with a spacer to push the button on the right side
        HBox buttonHbox = new HBox(space, continueButton);

        this.getChildren().addAll(title, instruction, buttonHbox);
        this.setPadding(new Insets(20));

        BorderStroke borderStroke = new BorderStroke(
                Color.web("788192FF"),
                BorderStrokeStyle.SOLID,
                new CornerRadii(0),
                new BorderWidths(1)
        );
        this.setBorder(new Border(borderStroke));
    }
}
