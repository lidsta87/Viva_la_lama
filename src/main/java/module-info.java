module com.example.teams {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.Hangman to javafx.fxml;
    exports com.example.Hangman;
}