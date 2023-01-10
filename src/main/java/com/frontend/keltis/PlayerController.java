package com.frontend.keltis;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayerController implements Initializable {
    @FXML
    private ChoiceBox<String> myAmount;



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        myAmount.getItems().addAll("2","3","4");




    }



    public void getPlayer(ActionEvent event) {



        String myAmount = this.myAmount.getValue();



    }




}
