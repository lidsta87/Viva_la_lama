package com.example.teams;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SceneController {

    private Stage stage;
    private Scene scene;


    public void switchToScene1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Scene1.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToScene2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Scene2.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    public void switchToScene3(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/Scene3.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
        stage.show();
    }
    public void switchToScene4(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/Scene4.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
        stage.show();
    }


    public void quit(ActionEvent event) throws IOException {
        System.exit(0);

    }

    @FXML
    private Label myLabel;

    @FXML
    private Label answerLabel;

    @FXML
    private TextField myTextfield;



    char guess;
    public  List <Character> hasGuest = new ArrayList<>();


    public void submit(ActionEvent event) {
        try {
            guess = myTextfield.getText().charAt(0);
            myTextfield.clear();
            //read in Texfield and clear it

            hasGuest.add(guess);
            myLabel.setText(hasGuest.toString());
            //add new guess to List and showcase it






            if (guess == 'c') {
                switchToScene3(event);

            }
            if(guess == 'x'){
                switchToScene4(event);
            }


        } catch (Exception e) {
            System.out.println(e);
            //catch IO Exception
        }

    }

    private String word = "Hello";

    public String getWord() {
        return word;
    }

}