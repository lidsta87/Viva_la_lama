module com.example.teams {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.teams to javafx.fxml;
    exports com.example.teams;
}