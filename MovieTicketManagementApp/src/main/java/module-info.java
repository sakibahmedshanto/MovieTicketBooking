module com.example.movieticketmanagementapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.movieticketmanagementapp to javafx.fxml;
    exports com.example.movieticketmanagementapp;
}