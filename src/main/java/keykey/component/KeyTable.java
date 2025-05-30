package keykey.component;

import atlantafx.base.theme.Styles;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import keykey.models.KeyDesc;

import java.util.Arrays;

public class KeyTable extends TableView<KeyDesc> {
    public KeyTable() {
        TableColumn<KeyDesc, String> keyName = new TableColumn<>("Key");
        keyName.setCellValueFactory(cellData -> {
            int[] keys = cellData.getValue().getKeys();
            return new ReadOnlyStringWrapper(Arrays.toString(keys));
        });

        TableColumn<KeyDesc, String> keyDesc = new TableColumn<>("Description");
        keyDesc.setCellValueFactory(cellData -> {
            return new ReadOnlyStringWrapper(cellData.getValue().getDescription());
        });

        TableColumn<KeyDesc, String> platforms = new TableColumn<>("Platforms");
        platforms.setCellValueFactory(cellData -> {
            String[] _platforms = cellData.getValue().getPlatforms();
            return new ReadOnlyStringWrapper(Arrays.toString(_platforms));
        });

        this.getColumns().add(keyName);
        this.getColumns().add(keyDesc);
        this.getColumns().add(platforms);

        ObservableList<KeyDesc> keysShortcuts = FXCollections.observableArrayList(
            new KeyDesc(new int[]{1, 2}, "Just a simple description", new String[]{"Mac", "Linux"}),
            new KeyDesc(new int[]{1, 2}, "Just a simple description", new String[]{"Mac", "Linux"}),
            new KeyDesc(new int[]{1, 2}, "Just a simple description", new String[]{"Mac", "Linux"}),
            new KeyDesc(new int[]{1, 2}, "Just a simple description", new String[]{"Mac", "Linux"}),
            new KeyDesc(new int[]{1, 2}, "Just a simple description", new String[]{"Mac", "Linux"}),
            new KeyDesc(new int[]{1, 2}, "Just a simple description", new String[]{"Mac", "Linux"}),
            new KeyDesc(new int[]{1, 2}, "Just a simple description", new String[]{"Mac", "Linux"}),
            new KeyDesc(new int[]{1, 2}, "Just a simple description", new String[]{"Mac", "Linux"})
        );

        this.setItems(keysShortcuts);

        this.getStyleClass().add(Styles.BORDERED);
        this.getStyleClass().add(Styles.STRIPED);

        BorderStroke borderStroke = new BorderStroke(
                Color.web("788192FF"),
                BorderStrokeStyle.SOLID,
                new CornerRadii(0),
                new BorderWidths(0.5)
        );

        this.setBorder(new Border(borderStroke));
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS); // Get rid of the extra column
    }
}
