module keykey.keykey {
    requires javafx.controls;
    requires javafx.fxml;
    requires atlantafx.base;
    requires com.google.gson;

    opens keykey to javafx.fxml;
    opens keykey.models to com.google.gson;
    exports keykey;
}