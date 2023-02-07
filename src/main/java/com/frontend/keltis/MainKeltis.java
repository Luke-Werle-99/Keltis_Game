package com.frontend.keltis;

import com.backend.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainKeltis extends Application {
    /**
     *
     * @param stage
     */
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

    /**
     * Launches the game
     */
    public static void main(String[] args) {

        launch();
    }
}