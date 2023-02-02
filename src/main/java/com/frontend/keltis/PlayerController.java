package com.frontend.keltis;

import com.backend.*;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.jar.Attributes;

public class PlayerController implements Initializable {

    @FXML
    public Pane namePane1;
    @FXML
    public Pane namePane2;
    @FXML
    private ChoiceBox<String> myAmount;
    @FXML
    private TextField name1;
    @FXML
    private TextField age1;
    @FXML
    private TextField name2;
    @FXML
    private TextField age2;
    @FXML
    private TextField name3;
    @FXML
    private TextField age3;
    @FXML
    private TextField name4;
    @FXML
    private TextField age4;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        myAmount.getItems().addAll("2","3","4");
        myAmount.getSelectionModel().selectFirst();
        namePane1.setVisible(false);
        namePane2.setVisible(false);
        myAmount.getSelectionModel()
                .selectedItemProperty()
                .addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) -> getPlayer(newValue));
    }


    public void getPlayer(String myAmount) {
        if(myAmount == "2"){
            namePane1.setVisible(false);
            namePane2.setVisible(false);
        }
        if(myAmount == "3"){
            namePane1.setVisible(true);
            namePane2.setVisible(false);
        }
        else if (myAmount == "4"){
            namePane1.setVisible(true);
            namePane2.setVisible(true);
        }
    }

    public void submitButton(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(MainKeltis.class.getResource("Game.fxml"));
        Stage stage = (Stage)namePane2.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        GameController gameController = fxmlLoader.getController();
        gameController.initialize();



        String name1Text = name1.getText();
        String age1Text = age1.getText();
        Player player1 = new Player(name1Text, Integer.parseInt(age1Text));
        gameController.gameInstance.addPlayer(player1);

        String name2Text = name2.getText();
        String age2Text = age2.getText();
        Player player2 = new Player(name2Text, Integer.parseInt(age2Text));
        gameController.gameInstance.addPlayer(player2);

        if(!name3.getText().equals("")) {
            String name3Text = name3.getText();
            String age3Text = age3.getText();
            Player player3 = new Player(name3Text, Integer.parseInt(age3Text));
            gameController.gameInstance.addPlayer(player3);

        }
        else if(!name4.getText().equals("")) {
            String name4Text = name4.getText();
            String age4Text = age4.getText();
            Player player4 = new Player(name4Text, Integer.parseInt(age4Text));
            gameController.gameInstance.addPlayer(player4);

        }
        //Oldest Player starts -> becomes first in ArrayList
        gameController.gameInstance.findOldestPlayer();
        stage.setScene(scene);
        stage.show();
    }
}
