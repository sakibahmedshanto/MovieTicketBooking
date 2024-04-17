module org.example.cinehub {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.cinehub to javafx.fxml;
    exports org.example.cinehub;
}