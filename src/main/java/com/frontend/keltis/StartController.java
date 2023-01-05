package com.example.fxscenebuilder;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {

    private Stage stage;
    private Scene scene;
    private Parent root;


    //not used
    public void switchToStartMenu(ActionEvent event) throws IOException{
                //Parent root = FXMLLoader.load(getClass().getResource("StartMenu.fxml"));
               FXMLLoader loader = new FXMLLoader(getClass().getResource("StartMenu.fxml"));
               root = loader.load();
       //Scene2Controller scene2Controller = loader.getController();
                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
   }
   public void switchToPlayerAmount(ActionEvent event) throws IOException{

        Parent root = FXMLLoader.load(getClass().getResource("PlayerAmount.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}