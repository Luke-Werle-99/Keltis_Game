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
    public int turn;
    public ImageView bl0,bl1,bl2,bl3,bl4,bl5,bl6,bl7,bl8,bl9,bl10;
    public ImageView br0, br1, br2, br3, br4, br5, br6, br7, br8, br9, br10;
    public ImageView y0, y1, y2, y3, y4, y5, y6, y7, y8, y9, y10;
    public ImageView p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10;
    public ImageView g0, g1, g2, g3, g4, g5, g6, g7, g8, g9, g10;
    //button Reihenfolge b0 - b54 aufsteigend sortiert nach blau, braun, gelb, grün, pink


    public void cover(){
        ImageView[] stones = {bl0,bl1,bl2,bl3,bl4,bl5,bl6,bl7,bl8,bl9,bl10,
                br0, br1, br2, br3, br4, br5, br6, br7, br8, br9, br10,
                y0, y1, y2, y3, y4, y5, y6, y7, y8, y9, y10,
                p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10,
                g0, g1, g2, g3, g4, g5, g6, g7, g8, g9, g10};

        for (ImageView image: stones) {
            URL PicLocation = getClass().getResource("images/stoned.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            image.setImage(Temp);
        }
    }
    public void initialize() {
        gameInstance = new Game();
        gameInstance.generateStones();
        for (Stone x : gameInstance.Stones) {

        }
    }
    public void ButtonPressed(ActionEvent event) throws IOException {
        //Oldest Player starts -> becomes first in ArrayList
        gameInstance.findOldestPlayer();
        cover();
        //Check whether the turn exceeds the player count
        if(turn > gameInstance.Players.size()){turn = 0;}
        Player currentPlayer = gameInstance.Players.get(turn);

        //fetch button ID to retrieve Stone
        Button ButtonPressed = (Button) event.getSource();
        String ButtonId = ButtonPressed.getId();
        //temporary for debugging purposes
        //TODO: find a way to fetch the Stone ID from Button ID
        Stone ChosenStone = gameInstance.Stones.get(0);

        //Ask currentPlayer if they want the stone
        Alert GetConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        GetConfirmation.setTitle("Confirm Your Action");
        GetConfirmation.setHeaderText("Do you want to use the Stone?");
        GetConfirmation.setContentText("Choose your option.");
        ButtonType YesButton = new ButtonType("Yes");
        ButtonType NoButton = new ButtonType("No");
        GetConfirmation.getButtonTypes().setAll(YesButton, NoButton);
        Optional<ButtonType> result = GetConfirmation.showAndWait();

        if (result.get() == YesButton){
            if(!(currentPlayer.pull(ChosenStone))){
                Alert WrongMove = new Alert(Alert.AlertType.CONFIRMATION);
                WrongMove.setTitle("Invalid Move");
                WrongMove.setHeaderText("You are not allowed to choose this stone");
                WrongMove.setContentText("Press ok to proceed");
                ButtonType Ok = new ButtonType("OK");
                WrongMove.getButtonTypes().setAll(YesButton, NoButton);
                Optional<ButtonType> result2 = WrongMove.showAndWait();
                if (result2.get() == Ok){
                    //TODO: Implement logic to retry the move
                }
            }

        } else if (result.get() == NoButton) {
            //TODO: Leave the Stone uncovered

        }
        turn++;
    }
}

