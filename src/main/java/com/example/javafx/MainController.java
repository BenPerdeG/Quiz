package com.example.javafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController {

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void showQuizPage() {
        try {
            // Load the quiz page FXML
            FXMLLoader quizLoader = new FXMLLoader(getClass().getResource("quiz.fxml"));
            VBox quizLayout = quizLoader.load();
            Scene quizScene = new Scene(quizLayout, 400, 300);

            // Set the new scene
            primaryStage.setScene(quizScene);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading quiz.fxml");
        }
    }

    @FXML
    private void showBlankPage() {
        try {
            // Load the blank page FXML
            FXMLLoader blankLoader = new FXMLLoader(getClass().getResource("blank.fxml"));
            VBox blankLayout = blankLoader.load();
            Scene blankScene = new Scene(blankLayout, 400, 300);

            // Set the new scene
            primaryStage.setScene(blankScene);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading blank.fxml");
        }
    }
}