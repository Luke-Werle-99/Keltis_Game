package com.frontend.keltis;
import com.backend.Player;
import com.backend.Game;
import javafx.fxml.FXML;
import com.backend.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Collections;
import com.backend.*;
import javafx.scene.layout.Pane;

public class EndScreenController
{

    @FXML
    public Label endLabel1;
    @FXML
    public Label endLabel2;
    @FXML
    public Label endLabel3;
    @FXML
    public Label endLabel4;
    public void initialize()
    {
        endLabel1.setVisible(false);
        endLabel2.setVisible(false);
        endLabel3.setVisible(false);
        endLabel4.setVisible(false);
    }
    public void gameEnd(ArrayList<Player> Players)
    {
        System.out.println("GAME OVER !");
        ArrayList<Integer> scores = new ArrayList<Integer>();
        ArrayList<Label> labels = new ArrayList<Label>();

        labels.add(endLabel1);
        labels.add(endLabel2);
        labels.add(endLabel3);
        labels.add(endLabel4);

        for (int i = 0; i < Players.size(); i++) {
            Player currentPlayer = Players.get(i);
            scores.add(currentPlayer.getScore());
        }

        Collections.sort(scores, Collections.reverseOrder());

        StringBuilder stringBuilder1 = new StringBuilder();

        for (int i = 1; i <= Players.size(); i++)
        {
            String name = "";

            for (Player x : Players)
            {
                if (x.getScore() == scores.get(i - 1))
                {
                    name = x.getName();
                }
            }

            stringBuilder1.append(i);
            stringBuilder1.append(". place is ");
            stringBuilder1.append(name);
            stringBuilder1.append(" with ");
            stringBuilder1.append(scores.get(i - 1));
            stringBuilder1.append(" points !!!");

           // endLabel1=stringBuilder1.toString();

            labels.get(i-1).setText(i + ". place is " + name + " with " + scores.get(i - 1) + " points !!!");
            labels.get(i-1).setVisible(true);

        }

    }

}

