package com.frontend.keltis;
import com.backend.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.InterruptedIOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController {
    public Text Player1Text;
    @FXML
    private ImageView imageview;
    public Game gameInstance = new Game();


    Button button1 = new Button();



    public void initialize() {
     //   Image i = new Image(new File("/images/813343350508748820.gif").toURI().toString());
       // imageview.setImage(i);
        //gameInstance = new Game();
    }
    public void showName(ActionEvent event) {
        Player1Text.setText(gameInstance.Players.get(0).getName());
        int test = 0;
        gameInstance.gameEnd();
    }
    public void setMusic(ActionEvent actionEvent) {

    }

    public void ButtonPressed(ActionEvent actionEvent) {
        Button x = (Button) actionEvent.getSource();
        System.out.println(x.getId());
    }
}

