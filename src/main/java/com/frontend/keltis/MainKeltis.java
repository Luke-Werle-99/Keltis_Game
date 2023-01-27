package com.frontend.keltis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainKeltis extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Image icon = new Image("file:src/Titel.png");
            stage.getIcons().add(icon);
            FXMLLoader fxmlLoader = new FXMLLoader(MainKeltis.class.getResource("StartMenu.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 400);
            //stage.setFullScreen(true);
            stage.setTitle("Keltis");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        launch();
    }
}