package keykey.component;

import atlantafx.base.theme.Styles;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import keykey.models.KeyDesc;
import keykey.utils.StorageUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CombDescriptionPopUp extends VBox {
    /**
     *
     * @param keyDesc {@link KeyDesc} class that stores all the information related to the key
     */
    public CombDescriptionPopUp(String application, KeyDesc keyDesc) {
        DescriptionInput descriptionInput = new DescriptionInput();
        PlatformPicker platformPicker = new PlatformPicker();
        ButtonGroup buttonGroup = new ButtonGroup(application, descriptionInput, platformPicker, keyDesc);

        this.getChildren().addAll(descriptionInput, platformPicker, buttonGroup);
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

class DescriptionInput extends VBox {
    private final TextField description;
    public DescriptionInput() {
        Text title = new Text("Description");
        description = new TextField("");
        this.setSpacing(15);
        this.getChildren().addAll(title, description);
    }

    public String getText() {
        return description.getText();
    }
}

class PlatformPicker extends VBox {
    private final CheckBox linux;
    private final CheckBox mac;
    private final CheckBox windows;
    public PlatformPicker() {
        Text title = new Text("Platform");

        VBox pickers = new VBox();
        linux = new CheckBox("Linux");
        mac = new CheckBox("Mac");
        windows = new CheckBox("Windows"); // Because windows sucks!

        pickers.getChildren().addAll(linux, mac, windows);

        this.setSpacing(15);
        this.getChildren().addAll(title, pickers);
    }

    public CheckBox getLinux() {
        return linux;
    }

    public CheckBox getMac() {
        return mac;
    }

    public CheckBox getWindows() {
        return windows;
    }
}

class ButtonGroup extends HBox {
    public ButtonGroup(String application, DescriptionInput desc, PlatformPicker platforms, KeyDesc keyDesc) {
        Button confirm = new Button("Confirm");
        confirm.getStyleClass().addAll(Styles.BUTTON_OUTLINED, Styles.SUCCESS);

        confirm.setOnAction(event -> {
            List<String> platformsSelected = new ArrayList<>();
            CheckBox linux = platforms.getLinux();
            CheckBox mac = platforms.getMac();
            CheckBox windows = platforms.getWindows();

            if (linux.isSelected()) {platformsSelected.add("Linux");}
            if (mac.isSelected()) {platformsSelected.add("Mac");}
            if (windows.isSelected()) {platformsSelected.add("Windows");}

            keyDesc.setDescription(desc.getText());
            keyDesc.setPlatforms(platformsSelected.toArray(new String[0]));

            try {
                StorageUtil storageUtil = new StorageUtil();
                storageUtil.WriteKeyDesc(application, keyDesc);
            } catch (IOException e) {
                System.err.println("Could not write the new key description to " + application + " " + e.getMessage());
            }


            Node source = (Node) event.getSource();
            Scene scene = source.getScene();
            Stage owner = (Stage) scene.getWindow();
            owner.close(); // Closes this popup
        });

        Button cancel = new Button("Cancel");
        cancel.getStyleClass().addAll(Styles.BUTTON_OUTLINED, Styles.DANGER);

        cancel.setOnAction(event -> {
            Node source = (Node) event.getSource();
            Scene scene = source.getScene();
            Stage owner = (Stage) scene.getWindow();

            owner.close();
        });

        Region space = new Region();
        HBox.setHgrow(space, Priority.ALWAYS);
        this.setSpacing(10);
        this.getChildren().addAll(space, cancel, confirm);
    }
}
