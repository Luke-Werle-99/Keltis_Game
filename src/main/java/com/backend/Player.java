package com.backend;
import java.util.ArrayList;


public class Player
{
    private String name;
    private int age;
    private ArrayList<Stone> rowBlue = new ArrayList<>();
    private ArrayList<Stone> rowYellow = new ArrayList<>();
    private ArrayList<Stone> rowRed = new ArrayList<>();
    private ArrayList<Stone> rowPink = new ArrayList<>();
    private ArrayList<Stone> rowOrange = new ArrayList<>();

    private void uncover(Stone _stone){
        _stone.setStatus(true);

    }

    private void pull(Stone _stone){
        switch (_stone.getColor()) {
            case 'b':
                rowBlue.add(_stone);
                break;
            default:
                System.out.println("Error");
        }

    }

    public int  calcpoints (){
        int stoneAmount = rowBlue.size() + rowYellow.size() + rowRed.size() + rowPink.size() + rowOrange.size();
        int bonusPoints = 0;
        int wishingStone = 0;
        int points = 0;

        //Counting Bonus points and wishing stones
        for (int i = 0; i < rowBlue.size(); i++) {
            if ( rowBlue.size() > 0) {
                bonusPoints += rowBlue.get(i).getBonusPoints();
                if (rowBlue.get(i).getWishingStone() == true){
                    wishingStone++;
                }
            }
        }
        for (int i = 0; i < rowYellow.size(); i++) {
            if ( rowYellow.size() > 0) {
                bonusPoints += rowYellow.get(i).getBonusPoints();
                if (rowBlue.get(i).getWishingStone() == true){
                wishingStone++;
                }
            }
        }
        for (int i = 0; i < rowRed.size(); i++) {
            if ( rowRed.size() > 0) {
                bonusPoints += rowRed.get(i).getBonusPoints();
                if (rowBlue.get(i).getWishingStone() == true){
                wishingStone++;
                }
            }
        }
        for (int i = 0; i < rowPink.size(); i++) {
            if ( rowPink.size() > 0) {
                bonusPoints += rowPink.get(i).getBonusPoints();
                if (rowBlue.get(i).getWishingStone() == true){
                    wishingStone++;
                }
            }
        }
        for (int i = 0; i < rowOrange.size(); i++) {
            if ( rowOrange.size() > 0) {
                bonusPoints += rowOrange.get(i).getBonusPoints();
                if (rowBlue.get(i).getWishingStone() == true){
                    wishingStone++;
                }
            }
        }

        //Points for row length
        points = getPointsOfRow(rowBlue, points);

        points = getPointsOfRow(rowYellow, points);

        points = getPointsOfRow(rowRed, points);

        points = getPointsOfRow(rowPink, points);

        points = getPointsOfRow(rowOrange, points);


        //Points for Wishing stones
        switch(wishingStone){
            case 0:
                points -= 4;
                break;
            case 1:
                points -= 3;
                break;
            case 2:
                points += 2;
                break;
            case 3:
                points += 3;
                break;
            case 4:
                points += 6;
                break;
            default:
                points += 10;
        }
        //Points for Bonus points
        points += bonusPoints;

        return points;
    }

    private int getPointsOfRow(ArrayList<Stone> rowColor, int points) {
        switch (rowColor.size()) {
            case 0:
                break;
            case 1:
                points -= 4;
                break;
            case 2:
                points -= 3;
                break;
            case 3:
                points += 2;
                break;
            case 4:
                points += 3;
                break;
            case 5:
                points += 6;
                break;
            default:
                points += 10;       // Lengths 6+ give same amount of points
        }
        return points;
    }
}
