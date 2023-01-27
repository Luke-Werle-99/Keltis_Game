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
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class GameController {
    public Text Player1Text;
    @FXML
    private ImageView imageview;
    @FXML
    private Button Button1;
    public Game gameInstance;

    //@Override
    public void initialize() {
        gameInstance = new Game();
        gameInstance.generateStones();
        gameInstance.findOldestPlayer();
    }

    public void ButtonPressed(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Your Action");
        alert.setHeaderText("Do you want to use the Stone?");
        alert.setContentText("Choose your option.");

        ButtonType buttonTypeOne = new ButtonType("Yes");
        ButtonType buttonTypeTwo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            //TODO: Add the Stone to currentPlayer
            System.out.println("Successfully added the Stone ");

        } else if (result.get() == buttonTypeTwo) {
            //TODO: Leave the Stone uncovered
            System.out.println("Left the Stone uncovered");
        }
    }
}

