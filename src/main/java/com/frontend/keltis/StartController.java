package com.frontend.keltis;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {

    /**
     * Changes scene to the next when called.
     * @param event used by button 1
     * @throws IOException
     */
   public void switchToPlayerAmount(ActionEvent event) throws IOException{

        Parent root = FXMLLoader.load(getClass().getResource("PlayerAmount.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Closes the game when called.
     * @param event used by button2
     * @throws IOException
     */
    public void exitGame(ActionEvent event) throws IOException{
       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       stage.close();
    }



}