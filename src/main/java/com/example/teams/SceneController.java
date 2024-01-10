package com.example.teams;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
    private String word = "intovert";

    int correctCount;
    int previousCorrectCount;
    int noCorrectGuesses;








    public void submit(ActionEvent event) {
        try {
            guess = myTextfield.getText().charAt(0);
            myTextfield.clear();
            //read in Textfield and clear it

            hasGuest.add(guess);
            myLabel.setText(hasGuest.toString());
            //add new guess to List and showcase it

            answerLabel.setText("");
            previousCorrectCount = correctCount;
            correctCount = 0;
            //refresh for a new Guess


            for (int k = 0; k < word.length(); k++) {
                if (hasGuest.contains(word.charAt(k))) {
                    answerLabel.setText(answerLabel.getText() + word.charAt(k) + " ");
                    correctCount++;
                } else {
                    answerLabel.setText(answerLabel.getText() + "_ ");

                }
            }
            if (previousCorrectCount == correctCount) {
                noCorrectGuesses++;
            }


            if (correctCount == word.length()) {
                switchToScene3(event);

            }
            if (noCorrectGuesses >= 6) {
                switchToScene4(event);
            }


        } catch (Exception e) {
            System.out.println(e);
            //catch IO Exception
        }

    }
}