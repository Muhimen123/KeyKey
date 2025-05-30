package keykey;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import keykey.component.Sidebar;

public class HomeController {
    public BorderPane borderpane;

    @FXML
    public void initialize() {
        borderpane.setId("keybinding");

        Text title = new Text("Hello There Goodman!");
        borderpane.setCenter(title);

        Sidebar sidebar = new Sidebar();
        borderpane.setLeft(sidebar);
    }
}
