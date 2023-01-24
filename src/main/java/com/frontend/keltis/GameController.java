package com.frontend.keltis;
import com.backend.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController {
    public Text Player1Text;
    @FXML
    private ImageView imageview;
    public Game game;

    //@Override
    public void initialize() {
        Image i = new Image(new File("images/813343350508748820.gif").toURI().toString());
        imageview.setImage(i);
        game = new Game();
    }
    public void showName(ActionEvent event) {
        Player1Text.setText(game.Players.get(0).getName());
        int test = 0;
        game.gameEnd();
    }
    public void setMusic(ActionEvent actionEvent) {

    }
}

