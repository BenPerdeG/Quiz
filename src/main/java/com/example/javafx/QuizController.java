package com.example.javafx;

import javafx.fxml.FXML;
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

    private List<Preguntas> preguntas;
    private int preguntactual = 0;
    private int puntuacion = 0;
    private ToggleGroup grupo;

    public void initialize() {
        grupo = new ToggleGroup();
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
    }
}