package de.backend;
import java.util.ArrayList;
import Stein.java;

public class Player
{
    private String name;
    private int age;
    private ArrayList<String> rowBlue = new ArrayList<>();
    private ArrayList<String> rowYellow = new ArrayList<>();
    private ArrayList<String> rowRed = new ArrayList<>();
    private ArrayList<String> rowPink = new ArrayList<>();
    private ArrayList<String> rowOrange = new ArrayList<>();

    private static uncover(Stone _stone){
        _stone.setStatus = true;

    }

    private static pull(Stone _stone){
        switch _stone.color {
            case 'b':
                rowBlue.add(_stone);
                break;
            default:
                System.out.println("Error")
        }
        return stone;
    }

    private static points (rowBlue, rowYellow, rowRed, rowPink, rowOrange){
        int stoneAmount = rowBlue.size() + rowYellow.size() + rowRed.size() + rowPink.size() + rowOrange.size();
        int bonusPoints = 0;
        int wishstone = 0;
        //Counting Bonus points
        for (int i = 0; i < rowBlue.size(); i++) {
            if ( >0)
                bonusPoints += bonusPoints.rowBlue(i);
        }

        return points;
    }
}
