package com.example.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        try {

            FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
            VBox menuLayout = menuLoader.load();


            MainController mainController = menuLoader.getController();
            mainController.setPrimaryStage(primaryStage);

            Scene menuScene = new Scene(menuLayout, 400, 300);


            primaryStage.setTitle("Quiz Castellar");
            primaryStage.setScene(menuScene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error menu.fxml");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}