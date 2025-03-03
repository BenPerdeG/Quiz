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

            FXMLLoader quizLoader = new FXMLLoader(getClass().getResource("quiz.fxml"));
            VBox quizLayout = quizLoader.load();
            Scene quizScene = new Scene(quizLayout, 400, 300);

            primaryStage.setScene(quizScene);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error quiz.fxml");
        }
    }

    @FXML
    private void showEditPage() {
        try {

            FXMLLoader editLoader = new FXMLLoader(getClass().getResource("questions.fxml"));
            VBox editLayout = editLoader.load();


            PreguntasController editController = editLoader.getController();
            editController.setPrimaryStage(primaryStage);

            Scene editScene = new Scene(editLayout, 400, 300);

            primaryStage.setScene(editScene);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error questions.fxml");
        }
    }
}