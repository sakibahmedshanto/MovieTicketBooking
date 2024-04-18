module org.example.cinehub {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.cinehub to javafx.fxml;
    exports org.example.cinehub;
}