package com.frontend.keltis;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameController {
    @FXML
    public Text player1;



    String playerString1 = "";

    //int
    public void setSetting( String player1){
        this.playerString1 = player1;
    }


    public void showName(ActionEvent event) {
        player1.setText(playerString1);
    }
}
