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

    public ArrayList<String> ImageURL = new ArrayList<>(55);

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
        cover();
        for (int i = 0; i < 11; i++) {
            StringBuilder sb = new StringBuilder("images/blau");
            sb.append(i);
            sb.append(".png");
            URL PicLocation = getClass().getResource(sb.toString());
            String URL = String.valueOf(PicLocation);
            ImageURL.add(URL);
        }
        for (int i = 0; i < 11; i++) {
            StringBuilder sb = new StringBuilder("images/braun");
            sb.append(i);
            sb.append(".png");
            URL PicLocation = getClass().getResource(sb.toString());
            String URL = String.valueOf(PicLocation);
            ImageURL.add(URL);
        }
        for (int i = 0; i < 11; i++) {
            StringBuilder sb = new StringBuilder("images/gelb");
            sb.append(i);
            sb.append(".png");
            URL PicLocation = getClass().getResource(sb.toString());
            String URL = String.valueOf(PicLocation);
            ImageURL.add(URL);
        }
        for (int i = 0; i < 11; i++) {
            StringBuilder sb = new StringBuilder("images/gruen");
            sb.append(i);
            sb.append(".png");
            URL PicLocation = getClass().getResource(sb.toString());
            String URL = String.valueOf(PicLocation);
            ImageURL.add(URL);
        }
        for (int i = 0; i < 11; i++) {
            StringBuilder sb = new StringBuilder("images/pink");
            sb.append(i);
            sb.append(".png");
            URL PicLocation = getClass().getResource(sb.toString());
            String URL = String.valueOf(PicLocation);
            ImageURL.add(URL);
        }
        Collections.shuffle(ImageURL);

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
            Image Temp = new Image(ImageURL.get(0));
            bl0.setImage(Temp);
        } else if (ButtonID.equals("b1")) {
            Image Temp = new Image(ImageURL.get(1));
            bl1.setImage(Temp);
        } else if (ButtonID.equals("b2")) {
            Image Temp = new Image(ImageURL.get(2));
            bl2.setImage(Temp);
        } else if (ButtonID.equals("b3")) {

            Image Temp = new Image(ImageURL.get(3));
            bl3.setImage(Temp);
        } else if (ButtonID.equals("b4")) {
            Image Temp = new Image(ImageURL.get(4));
            bl4.setImage(Temp);
        } else if (ButtonID.equals("b5")) {
            Image Temp = new Image(ImageURL.get(5));
            bl5.setImage(Temp);
        } else if (ButtonID.equals("b6")) {
            Image Temp = new Image(ImageURL.get(6));
            bl6.setImage(Temp);
        } else if (ButtonID.equals("b7")) {

            Image Temp = new Image(ImageURL.get(7));
            bl7.setImage(Temp);
        } else if (ButtonID.equals("b8")) {
            Image Temp = new Image(ImageURL.get(8));
            bl8.setImage(Temp);
        } else if (ButtonID.equals("b9")) {
            Image Temp = new Image(ImageURL.get(9));
            bl9.setImage(Temp);
        } else if (ButtonID.equals("b10")) {
            Image Temp = new Image(ImageURL.get(10));
            bl10.setImage(Temp);
        } else if (ButtonID.equals("b11")) {
            Image Temp = new Image(ImageURL.get(11));
            br0.setImage(Temp);
        } else if (ButtonID.equals("b12")) {
            Image Temp = new Image(ImageURL.get(12));
            br1.setImage(Temp);
        } else if (ButtonID.equals("b13")) {
            Image Temp = new Image(ImageURL.get(13));
            br2.setImage(Temp);
        } else if (ButtonID.equals("b14")) {

            Image Temp = new Image(ImageURL.get(14));
            br3.setImage(Temp);
        } else if (ButtonID.equals("b15")) {

            Image Temp = new Image(ImageURL.get(15));
            br4.setImage(Temp);
        } else if (ButtonID.equals("b16")) {
            Image Temp = new Image(ImageURL.get(16));
            br5.setImage(Temp);
        } else if (ButtonID.equals("b17")) {
            Image Temp = new Image(ImageURL.get(17));
            br6.setImage(Temp);
        } else if (ButtonID.equals("b18")) {
            Image Temp = new Image(ImageURL.get(18));
            br7.setImage(Temp);
        } else if (ButtonID.equals("b19")) {
            Image Temp = new Image(ImageURL.get(19));
            br8.setImage(Temp);
        } else if (ButtonID.equals("b20")) {

            Image Temp = new Image(ImageURL.get(20));
            br9.setImage(Temp);
        } else if (ButtonID.equals("b21")) {
            Image Temp = new Image(ImageURL.get(21));
            br10.setImage(Temp);
        } else if (ButtonID.equals("b22")) {

            Image Temp = new Image(ImageURL.get(22));
            y0.setImage(Temp);
        } else if (ButtonID.equals("b23")) {

            Image Temp = new Image(ImageURL.get(23));
            y1.setImage(Temp);
        } else if (ButtonID.equals("b24")) {
            Image Temp = new Image(ImageURL.get(24));
            y2.setImage(Temp);
        } else if (ButtonID.equals("b25")) {
            Image Temp = new Image(ImageURL.get(25));
            y3.setImage(Temp);
        } else if (ButtonID.equals("b26")) {
            Image Temp = new Image(ImageURL.get(26));
            y4.setImage(Temp);
        } else if (ButtonID.equals("b27")) {
            Image Temp = new Image(ImageURL.get(27));
            y5.setImage(Temp);
        } else if (ButtonID.equals("b28")) {
            Image Temp = new Image(ImageURL.get(28));
            y6.setImage(Temp);
        } else if (ButtonID.equals("b29")) {
            Image Temp = new Image(ImageURL.get(29));
            y7.setImage(Temp);
        } else if (ButtonID.equals("b30")) {
            Image Temp = new Image(ImageURL.get(30));
            y8.setImage(Temp);
        } else if (ButtonID.equals("b31")) {
            Image Temp = new Image(ImageURL.get(31));
            y9.setImage(Temp);
        } else if (ButtonID.equals("b32")) {
            Image Temp = new Image(ImageURL.get(32));
            y10.setImage(Temp);
        } else if (ButtonID.equals("b33")) {
            Image Temp = new Image(ImageURL.get(33));
            g0.setImage(Temp);
        } else if (ButtonID.equals("b34")) {
            Image Temp = new Image(ImageURL.get(34));
            g1.setImage(Temp);
        } else if (ButtonID.equals("b35")) {
            Image Temp = new Image(ImageURL.get(35));
            g2.setImage(Temp);
        } else if (ButtonID.equals("b36")) {
            Image Temp = new Image(ImageURL.get(36));
            g3.setImage(Temp);
        } else if (ButtonID.equals("b37")) {
            Image Temp = new Image(ImageURL.get(37));
            g4.setImage(Temp);
        } else if (ButtonID.equals("b38")) {
            Image Temp = new Image(ImageURL.get(38));
            g5.setImage(Temp);
        } else if (ButtonID.equals("b39")) {
            Image Temp = new Image(ImageURL.get(39));
            g6.setImage(Temp);
        } else if (ButtonID.equals("b40")) {
            Image Temp = new Image(ImageURL.get(40));
            g7.setImage(Temp);
        } else if (ButtonID.equals("b41")) {
            Image Temp = new Image(ImageURL.get(41));
            g8.setImage(Temp);
        } else if (ButtonID.equals("b42")) {
            Image Temp = new Image(ImageURL.get(42));
            g9.setImage(Temp);
        } else if (ButtonID.equals("b43")) {
            Image Temp = new Image(ImageURL.get(43));
            g10.setImage(Temp);
        } else if (ButtonID.equals("b44")) {
            Image Temp = new Image(ImageURL.get(44));
            p0.setImage(Temp);
        } else if (ButtonID.equals("b45")) {
            Image Temp = new Image(ImageURL.get(45));
            p1.setImage(Temp);
        } else if (ButtonID.equals("b46")) {
            Image Temp = new Image(ImageURL.get(46));
            p2.setImage(Temp);
        } else if (ButtonID.equals("b47")) {
            Image Temp = new Image(ImageURL.get(47));
            p3.setImage(Temp);
        } else if (ButtonID.equals("b48")) {
            Image Temp = new Image(ImageURL.get(48));
            p4.setImage(Temp);
        } else if (ButtonID.equals("b49")) {
            Image Temp = new Image(ImageURL.get(49));
            p5.setImage(Temp);
        } else if (ButtonID.equals("b50")) {
            Image Temp = new Image(ImageURL.get(50));
            p6.setImage(Temp);
        } else if (ButtonID.equals("b51")) {
            Image Temp = new Image(ImageURL.get(51));
            p7.setImage(Temp);
        } else if (ButtonID.equals("b52")) {
            Image Temp = new Image(ImageURL.get(52));
            p8.setImage(Temp);
        } else if (ButtonID.equals("b53")) {
            Image Temp = new Image(ImageURL.get(53));
            p9.setImage(Temp);
        } else if (ButtonID.equals("b54")) {
            Image Temp = new Image(ImageURL.get(54));
            p10.setImage(Temp);
        }
    }
}

