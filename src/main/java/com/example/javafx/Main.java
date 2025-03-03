package com.example.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

public class Main extends Application {
    private List<Preguntas> preguntas;
    private int preguntactual = 0;
    private int puntuacion = 0;
    private Label preguntasLabel;
    private ToggleGroup grupo;
    private VBox opciones;
    private Button next;

    @Override
    public void start(Stage primaryStage) {
        // Load questions from file
        try {
            preguntas = CargarPreguntas.loadQuestions("questions.txt");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error con las preguntas!");
            return;
        }

        // Initialize UI components
        preguntasLabel = new Label();
        grupo = new ToggleGroup();
        opciones = new VBox(10);
        next = new Button("Next");

        next.setOnAction(e -> checkAnswer());

        VBox layout = new VBox(20, preguntasLabel, opciones, next);
        layout.setStyle("-fx-padding: 20;");
        primaryStage.setScene(new Scene(layout, 400, 300));

        showQuestion();
        primaryStage.setTitle("JavaFX Quiz App");
        primaryStage.show();
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
    }

    public static void main(String[] args) {
        launch(args);
    }
}
