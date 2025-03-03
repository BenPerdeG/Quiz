package com.example.javafx;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage; // Reference to the primary stage

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage; // Store the primary stage
        try {
            // Load the menu page FXML
            FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
            VBox menuLayout = menuLoader.load(); // No need to set the controller here
            Scene menuScene = new Scene(menuLayout, 400, 300);

            // Set the menu scene and show the stage
            primaryStage.setTitle("JavaFX Navigation App");
            primaryStage.setScene(menuScene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading menu.fxml");
        }
    }

    // Method to show the Quiz Page
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

    // Method to show a Blank Page
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

    public static void main(String[] args) {
        launch(args);
    }
}