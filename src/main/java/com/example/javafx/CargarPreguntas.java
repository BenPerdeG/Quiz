package com.example.javafx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CargarPreguntas {
    public static List<Preguntas> loadQuestions(String fileName) throws IOException {
        List<Preguntas> questions = new ArrayList<>();

        InputStream inputStream = CargarPreguntas.class.getResourceAsStream("/" + fileName);
        if (inputStream == null) {
            throw new IOException("File not found: " + fileName);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    String questionText = parts[0];
                    List<String> options = Arrays.asList(parts[1].split(","));
                    String correctAnswer = parts[2];
                    questions.add(new Preguntas(questionText, options, correctAnswer));
                }
            }
        }

        // Shuffle the questions to randomize their order
        Collections.shuffle(questions);

        return questions;
    }
}