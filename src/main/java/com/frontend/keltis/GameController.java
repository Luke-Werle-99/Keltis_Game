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
    public Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19,
            b20, b21, b22, b23, b24, b25, b26, b27, b28, b29, b30, b31, b32, b33, b34, b35, b36, b37, b38, b39, b40,
            b41, b42, b43, b44, b45, b46, b47, b48, b49, b50, b51, b52, b53, b54;


    public void cover(){
        ImageView[] stones = {bl0,bl1,bl2,bl3,bl4,bl5,bl6,bl7,bl8,bl9,bl10,
                br0, br1, br2, br3, br4, br5, br6, br7, br8, br9, br10,
                y0, y1, y2, y3, y4, y5, y6, y7, y8, y9, y10,
                p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10,
                g0, g1, g2, g3, g4, g5, g6, g7, g8, g9, g10};

       /* Button[] buttons = {b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19,
                b20, b21, b22, b23, b24, b25, b26, b27, b28, b29, b30, b31, b32, b33, b34, b35, b36, b37, b38, b39, b40,
                b41, b42, b43, b44, b45, b46, b47, b48, b49, b50, b51, b52, b53, b54 };
        for ( Button button:buttons){
            URL PicLocation =
            }
        */

        for (ImageView image: stones) {
            URL PicLocation = getClass().getResource("images/stoned.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            image.setImage(Temp);
        }
    }

    public void initialize() {
        gameInstance = new Game();
        gameInstance.generateStones();
        cover();
    }
    public void ButtonPressed(ActionEvent event) throws IOException {
        //Ask currentPlayer if they want the stone
        Alert GetConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        GetConfirmation.setTitle("Confirm Your Action");
        GetConfirmation.setHeaderText("Do you want to use the Stone?");
        GetConfirmation.setContentText("Choose your option.");
        ButtonType YesButton = new ButtonType("Yes");
        ButtonType NoButton = new ButtonType("No");
        GetConfirmation.getButtonTypes().setAll(YesButton, NoButton);
        Optional<ButtonType> result = GetConfirmation.showAndWait();

        //Check whether the turn exceeds the player count
        if(turn >= gameInstance.Players.size()){turn = 0;}
        Player currentPlayer = gameInstance.Players.get(turn);
        //fetch button ID to retrieve Stone
        Button ButtonPressed = (Button) event.getSource();
        String ButtonID = ButtonPressed.getId();
        uncover(ButtonID);
        System.out.println(currentPlayer.getName() + " chose Button: ");
        System.out.println(ButtonID);
        //temporary for debugging purposes
        //TODO: find a way to fetch the Stone ID from Button ID
        Stone ChosenStone = gameInstance.Stones.get(0);



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
    public void uncover(String ButtonID) {

        if (ButtonID.equals("b0")) {
            URL PicLocation = getClass().getResource("images/blau0.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            bl0.setImage(Temp);
        } else if (ButtonID.equals("b1")) {
            URL PicLocation = getClass().getResource("images/blau1.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            bl1.setImage(Temp);
        } else if (ButtonID.equals("b2")) {
            URL PicLocation = getClass().getResource("images/blau2.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            bl2.setImage(Temp);
        } else if (ButtonID.equals("b3")) {
            URL PicLocation = getClass().getResource("images/blau3.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            bl3.setImage(Temp);
        } else if (ButtonID.equals("b4")) {
            URL PicLocation = getClass().getResource("images/blau4.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            bl4.setImage(Temp);
        } else if (ButtonID.equals("b5")) {
            URL PicLocation = getClass().getResource("images/blau5.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            bl5.setImage(Temp);
        } else if (ButtonID.equals("b6")) {
            URL PicLocation = getClass().getResource("images/blau6.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            bl6.setImage(Temp);
        } else if (ButtonID.equals("b7")) {
            URL PicLocation = getClass().getResource("images/blau7.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            bl7.setImage(Temp);
        } else if (ButtonID.equals("b8")) {
            URL PicLocation = getClass().getResource("images/blau8.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            bl8.setImage(Temp);
        } else if (ButtonID.equals("b9")) {
            URL PicLocation = getClass().getResource("images/blau9.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            bl9.setImage(Temp);
        } else if (ButtonID.equals("b10")) {
            URL PicLocation = getClass().getResource("images/blau10.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            bl10.setImage(Temp);
        } else if (ButtonID.equals("b11")) {
            URL PicLocation = getClass().getResource("images/braun0.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            br0.setImage(Temp);
        } else if (ButtonID.equals("b12")) {
            URL PicLocation = getClass().getResource("images/braun1.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            br1.setImage(Temp);
        } else if (ButtonID.equals("b13")) {
            URL PicLocation = getClass().getResource("images/braun2.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            br2.setImage(Temp);
        } else if (ButtonID.equals("b14")) {
            URL PicLocation = getClass().getResource("images/braun3.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            br3.setImage(Temp);
        } else if (ButtonID.equals("b15")) {
            URL PicLocation = getClass().getResource("images/braun4.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            br4.setImage(Temp);
        } else if (ButtonID.equals("b16")) {
            URL PicLocation = getClass().getResource("images/braun5.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            br5.setImage(Temp);
        } else if (ButtonID.equals("b17")) {
            URL PicLocation = getClass().getResource("images/braun6.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            br6.setImage(Temp);
        } else if (ButtonID.equals("b18")) {
            URL PicLocation = getClass().getResource("images/braun7.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            br7.setImage(Temp);
        } else if (ButtonID.equals("b19")) {
            URL PicLocation = getClass().getResource("images/braun8.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            br8.setImage(Temp);
        } else if (ButtonID.equals("b20")) {
            URL PicLocation = getClass().getResource("images/braun9.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            br9.setImage(Temp);
        } else if (ButtonID.equals("b21")) {
            URL PicLocation = getClass().getResource("images/braun10.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            br10.setImage(Temp);
        } else if (ButtonID.equals("b22")) {
            URL PicLocation = getClass().getResource("images/gelb0.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            y0.setImage(Temp);
        } else if (ButtonID.equals("b23")) {
            URL PicLocation = getClass().getResource("images/gelb1.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            y1.setImage(Temp);
        } else if (ButtonID.equals("b24")) {
            URL PicLocation = getClass().getResource("images/gelb2.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            y2.setImage(Temp);
        } else if (ButtonID.equals("b25")) {
            URL PicLocation = getClass().getResource("images/gelb3.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            y3.setImage(Temp);
        } else if (ButtonID.equals("b26")) {
            URL PicLocation = getClass().getResource("images/gelb4.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            y4.setImage(Temp);
        } else if (ButtonID.equals("b27")) {
            URL PicLocation = getClass().getResource("images/gelb5.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            y5.setImage(Temp);
        } else if (ButtonID.equals("b28")) {
            URL PicLocation = getClass().getResource("images/gelb6.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            y6.setImage(Temp);
        } else if (ButtonID.equals("b29")) {
            URL PicLocation = getClass().getResource("images/gelb7.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            y7.setImage(Temp);
        } else if (ButtonID.equals("b30")) {
            URL PicLocation = getClass().getResource("images/gelb8.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            y8.setImage(Temp);
        } else if (ButtonID.equals("b31")) {
            URL PicLocation = getClass().getResource("images/gelb9.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            y9.setImage(Temp);
        } else if (ButtonID.equals("b32")) {
            URL PicLocation = getClass().getResource("images/gelb10.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            y10.setImage(Temp);
        } else if (ButtonID.equals("b33")) {
            URL PicLocation = getClass().getResource("images/gruen0.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            g0.setImage(Temp);
        } else if (ButtonID.equals("b34")) {
            URL PicLocation = getClass().getResource("images/gruen1.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            g1.setImage(Temp);
        } else if (ButtonID.equals("b35")) {
            URL PicLocation = getClass().getResource("images/gruen2.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            g2.setImage(Temp);
        } else if (ButtonID.equals("b36")) {
            URL PicLocation = getClass().getResource("images/gruen3.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            g3.setImage(Temp);
        } else if (ButtonID.equals("b37")) {
            URL PicLocation = getClass().getResource("images/gruen4.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            g4.setImage(Temp);
        } else if (ButtonID.equals("b38")) {
            URL PicLocation = getClass().getResource("images/gruen5.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            g5.setImage(Temp);
        } else if (ButtonID.equals("b39")) {
            URL PicLocation = getClass().getResource("images/gruen6.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            g6.setImage(Temp);
        } else if (ButtonID.equals("b40")) {
            URL PicLocation = getClass().getResource("images/gruen7.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            g7.setImage(Temp);
        } else if (ButtonID.equals("b41")) {
            URL PicLocation = getClass().getResource("images/gruen8.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            g8.setImage(Temp);
        } else if (ButtonID.equals("b42")) {
            URL PicLocation = getClass().getResource("images/gruen9.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            g9.setImage(Temp);
        } else if (ButtonID.equals("b43")) {
            URL PicLocation = getClass().getResource("images/gruen10.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            g10.setImage(Temp);
        } else if (ButtonID.equals("b44")) {
            URL PicLocation = getClass().getResource("images/pink0.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            p0.setImage(Temp);
        } else if (ButtonID.equals("b45")) {
            URL PicLocation = getClass().getResource("images/pink1.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            p1.setImage(Temp);
        } else if (ButtonID.equals("b46")) {
            URL PicLocation = getClass().getResource("images/pink2.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            p2.setImage(Temp);
        } else if (ButtonID.equals("b47")) {
            URL PicLocation = getClass().getResource("images/pink3.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            p3.setImage(Temp);
        } else if (ButtonID.equals("b48")) {
            URL PicLocation = getClass().getResource("images/pink4.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            p4.setImage(Temp);
        } else if (ButtonID.equals("b49")) {
            URL PicLocation = getClass().getResource("images/pink5.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            p5.setImage(Temp);
        } else if (ButtonID.equals("b50")) {
            URL PicLocation = getClass().getResource("images/pink6.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            p6.setImage(Temp);
        } else if (ButtonID.equals("b51")) {
            URL PicLocation = getClass().getResource("images/pink7.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            p7.setImage(Temp);
        } else if (ButtonID.equals("b52")) {
            URL PicLocation = getClass().getResource("images/pink8.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            p8.setImage(Temp);
        } else if (ButtonID.equals("b53")) {
            URL PicLocation = getClass().getResource("images/pink9.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            p9.setImage(Temp);
        } else if (ButtonID.equals("b54")) {
            URL PicLocation = getClass().getResource("images/pink10.png");
            Image Temp = new Image(String.valueOf(PicLocation));
            p10.setImage(Temp);
        }
    }
}

