package com.example.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage; // Reference to the primary stage

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage; // Store the primary stage
        try {
            // Load the menu page FXML
            FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
            VBox menuLayout = menuLoader.load();

            // Get the controller and pass the primaryStage to it
            MainController mainController = menuLoader.getController();
            mainController.setPrimaryStage(primaryStage);

            Scene menuScene = new Scene(menuLayout, 400, 300);

            // Set the menu scene and show the stage
            primaryStage.setTitle("JavaFX Navigation App");
            primaryStage.setScene(menuScene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading menu.fxml");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}