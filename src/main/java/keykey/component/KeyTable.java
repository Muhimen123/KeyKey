package keykey.component;

import atlantafx.base.theme.Styles;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import keykey.models.KeyDesc;
import keykey.utils.StorageUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public class KeyTable extends TableView<KeyDesc> {
    public KeyTable(String application) throws IOException {
        TableColumn<KeyDesc, String> keyName = new TableColumn<>("Key");
        keyName.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getKeys()));

        TableColumn<KeyDesc, String> keyDesc = new TableColumn<>("Description");
        keyDesc.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getDescription()));

        TableColumn<KeyDesc, String> platforms = new TableColumn<>("Platforms");
        platforms.setCellValueFactory(cellData -> {
            String[] _platforms = cellData.getValue().getPlatforms();
            String _platform_string = Arrays.toString(_platforms);
            if(!_platform_string.isEmpty()) {
                _platform_string = _platform_string.substring(1, _platform_string.length() - 1);
            }
            return new ReadOnlyStringWrapper(_platform_string);
        });

        this.getColumns().add(keyName);
        this.getColumns().add(keyDesc);
        this.getColumns().add(platforms);

        this.setRowFactory(tv->  {
            TableRow<KeyDesc> row = new TableRow<>();
            AtomicReference<KeyDesc> rowData = new AtomicReference<>(row.getItem());
            ContextMenu contextMenu = new ContextMenu();

            MenuItem delete = new MenuItem("Delete");
            delete.setOnAction(event -> {
                try {
                    StorageUtil storageUtil = new StorageUtil();
                    storageUtil.DeleteKeyDesc(application, rowData.get());
                    this.UpdateTable(application);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

//            MenuItem edit = new MenuItem("Edit");
//            edit.setOnAction(event -> {
//                System.out.println("Item edit action");
//            });

//            contextMenu.getItems().add(edit);
            contextMenu.getItems().add(delete);

            row.setOnContextMenuRequested(event -> {
                if(!row.isEmpty()) {
                    rowData.set(row.getItem());
                    contextMenu.show(this, event.getScreenX(), event.getScreenY());
                } else {
                    contextMenu.hide();
                }
            });

            return row;
        });

        StorageUtil storage = new StorageUtil();
        ObservableList<KeyDesc> keysShortcuts = FXCollections.observableArrayList(storage.ReadAllKeyDescs(application));

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
        this.setId("tableView");
    }

    public void UpdateTable(String application) throws IOException {
        StorageUtil storage = new StorageUtil();
        ObservableList<KeyDesc> keysShortcuts = FXCollections.observableArrayList(storage.ReadAllKeyDescs(application));
        this.setItems(keysShortcuts);
    }
}
