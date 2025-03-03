package com.example.javafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class QuizController {
    @FXML
    private Label preguntasLabel;
    @FXML
    private VBox opciones;
    @FXML
    private Button next;
    @FXML
    private Button backToMenu;

    private List<Preguntas> preguntas;
    private int preguntactual = 0;
    private int puntuacion = 0;
    private ToggleGroup grupo;

    public void initialize() {
        grupo = new ToggleGroup();
        backToMenu.setVisible(false); // Hide the "Back to Menu" button initially
        try {
            preguntas = CargarPreguntas.loadQuestions("questions.txt");
            showQuestion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showQuestion() {
        if (preguntactual < preguntas.size()) {
            Preguntas q = preguntas.get(preguntactual);
            preguntasLabel.setText(q.getQuestionText());
            opciones.getChildren().clear();

            for (String option : q.getOptions()) {
                RadioButton rb = new RadioButton(option);
                rb.setToggleGroup(grupo);
                opciones.getChildren().add(rb);
            }
        } else {
            showResult();
        }
    }

    @FXML
    private void checkAnswer() {
        RadioButton selected = (RadioButton) grupo.getSelectedToggle();
        if (selected != null) {
            String answer = selected.getText();
            if (preguntas.get(preguntactual).isCorrect(answer)) {
                puntuacion++;
            }
            preguntactual++;
            showQuestion();
        }
    }

    private void showResult() {
        preguntasLabel.setText("Enhorabuena! Puntuación: " + puntuacion + "/" + preguntas.size());
        opciones.getChildren().clear();
        next.setDisable(true);
        backToMenu.setVisible(true); // Show the "Back to Menu" button
    }

    @FXML
    private void goBackToMenu() {
        try {
            // Load the menu page FXML
            FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
            VBox menuLayout = menuLoader.load();

            // Get the controller and pass the primaryStage to it
            MainController mainController = menuLoader.getController();
            mainController.setPrimaryStage((Stage) backToMenu.getScene().getWindow());

            Scene menuScene = new Scene(menuLayout, 400, 300);

            // Set the menu scene
            Stage stage = (Stage) backToMenu.getScene().getWindow();
            stage.setScene(menuScene);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading menu.fxml");
        }
    }
}