package com.example.Hangman;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    @FXML
    private ImageView hangmanImage;


    char guess;
    public List<Character> hasGuessed = new ArrayList<>();


    Path filePath = Paths.get("src/main/resources", "Words");
    List<String> words;

    {
        try {
            words = Files.readAllLines(filePath, StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    Random random = new Random();


    private String word = words.get(random.nextInt(words.size()));



    int correctCount;
    int previousCorrectCount;
    int IncorrectGuess;
    Image image;


    public void submit(ActionEvent event) {
        try {
            guess = myTextfield.getText().charAt(0);
            myTextfield.clear();
            //read in Textfield and clear it

            hasGuessed.add(guess);
            myLabel.setText(hasGuessed.toString());
            //add new guess to List and showcase it

            answerLabel.setText("");
            previousCorrectCount = correctCount;
            correctCount = 0;
            //refresh for a new Guess


            for (int k = 0; k < word.length(); k++) {
                if (hasGuessed.contains(word.charAt(k))) {
                    answerLabel.setText(answerLabel.getText() + word.charAt(k) + " ");
                    correctCount++;
                } else {
                    answerLabel.setText(answerLabel.getText() + "_ ");
                }
            }

            if (previousCorrectCount == correctCount) {
                IncorrectGuess++;
                String pathway = "Hangman" + IncorrectGuess + ".png";
                image = new Image(pathway);

            }


            if (correctCount == word.length()) {
                switchToScene3(event);

            }
            if (IncorrectGuess >= 6) {
                switchToScene4(event);
            }

            hangmanImage.setImage(image);


        } catch (Exception e) {
            System.out.println(e);
            //catch IO Exception
        }

    }
}