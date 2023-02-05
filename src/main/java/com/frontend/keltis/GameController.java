package com.frontend.keltis;
import com.backend.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

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

    public static ArrayList<String> ImageURL = new ArrayList<>(55);
    public void cover(){
        ImageView[] stones = {bl0,bl1,bl2,bl3,bl4,bl5,bl6,bl7,bl8,bl9,bl10,
                br0, br1, br2, br3, br4, br5, br6, br7, br8, br9, br10,
                y0, y1, y2, y3, y4, y5, y6, y7, y8, y9, y10,
                p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10,
                g0, g1, g2, g3, g4, g5, g6, g7, g8, g9, g10};
        URL PicLocation = getClass().getResource("images/stoned.png");
        Image Temp = new Image(String.valueOf(PicLocation));
        for (ImageView image: stones) {
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

    public Stone FindStoneObject(String fxID) {
        Stone currentStone = null;
        for (int i = 0; i < 55; i++) {
            Stone check = gameInstance.Stones.get(i);
            if ((check.getID()).equals(fxID)) {
                currentStone = check;
            }
        }
        return currentStone;
    }

    public void ButtonPressed(ActionEvent event) throws IOException {
        //Check whether the turn exceeds the player count
        if(turn >= gameInstance.Players.size()){turn = 0;}
        Player currentPlayer = gameInstance.Players.get(turn);

        Button ButtonPressed = (Button) event.getSource();
        String ButtonID = ButtonPressed.getId();
        Stone ChosenStone = FindStoneObject(uncover(ButtonID));
        //Ask currentPlayer if they want the stone
        Alert GetConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        GetConfirmation.setTitle("Confirm Your Action");
        GetConfirmation.setHeaderText("Do you want to use the Stone?");
        GetConfirmation.setContentText("Choose your option.");
        ButtonType YesButton = new ButtonType("Yes");
        ButtonType NoButton = new ButtonType("No");
        GetConfirmation.getButtonTypes().setAll(YesButton, NoButton);
        Optional<ButtonType> result = GetConfirmation.showAndWait();

        System.out.println(currentPlayer.getName() + " chose Button: ");
        System.out.println(ButtonID);
        System.out.println("Stone ID: "+ ChosenStone.getID());
        //temporary for debugging purposes

        if (result.get() == YesButton){
            if(!(currentPlayer.pull(ChosenStone))){
                Alert WrongMove = new Alert(Alert.AlertType.CONFIRMATION);
                WrongMove.setTitle("Invalid Move");
                WrongMove.setHeaderText("You are not allowed to choose this stone");
                WrongMove.setContentText("Press ok to proceed");
                ButtonType Ok = new ButtonType("OK");
                WrongMove.getButtonTypes().setAll(Ok);
                Optional<ButtonType> result2 = WrongMove.showAndWait();
                if (result2.get() == Ok){
                    //TODO: Implement logic to retry the move
                }
            }
            //Set the corresponding Stone invisible
            setInvisible(ButtonID);

        } else if (result.get() == NoButton) {
            //TODO: Leave the Stone uncovered

        }
        turn++;
    }
    public void setInvisible(String ButtonID){
        bl0.setVisible(false);
    }
    public String uncover(String ButtonID) {
        StringBuilder sb = new StringBuilder();
        if (ButtonID.equals("b0")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(0));
            bl0.setImage(Temp);
            sb.append(ImageURL.get(0));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());
            return sb.toString();

        } else if (ButtonID.equals("b1")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(1));
            bl1.setImage(Temp);
            sb.append(ImageURL.get(1));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b2")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(2));
            bl2.setImage(Temp);
            sb.append(ImageURL.get(2));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b3")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(3));
            bl3.setImage(Temp);
            sb.append(ImageURL.get(3));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b4")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(4));
            bl4.setImage(Temp);
            sb.append(ImageURL.get(4));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b5")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(5));
            bl5.setImage(Temp);
            sb.append(ImageURL.get(5));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());
            return sb.toString();

        } else if (ButtonID.equals("b6")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(6));
            bl6.setImage(Temp);
            sb.append(ImageURL.get(6));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b7")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(7));
            bl7.setImage(Temp);
            sb.append(ImageURL.get(7));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b8")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(8));
            bl8.setImage(Temp);
            sb.append(ImageURL.get(8));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+ 8);
            sb.delete(sb.indexOf(".png"),sb.length());
            return sb.toString();

        } else if (ButtonID.equals("b9")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(9));
            bl9.setImage(Temp);
            sb.append(ImageURL.get(9));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b10")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(10));
            bl10.setImage(Temp);
            sb.append(ImageURL.get(10));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b11")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(11));
            br0.setImage(Temp);
            sb.append(ImageURL.get(11));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b12")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(12));
            br1.setImage(Temp);
            sb.append(ImageURL.get(12));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b13")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(13));
            br2.setImage(Temp);
            sb.append(ImageURL.get(13));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b14")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(14));
            br3.setImage(Temp);
            sb.append(ImageURL.get(14));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b15")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(15));
            br4.setImage(Temp);
            sb.append(ImageURL.get(15));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b16")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(16));
            br5.setImage(Temp);
            sb.append(ImageURL.get(16));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b17")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(17));
            br6.setImage(Temp);
            sb.append(ImageURL.get(17));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b18")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(18));
            br7.setImage(Temp);
            sb.append(ImageURL.get(18));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b19")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(19));
            br8.setImage(Temp);
            sb.append(ImageURL.get(19));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b20")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(20));
            br9.setImage(Temp);
            sb.append(ImageURL.get(20));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b21")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(21));
            br10.setImage(Temp);
            sb.append(ImageURL.get(21));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b22")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(22));
            y0.setImage(Temp);
            sb.append(ImageURL.get(22));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();
        } else if (ButtonID.equals("b23")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(23));
            y1.setImage(Temp);
            sb.append(ImageURL.get(23));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b24")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(24));
            y2.setImage(Temp);
            sb.append(ImageURL.get(24));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b25")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(25));
            y3.setImage(Temp);
            sb.append(ImageURL.get(25));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b26")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(26));
            y4.setImage(Temp);
            sb.append(ImageURL.get(26));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b27")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(27));
            y5.setImage(Temp);
            sb.append(ImageURL.get(27));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b28")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(28));
            y6.setImage(Temp);
            sb.append(ImageURL.get(28));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b29")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(29));
            y7.setImage(Temp);
            sb.append(ImageURL.get(29));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b30")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(30));
            y8.setImage(Temp);
            sb.append(ImageURL.get(30));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b31")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(31));
            y9.setImage(Temp);
            sb.append(ImageURL.get(31));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b32")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(32));
            y10.setImage(Temp);
            sb.append(ImageURL.get(32));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b33")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(33));
            g0.setImage(Temp);
            sb.append(ImageURL.get(33));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b34")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(34));
            g1.setImage(Temp);
            sb.append(ImageURL.get(34));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b35")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(35));
            g2.setImage(Temp);
            sb.append(ImageURL.get(35));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b36")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(36));
            g3.setImage(Temp);
            sb.append(ImageURL.get(36));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b37")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(37));
            g4.setImage(Temp);
            sb.append(ImageURL.get(37));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b38")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(38));
            g5.setImage(Temp);
            sb.append(ImageURL.get(38));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b39")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(39));
            g6.setImage(Temp);
            sb.append(ImageURL.get(39));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b40")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(40));
            g7.setImage(Temp);
            sb.append(ImageURL.get(40));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b41")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(41));
            g8.setImage(Temp);
            sb.append(ImageURL.get(41));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b42")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(42));
            g9.setImage(Temp);
            sb.append(ImageURL.get(42));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b43")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(43));
            g10.setImage(Temp);
            sb.append(ImageURL.get(43));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b44")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(44));
            p0.setImage(Temp);
            sb.append(ImageURL.get(44));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b45")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(45));
            p1.setImage(Temp);
            sb.append(ImageURL.get(45));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b46")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(46));
            p2.setImage(Temp);
            sb.append(ImageURL.get(46));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b47")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(47));
            p3.setImage(Temp);
            sb.append(ImageURL.get(47));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b48")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(48));
            p4.setImage(Temp);
            sb.append(ImageURL.get(48));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b49")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(49));
            p5.setImage(Temp);
            sb.append(ImageURL.get(49));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b50")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(50));
            p6.setImage(Temp);
            sb.append(ImageURL.get(50));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b51")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(51));
            p7.setImage(Temp);
            sb.append(ImageURL.get(51));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b52")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(52));
            p8.setImage(Temp);
            sb.append(ImageURL.get(52));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b53")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(53));
            p9.setImage(Temp);
            sb.append(ImageURL.get(53));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        } else if (ButtonID.equals("b54")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(54));
            p10.setImage(Temp);
            sb.append(ImageURL.get(54));
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

            return sb.toString();

        }
        return null;
    }
}

