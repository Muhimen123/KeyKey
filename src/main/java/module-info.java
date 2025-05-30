module keykey.keykey {
    requires javafx.controls;
    requires javafx.fxml;
    requires atlantafx.base;

    opens keykey to javafx.fxml;
    exports keykey;
}