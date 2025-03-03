package com.example.javafx;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CargarPreguntas {
    public static List<Preguntas> loadQuestions(String fileName) throws IOException {
        List<Preguntas> questions = new ArrayList<>();

        String questionsFilePath = fileName;

        if (Files.exists(Paths.get(questionsFilePath))) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                    Files.newInputStream(Paths.get(questionsFilePath))))) {
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
                System.out.println("Preguntas Cargadas");
            } catch (IOException e) {
                throw new IOException("Error questions.txt");
            }
        } else {
            try (InputStream inputStream = CargarPreguntas.class.getResourceAsStream("/" + fileName);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                if (inputStream == null) {
                    throw new IOException("File not found");
                }
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
            } catch (IOException e) {
                throw new IOException("Error questions.txt");
            }
        }

        Collections.shuffle(questions);

        return questions;
    }
}