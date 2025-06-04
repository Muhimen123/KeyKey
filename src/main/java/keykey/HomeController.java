package keykey;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import keykey.component.Sidebar;
import keykey.utils.StorageUtil;

import java.io.IOException;

public class HomeController {
    public BorderPane borderpane;

    @FXML
    public void initialize() throws IOException {
        borderpane.setId("keybinding");

        Text title = new Text("Hello There Goodman!");
        borderpane.setCenter(title);

        StorageUtil storageUtil = new StorageUtil();
        Sidebar sidebar = new Sidebar(storageUtil.ReadAllApplications());
        borderpane.setLeft(sidebar);
    }
}
