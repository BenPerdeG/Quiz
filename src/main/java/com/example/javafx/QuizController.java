package com.example.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class QuizController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}