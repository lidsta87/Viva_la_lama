package com.example.Hangman;

import javafx.animation.PauseTransition;
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
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.sound.sampled.*;

public class SceneController {

    private Stage stage;
    private Scene scene;

    public void switchToScene1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/StartScreen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GameScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene3(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/VictoryScrene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
        stage.show();
    }

    public void switchToScene4(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/GameOVerScrene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
        stage.show();
    }

    //Quit Button
    public void quit() {
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


    //read a random word from the file Words
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


    char guess;
    public List<Character> hasGuessed = new ArrayList<>();
    PauseTransition pause = new PauseTransition(Duration.seconds(1));

    int correctCount;
    int previousCorrectCount;

    int IncorrectGuess;
    Image image;


    public void makeAGuess(ActionEvent event) {
        try {
            //read in Text field and clear it
            guess = myTextfield.getText().charAt(0);
            guess = Character.toUpperCase(guess);
            myTextfield.clear();


            if(hasGuessed.contains(guess)) {
                return;
            }

            //add new guess to List and showcase it
            hasGuessed.add(guess);
            myLabel.setText(hasGuessed.toString());

            //refresh for a new Guess
            answerLabel.setText("");
            previousCorrectCount = correctCount;
            correctCount = 0;

            //Check and write what Characters have been guessed up till now
            for (int k = 0; k < word.length(); k++) {
                if (hasGuessed.contains(word.charAt(k))) {
                    answerLabel.setText(answerLabel.getText() + word.charAt(k) + " ");
                    correctCount++;
                } else {
                    answerLabel.setText(answerLabel.getText() + "_ ");
                }
            }

            //Check if any Character was guessed, if not change Hangman Image and play Sound Effects
            if (previousCorrectCount == correctCount) {
                IncorrectGuess++;

                String pathwayImage = "Hangman" + IncorrectGuess + ".png";
                image = new Image(pathwayImage);
                hangmanImage.setImage(image);

                String pathwaySound = "src/main/resources/Sound/Hangman" + IncorrectGuess + ".wav";
                AudioInputStream audiostream = AudioSystem.getAudioInputStream(new File(pathwaySound));
                Clip clip = AudioSystem.getClip();
                clip.open(audiostream);
                clip.start();
            }

            //Check for Win-condition
            if (correctCount == word.length()) {
                pause.setOnFinished(e -> {
                    try {
                        switchToScene3(event);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                pause.play();
            }

            //Check for Loose-Condition
            if (IncorrectGuess == 6) {
                answerLabel.setText("");
                for (int k = 0; k < word.length(); k++) {
                    answerLabel.setText(answerLabel.getText() + word.charAt(k) + " ");
                }
                    pause.setOnFinished(e -> {
                    try {
                        switchToScene4(event);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                pause.play();
            }

            //catch IO Exception
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}