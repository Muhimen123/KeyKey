module keykey.keykey {
    requires javafx.fxml;
    requires atlantafx.base;
    requires com.google.gson;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.material2;

    opens keykey to javafx.fxml;
    opens keykey.models to com.google.gson;
    exports keykey;
}