package com.example.javafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class PreguntasController {

    @FXML
    private TextArea questionsTextArea;

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    public void initialize() {
        loadQuestions();
    }

    private void loadQuestions() {

        String questionsFilePath = "questions.txt";

        if (Files.exists(Paths.get(questionsFilePath))) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                    Files.newInputStream(Paths.get(questionsFilePath))))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                questionsTextArea.setText(content.toString());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error questions.txt");
            }
        } else {

            try (InputStream inputStream = getClass().getResourceAsStream("/questions.txt");
                 Scanner scanner = new Scanner(inputStream)) {
                StringBuilder content = new StringBuilder();
                while (scanner.hasNextLine()) {
                    content.append(scanner.nextLine()).append("\n");
                }
                questionsTextArea.setText(content.toString());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error questions.txt");
            }
        }
    }

    @FXML
    private void saveChanges() {
        String questionsFilePath = "questions.txt";
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                Files.newOutputStream(Paths.get(questionsFilePath))))) {
            writer.write(questionsTextArea.getText());
            System.out.println("Cambios Guardados!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error guardando cambios");
        }
    }

    @FXML
    private void goBackToMenu() {
        try {

            FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
            VBox menuLayout = menuLoader.load();


            MainController mainController = menuLoader.getController();
            mainController.setPrimaryStage((Stage) questionsTextArea.getScene().getWindow());

            Scene menuScene = new Scene(menuLayout, 400, 300);


            Stage stage = (Stage) questionsTextArea.getScene().getWindow();
            stage.setScene(menuScene);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error menu.fxml");
        }
    }
}