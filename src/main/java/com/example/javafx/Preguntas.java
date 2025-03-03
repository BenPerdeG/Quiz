package com.example.javafx;

import java.util.List;

public class Preguntas {
    private String questionText;
    private List<String> options;
    private String correctAnswer;

    public Preguntas(String questionText, List<String> options, String correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() { return questionText; }
    public List<String> getOptions() { return options; }
    public String getCorrectAnswer() { return correctAnswer; }

    public boolean isCorrect(String answer) {
        return correctAnswer.equals(answer);
    }
}

