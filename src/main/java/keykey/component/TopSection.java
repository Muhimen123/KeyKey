package keykey.component;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

public class TopSection extends HBox {
    public TopSection(String applicationName) {

        Text applicationTitle = new Text(applicationName);
        applicationTitle.setStyle("-fx-font-size: 25px; -fx-fill: white");

        Button addNewButton = new Button("Add _New");
        addNewButton.setOnAction(e -> {
            System.out.println("Add new button clicked");
        });

        Region spacer = new Region(); // Spacer takes all the available space in between the add new button and heading
        HBox.setHgrow(spacer, Priority.ALWAYS);
        this.getChildren().addAll(applicationTitle, spacer, addNewButton);
    }
}
