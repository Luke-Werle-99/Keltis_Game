package com.frontend.keltis;
import com.backend.Player;
import com.backend.Game;
import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.Collections;
import com.backend.*;
import javafx.scene.layout.Pane;

public class EndScreenController
{

    @FXML
    public Pane namePane1;

    public static void gameEnd(ArrayList Players)
    {

        System.out.println("GAME OVER !");

        ArrayList<Integer> scores = new ArrayList<Integer>();

        for (int i = 0; i < Players.size(); i++) {
            Player currentPlayer = (Player) Players.get(i);
            scores.add(currentPlayer.getScore());
        }

        Collections.sort(scores, Collections.reverseOrder());
/*
        for (int i = 1; i <= Players.size(); i++) {
            String name = "";
            for (Player x : Players) {
                if (x.getScore() == scores.get(i - 1)) {
                    name = x.getName();
                }
            }
            System.out.println(i + ". place is " + name + " with " + scores.get(i - 1) + " points !!!");

        }
*/
    }

}

