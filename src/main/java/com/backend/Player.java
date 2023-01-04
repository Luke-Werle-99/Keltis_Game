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
    private int score;

    public Player(String _name, int _age){
        name = _name;
        age = _age;
    }

    public boolean pull(Stone _stone)
    {
        switch (_stone.getColor())
        {
            //logical checks to see if move is legal

            case 'r':
                if (rowRed.size() == 0 || rowRed.size() == 1)
                {
                    rowRed.add(_stone);
                    return true;
                }
                if(rowRed.size() >= 2)
                {
                    if((rowRed.get(0).getNumber() < rowRed.get(1).getNumber()) && _stone.getNumber() > rowRed.get(rowRed.size() - 1).getNumber())
                    {
                        rowRed.add(_stone);
                        return true;
                    }
                    else if((rowRed.get(0).getNumber() > rowRed.get(1).getNumber()) && _stone.getNumber() < rowRed.get(rowRed.size() - 1).getNumber())
                    {
                        rowRed.add(_stone);
                        return true;
                    }else
                    {
                            System.out.println("Error");
                            System.out.println("Stone is not placeable");
                            return false;
                    }
                }
                break;
            case 'o':
                if (rowOrange.size() == 0 || rowOrange.size() == 1)
                {
                    rowOrange.add(_stone);
                    return true;
                }
                if(rowOrange.size() >= 2)
                {
                    if((rowOrange.get(0).getNumber() < rowOrange.get(1).getNumber()) && _stone.getNumber() > rowOrange.get(rowOrange.size() - 1).getNumber())
                    {
                        rowOrange.add(_stone);
                        return true;
                    }
                    else if((rowOrange.get(0).getNumber() > rowOrange.get(1).getNumber()) && _stone.getNumber() < rowOrange.get(rowOrange.size() - 1).getNumber())
                    {
                        rowOrange.add(_stone);
                        return true;
                    }else
                    {
                        System.out.println("Error");
                        System.out.println("Stone is not placeable");
                        return false;
                    }
                }
                break;
            case 'p':
                if (rowPink.size() == 0 || rowPink.size() == 1)
                {
                    rowPink.add(_stone);
                    return true;
                }
                if(rowPink.size() >= 2)
                {
                    if((rowPink.get(0).getNumber() < rowPink.get(1).getNumber()) && _stone.getNumber() > rowPink.get(rowPink.size() - 1).getNumber())
                    {
                        rowPink.add(_stone);
                        return true;
                    }
                    else if((rowPink.get(0).getNumber() > rowPink.get(1).getNumber()) && _stone.getNumber() < rowPink.get(rowPink.size() - 1).getNumber())
                    {
                        rowPink.add(_stone);
                        return true;
                    }else
                    {
                        System.out.println("Error");
                        System.out.println("Stone is not placeable");
                        return false;
                    }
                }
                break;
            case 'y':
                if (rowYellow.size() == 0 || rowYellow.size() == 1)
                {
                    rowYellow.add(_stone);
                    return true;
                }
                if(rowYellow.size() >= 2)
                {
                    if((rowYellow.get(0).getNumber() < rowYellow.get(1).getNumber()) && _stone.getNumber() > rowYellow.get(rowYellow.size() - 1).getNumber())
                    {
                        rowYellow.add(_stone);
                        return true;
                    }
                    else if((rowYellow.get(0).getNumber() > rowYellow.get(1).getNumber()) && _stone.getNumber() < rowYellow.get(rowYellow.size() - 1).getNumber())
                    {
                        rowYellow.add(_stone);
                        return true;
                    }else
                    {
                        System.out.println("Error");
                        System.out.println("Stone is not placeable");
                        return false;
                    }
                }
                break;
            case 'b':
                if (rowBlue.size() == 0 || rowBlue.size() == 1)
                {
                    rowBlue.add(_stone);
                    return true;
                }
                if(rowBlue.size() >= 2)
                {
                    if((rowBlue.get(0).getNumber() < rowBlue.get(1).getNumber()) && _stone.getNumber() > rowBlue.get(rowBlue.size() - 1).getNumber())
                    {
                        rowBlue.add(_stone);
                        return true;
                    }
                    else if((rowBlue.get(0).getNumber() > rowBlue.get(1).getNumber()) && _stone.getNumber() < rowBlue.get(rowBlue.size() - 1).getNumber())
                    {
                        rowBlue.add(_stone);
                        return true;
                    }else
                    {
                        System.out.println("Error");
                        System.out.println("Stone is not placeable");
                        return false;
                    }
                }
                break;
        }
    return false;
    }

    public int  calcPoints (){
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
                if (rowYellow.get(i).getWishingStone() == true){
                wishingStone++;
                }
            }
        }
        for (int i = 0; i < rowRed.size(); i++) {
            if ( rowRed.size() > 0) {
                bonusPoints += rowRed.get(i).getBonusPoints();
                if (rowRed.get(i).getWishingStone() == true){
                wishingStone++;
                }
            }
        }
        for (int i = 0; i < rowPink.size(); i++) {
            if ( rowPink.size() > 0) {
                bonusPoints += rowPink.get(i).getBonusPoints();
                if (rowPink.get(i).getWishingStone() == true){
                    wishingStone++;
                }
            }
        }
        for (int i = 0; i < rowOrange.size(); i++) {
            if ( rowOrange.size() > 0) {
                bonusPoints += rowOrange.get(i).getBonusPoints();
                if (rowOrange.get(i).getWishingStone() == true){
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

    public int getPointsOfRow(ArrayList<Stone> rowColor, int points) {
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
    public void setName(String _name){this.name = _name;}
    public String getName(){return this.name;}
    public void setAge(int _age){this.age = _age;}
    public int getAge(){return  this.age;}
    public int getScore(){return this.score;}
}
