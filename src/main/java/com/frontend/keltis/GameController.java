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
    public int uncoveredCount = 0;
    public ImageView bl0,bl1,bl2,bl3,bl4,bl5,bl6,bl7,bl8,bl9,bl10;
    public ImageView br0, br1, br2, br3, br4, br5, br6, br7, br8, br9, br10;
    public ImageView y0, y1, y2, y3, y4, y5, y6, y7, y8, y9, y10;
    public ImageView p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10;
    public ImageView g0, g1, g2, g3, g4, g5, g6, g7, g8, g9, g10;
    //button Reihenfolge b0 - b54 aufsteigend sortiert nach blau, braun, gelb, grün, pink
    //Images to display Stones of Player1
    public ImageView pl1, pl2,pl3,pl4,pl5,pl6,pl7,pl8, pl9, pl10, pl11,pl12,pl13,pl14,pl15,pl16,pl17,pl18,pl19,pl20,
                    pl21,pl22,pl23,pl24,pl25,pl26,pl27,pl28,pl29,pl30,pl31,pl32,pl33,pl34,pl35,pl36,pl37,pl38,pl39,pl40,
                    pl41,pl42,pl43,pl44,pl45,pl46,pl47,pl48,pl49,pl50,pl51,pl52,pl53,pl54,pl55;
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

    public Stone FindStoneObject(String ImageURL)
    {
        Stone currentStone = null;
        for (int i = 0; i < 55; i++)
        {
            Stone check = gameInstance.Stones.get(i);
            if (ImageURL.contains(check.getID()))
            {
                currentStone = check;
                currentStone.setURL(ImageURL);
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

        //temporary for debugging purposes
        System.out.println(currentPlayer.getName() + " chose Button: ");
        System.out.println(ButtonID);
        System.out.println("Stone ID: "+ ChosenStone.getID());
        System.out.println("Stone URL: "+ ChosenStone.getURL());


        if (result.get() == YesButton){
            if((currentPlayer.pull(ChosenStone))){
                //Set the corresponding Stone invisible
                setInvisible(ButtonID);
                //PlayerStones(ChosenStone.getID(),turn,1);
                DisplayStoneForPlayer(gameInstance.Players);
                }else {
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



        } else if (result.get() == NoButton) {
            //TODO: Leave the Stone uncovered

        }

        if(uncoveredCount>54)
        {
            gameInstance.gameEnd();
        }
        turn++;
    }

    public void PlayerStones(String url, int turn, int playerId) {
        Stone[][] Player = new Stone[5][11];
        switch (playerId){
            case 1: {
                Player = gameInstance.Player1;
            }
        }
        if (turn == 0) {
            int x = Integer.parseInt(url.substring(url.length() - 1));
            if (url.contains("blau")) {
                Player[0][x] = new Stone();
                Player[0][x].setColor("blau");
                Player[0][x].setNumber(x);
            }
            if (url.contains("braun")) {
                Player[1][x] = new Stone();
                Player[1][x].setColor("braun");
                Player[1][x].setNumber(x);
            }

            if (url.contains("gelb")) {
                Player[2][x] = new Stone();
                Player[2][x].setColor("gelb");
                Player[2][x].setNumber(x);
            }
            if (url.contains("gruen")) {
                Player[3][x] = new Stone();
                Player[3][x].setColor("gruen");
                Player[3][x].setNumber(x);
            }
            if (url.contains("pink")) {
                Player[4][x] = new Stone();
                Player[4][x].setColor("pink");
                Player[4][x].setNumber(x);
            }

        }
    }

    public void DisplayStoneForPlayer(ArrayList<Player> Players) {
        Player Player1 = gameInstance.Players.get(0);
        Player Player2 = gameInstance.Players.get(0);
        if(gameInstance.Players.size() == 3){
            Player Player3 = gameInstance.Players.get(0);
        } else if (gameInstance.Players.size() == 3) {
            Player Player5 = gameInstance.Players.get(0);
        }
        //Create the Arrays of Player1 for the images
        ImageView[] BlueStonesPlayer1= {pl1, pl2,pl3,pl4,pl5,pl6,pl7,pl8, pl9, pl10, pl11};
        ImageView[] GreenStonesPlayer1= {pl12,pl13,pl14,pl15,pl16,pl17,pl18,pl19,pl20, pl21,pl22};
        ImageView[] BrownStonesPlayer1 = {pl23, pl24,pl25,pl26,pl27,pl28,pl29,pl30,pl31,pl32,pl33};
        ImageView[] PinkStonesPlayer1 = {pl34,pl35,pl36,pl37,pl38,pl39,pl40,pl41,pl42,pl43,pl44};
        ImageView[] YellowStonesPlayer1 = {pl45,pl46,pl47,pl48,pl49,pl50,pl51,pl52,pl53,pl54,pl55};
        int counter = 0;
        for (Stone x:Player1.getRowBlue()) {
            Image temp = new Image(x.getURL());
            BlueStonesPlayer1[counter].setImage(temp);
            counter++;
        }
        counter = 0;
        for (Stone x:Player1.getRowPink()) {
            Image temp = new Image(x.getURL());
            PinkStonesPlayer1[counter].setImage(temp);
            counter++;
        }
        counter = 0;
        for (Stone x:Player1.getRowGreen()) {
            Image temp = new Image(x.getURL());
            GreenStonesPlayer1[counter].setImage(temp);
            counter++;
        }
        counter = 0;
        for (Stone x:Player1.getRowYellow()) {
            Image temp = new Image(x.getURL());
            YellowStonesPlayer1[counter].setImage(temp);
            counter++;
        }
        counter = 0;
        for (Stone x:Player1.getRowBrown()) {
            Image temp = new Image(x.getURL());
            BrownStonesPlayer1[counter].setImage(temp);
            counter++;
        }
        counter = 0;

    }

    public void setInvisible(String ButtonID){
        if(ButtonID.equals("b0")){
            bl0.setVisible(false);
            b0.setDisable(true);
        } else if (ButtonID.equals("b1")) {
            bl1.setVisible(false);
            b1.setDisable(true);
        }else if (ButtonID.equals("b2")) {
            bl2.setVisible(false);
            b2.setDisable(true);
        }else if (ButtonID.equals("b3")) {
            bl3.setVisible(false);
            b3.setDisable(true);
        }else if (ButtonID.equals("b4")) {
            bl4.setVisible(false);
            b4.setDisable(true);
        }else if (ButtonID.equals("b5")) {
            bl5.setVisible(false);
            b5.setDisable(true);
        }else if (ButtonID.equals("b6")) {
            bl6.setVisible(false);
            b6.setDisable(true);
        }else if (ButtonID.equals("b7")) {
            bl7.setVisible(false);
            b7.setDisable(true);
        }else if (ButtonID.equals("b8")) {
            bl8.setVisible(false);
            b8.setDisable(true);
        }else if (ButtonID.equals("b9")) {
            bl9.setVisible(false);
            b9.setDisable(true);
        }else if (ButtonID.equals("b10")) {
            bl10.setVisible(false);
            b10.setDisable(true);
        }else if (ButtonID.equals("b11")) {
            br0.setVisible(false);
            b11.setDisable(true);
        }else if (ButtonID.equals("b12")) {
            br1.setVisible(false);
            b12.setDisable(true);
        }else if (ButtonID.equals("b13")) {
            br2.setVisible(false);
            b13.setDisable(true);
        }else if (ButtonID.equals("b14")) {
            br3.setVisible(false);
            b14.setDisable(true);
        }else if (ButtonID.equals("b15")) {
            br4.setVisible(false);
            b15.setDisable(true);
        }else if (ButtonID.equals("b16")) {
            br5.setVisible(false);
            b16.setDisable(true);
        }else if (ButtonID.equals("b17")) {
            br6.setVisible(false);
            b17.setDisable(true);
        }else if (ButtonID.equals("b18")) {
            br7.setVisible(false);
            b18.setDisable(true);
        }else if (ButtonID.equals("b19")) {
            br8.setVisible(false);
            b19.setDisable(true);
        }else if (ButtonID.equals("b20")) {
            br9.setVisible(false);
            b20.setDisable(true);
        }else if (ButtonID.equals("b21")) {
            br10.setVisible(false);
            b21.setDisable(true);
        }else if (ButtonID.equals("b22")) {
            y0.setVisible(false);
            b22.setDisable(true);
        }else if (ButtonID.equals("b23")) {
            y1.setVisible(false);
            b23.setDisable(true);
        }else if (ButtonID.equals("b24")) {
            y2.setVisible(false);
            b24.setDisable(true);
        }else if (ButtonID.equals("b25")) {
            y3.setVisible(false);
            b25.setDisable(true);
        }else if (ButtonID.equals("b26")) {
            y4.setVisible(false);
            b26.setDisable(true);
        }else if (ButtonID.equals("b27")) {
            y5.setVisible(false);
            b27.setDisable(true);
        }else if (ButtonID.equals("b28")) {
            y6.setVisible(false);
            b28.setDisable(true);
        }else if (ButtonID.equals("b29")) {
            y7.setVisible(false);
            b29.setDisable(true);
        }else if (ButtonID.equals("b30")) {
            y8.setVisible(false);
            b30.setDisable(true);
        }else if (ButtonID.equals("b31")) {
            y9.setVisible(false);
            b31.setDisable(true);
        }else if (ButtonID.equals("b32")) {
            y10.setVisible(false);
            b32.setDisable(true);
        }else if (ButtonID.equals("b33")) {
            g0.setVisible(false);
            b33.setDisable(true);
        }else if (ButtonID.equals("b34")) {
            g1.setVisible(false);
            b34.setDisable(true);
        }else if (ButtonID.equals("b35")) {
            g2.setVisible(false);
            b35.setDisable(true);
        }else if (ButtonID.equals("b36")) {
            g3.setVisible(false);
            b36.setDisable(true);
        }else if (ButtonID.equals("b37")) {
            g4.setVisible(false);
            b37.setDisable(true);
        }else if (ButtonID.equals("b38")) {
            g5.setVisible(false);
            b38.setDisable(true);
        }else if (ButtonID.equals("b39")) {
            g6.setVisible(false);
            b39.setDisable(true);
        }else if (ButtonID.equals("b40")) {
            g7.setVisible(false);
            b40.setDisable(true);
        }else if (ButtonID.equals("b41")) {
            g8.setVisible(false);
            b41.setDisable(true);
        }else if (ButtonID.equals("b42")) {
            g9.setVisible(false);
            b42.setDisable(true);
        }else if (ButtonID.equals("b43")) {
            g10.setVisible(false);
            b43.setDisable(true);
        }else if (ButtonID.equals("b44")) {
            p0.setVisible(false);
            b44.setDisable(true);
        }else if (ButtonID.equals("b45")) {
            p1.setVisible(false);
            b45.setDisable(true);
        }else if (ButtonID.equals("b46")) {
            p2.setVisible(false);
            b46.setDisable(true);
        }else if (ButtonID.equals("b47")) {
            p3.setVisible(false);
            b47.setDisable(true);
        }else if (ButtonID.equals("b48")) {
            p4.setVisible(false);
            b48.setDisable(true);
        }else if (ButtonID.equals("b49")) {
            p5.setVisible(false);
            b49.setDisable(true);
        }else if (ButtonID.equals("b50")) {
            p6.setVisible(false);
            b50.setDisable(true);
        }else if (ButtonID.equals("b51")) {
            p7.setVisible(false);
            b51.setDisable(true);
        }else if (ButtonID.equals("b52")) {
            p8.setVisible(false);
            b52.setDisable(true);
        }else if (ButtonID.equals("b53")) {
            p9.setVisible(false);
            b53.setDisable(true);
        }else if (ButtonID.equals("b54")) {
            p10.setVisible(false);
            b54.setDisable(true);
        }

    }
    public String uncover(String ButtonID) {

        uncoveredCount++; //count the uncovered stones

        StringBuilder sb = new StringBuilder();
        if (ButtonID.equals("b0")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(0));
            bl0.setImage(Temp);
            sb.append(ImageURL.get(0));
            /*
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

             */
            return sb.toString();

        } else if (ButtonID.equals("b1")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(1));
            bl1.setImage(Temp);
            sb.append(ImageURL.get(1));
            /*
            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b2")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(2));
            bl2.setImage(Temp);
            sb.append(ImageURL.get(2));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b3")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(3));
            bl3.setImage(Temp);
            sb.append(ImageURL.get(3));
                       /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


                        */
            return sb.toString();

        } else if (ButtonID.equals("b4")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(4));
            bl4.setImage(Temp);
            sb.append(ImageURL.get(4));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b5")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(5));
            bl5.setImage(Temp);
            sb.append(ImageURL.get(5));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());

             */
            return sb.toString();

        } else if (ButtonID.equals("b6")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(6));
            bl6.setImage(Temp);
            sb.append(ImageURL.get(6));
                        /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


                         */

            return sb.toString();

        } else if (ButtonID.equals("b7")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(7));
            bl7.setImage(Temp);
            sb.append(ImageURL.get(7));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b8")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(8));
            bl8.setImage(Temp);
            sb.append(ImageURL.get(8));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+ 8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b9")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(9));
            bl9.setImage(Temp);
            sb.append(ImageURL.get(9));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b10")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(10));
            bl10.setImage(Temp);
            sb.append(ImageURL.get(10));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b11")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(11));
            br0.setImage(Temp);
            sb.append(ImageURL.get(11));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b12")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(12));
            br1.setImage(Temp);
            sb.append(ImageURL.get(12));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b13")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(13));
            br2.setImage(Temp);
            sb.append(ImageURL.get(13));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b14")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(14));
            br3.setImage(Temp);
            sb.append(ImageURL.get(14));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b15")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(15));
            br4.setImage(Temp);
            sb.append(ImageURL.get(15));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b16")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(16));
            br5.setImage(Temp);
            sb.append(ImageURL.get(16));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */

            return sb.toString();

        } else if (ButtonID.equals("b17")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(17));
            br6.setImage(Temp);
            sb.append(ImageURL.get(17));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b18")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(18));
            br7.setImage(Temp);
            sb.append(ImageURL.get(18));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b19")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(19));
            br8.setImage(Temp);
            sb.append(ImageURL.get(19));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */

            return sb.toString();

        } else if (ButtonID.equals("b20")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(20));
            br9.setImage(Temp);
            sb.append(ImageURL.get(20));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b21")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(21));
            br10.setImage(Temp);
            sb.append(ImageURL.get(21));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b22")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(22));
            y0.setImage(Temp);
            sb.append(ImageURL.get(22));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();
        } else if (ButtonID.equals("b23")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(23));
            y1.setImage(Temp);
            sb.append(ImageURL.get(23));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b24")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(24));
            y2.setImage(Temp);
            sb.append(ImageURL.get(24));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b25")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(25));
            y3.setImage(Temp);
            sb.append(ImageURL.get(25));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b26")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(26));
            y4.setImage(Temp);
            sb.append(ImageURL.get(26));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b27")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(27));
            y5.setImage(Temp);
            sb.append(ImageURL.get(27));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b28")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(28));
            y6.setImage(Temp);
            sb.append(ImageURL.get(28));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b29")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(29));
            y7.setImage(Temp);
            sb.append(ImageURL.get(29));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b30")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(30));
            y8.setImage(Temp);
            sb.append(ImageURL.get(30));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b31")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(31));
            y9.setImage(Temp);
            sb.append(ImageURL.get(31));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b32")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(32));
            y10.setImage(Temp);
            sb.append(ImageURL.get(32));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b33")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(33));
            g0.setImage(Temp);
            sb.append(ImageURL.get(33));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b34")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(34));
            g1.setImage(Temp);
            sb.append(ImageURL.get(34));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b35")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(35));
            g2.setImage(Temp);
            sb.append(ImageURL.get(35));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b36")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(36));
            g3.setImage(Temp);
            sb.append(ImageURL.get(36));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b37")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(37));
            g4.setImage(Temp);
            sb.append(ImageURL.get(37));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b38")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(38));
            g5.setImage(Temp);
            sb.append(ImageURL.get(38));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b39")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(39));
            g6.setImage(Temp);
            sb.append(ImageURL.get(39));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b40")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(40));
            g7.setImage(Temp);
            sb.append(ImageURL.get(40));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b41")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(41));
            g8.setImage(Temp);
            sb.append(ImageURL.get(41));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b42")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(42));
            g9.setImage(Temp);
            sb.append(ImageURL.get(42));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b43")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(43));
            g10.setImage(Temp);
            sb.append(ImageURL.get(43));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b44")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(44));
            p0.setImage(Temp);
            sb.append(ImageURL.get(44));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b45")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(45));
            p1.setImage(Temp);
            sb.append(ImageURL.get(45));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b46")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(46));
            p2.setImage(Temp);
            sb.append(ImageURL.get(46));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b47")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(47));
            p3.setImage(Temp);
            sb.append(ImageURL.get(47));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b48")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(48));
            p4.setImage(Temp);
            sb.append(ImageURL.get(48));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b49")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(49));
            p5.setImage(Temp);
            sb.append(ImageURL.get(49));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b50")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(50));
            p6.setImage(Temp);
            sb.append(ImageURL.get(50));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b51")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(51));
            p7.setImage(Temp);
            sb.append(ImageURL.get(51));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b52")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(52));
            p8.setImage(Temp);
            sb.append(ImageURL.get(52));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b53")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(53));
            p9.setImage(Temp);
            sb.append(ImageURL.get(53));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        } else if (ButtonID.equals("b54")) {
            sb.setLength(0);
            Image Temp = new Image(ImageURL.get(54));
            p10.setImage(Temp);
            sb.append(ImageURL.get(54));
            /*

            sb.delete(0,sb.indexOf("/images"));
            sb.delete(sb.indexOf("/images"),sb.indexOf("/images")+8);
            sb.delete(sb.indexOf(".png"),sb.length());


             */
            return sb.toString();

        }
        return null;
    }
}

