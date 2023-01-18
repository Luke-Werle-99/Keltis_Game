package com.frontend.keltis;
import com.backend.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameController {
    public Text Player1Text;
    @FXML



    Game game;



    //int
    public void setSetting(){
        game = new Game();
    }


    public void showName(ActionEvent event) {
        int test = 0;
        game.gameEnd();
    }
}
