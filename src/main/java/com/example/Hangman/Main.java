package com.example.Hangman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setResizable(false);
        stage.setTitle("Viva_La_Lama");
        stage.setHeight(800);
        stage.setWidth(1200);
        Image icon = new Image(getClass().getResourceAsStream("/Images/Logo.png"));
        stage.getIcons().add(icon);
        Parent root = FXMLLoader.load(getClass().getResource("/StartScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}