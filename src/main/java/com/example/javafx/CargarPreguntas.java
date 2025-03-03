package com.example.javafx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CargarPreguntas {
    public static List<Preguntas> loadQuestions(String fileName) throws IOException {
        String questionsFilePath = fileName;

        if (Files.exists(Paths.get(questionsFilePath))) {
            try (Stream<String> lines = Files.lines(Paths.get(questionsFilePath))) {
                return lines
                        .map(line -> line.split(";"))
                        .filter(parts -> parts.length == 3)
                        .map(parts -> new Preguntas(parts[0], List.of(parts[1].split(",")), parts[2]))
                        .collect(Collectors.toList());
            }
        } else {
            try (InputStream inputStream = CargarPreguntas.class.getResourceAsStream("/" + fileName);
                 Stream<String> lines = new BufferedReader(new InputStreamReader(inputStream)).lines()) {
                if (inputStream == null) {
                    throw new IOException("File not found");
                }
                return lines
                        .map(line -> line.split(";"))
                        .filter(parts -> parts.length == 3)
                        .map(parts -> new Preguntas(parts[0], List.of(parts[1].split(",")), parts[2]))
                        .collect(Collectors.toList());
            }
        }
    }
}