package com.frontend.keltis;
import com.backend.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class GameController {
    public Text PlayerText;
    public Game gameInstance;
    public void initialize() {
        gameInstance = new Game();
        gameInstance.generateStones();
    }
    public void ButtonPressed(ActionEvent event) throws IOException {
        gameInstance.findOldestPlayer();

        Button ButtonPressed = (Button) event.getSource();
        String ButtonId = ButtonPressed.getId();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Your Action");
        alert.setHeaderText("Do you want to use the Stone?");
        alert.setContentText("Choose your option.");

        ButtonType YesButton = new ButtonType("Yes");
        ButtonType NoButton = new ButtonType("No");
        alert.getButtonTypes().setAll(YesButton, NoButton);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == YesButton){
            //TODO: Add the Stone to currentPlayer
            System.out.println("Added " + ButtonId);


        } else if (result.get() == NoButton) {
            //TODO: Leave the Stone uncovered
            System.out.println("Left " + ButtonId + " uncovered");
        }
    }
}

