package com.backend;
import java.util.ArrayList;

public class Player
{
    private String name;
    private int age;

    private ArrayList<Stone> rowBlue = new ArrayList<>();
    private ArrayList<Stone> rowYellow = new ArrayList<>();
    private ArrayList<Stone> rowGreen = new ArrayList<>();
    private ArrayList<Stone> rowPink = new ArrayList<>();
    private ArrayList<Stone> rowBrown = new ArrayList<>();
    private int score;

    /**
     * Player that takes part in the game
     * @param _name of the player
     * @param _age of the player, determines starting order
     */
    public Player(String _name, int _age){
        this.name = _name;
        this.age = _age;
    }

    /**
     * Function that printed out the player rows before we had a UI
     */
    public void displayRows(){
        if(rowBlue.size() > 0){
            System.out.println("Blue Row: ");
            for (Stone x : rowBlue) {
                x.displayStone();
            }
            System.out.println("__________________________________________");
        }
        if(rowYellow.size() > 0){
            System.out.println("Yellow Row: ");
            for (Stone x : rowYellow) {
                x.displayStone();
            }
            System.out.println("__________________________________________");
        }
        if(rowGreen.size() > 0){
            System.out.println("Green Row: ");
            for (Stone x : rowGreen) {
                x.displayStone();
            }
            System.out.println("__________________________________________");

        }
        if(rowPink.size() > 0){
            System.out.println("Pink Row: ");
            for (Stone x : rowPink) {
                x.displayStone();
            }
            System.out.println("__________________________________________");

        }
        if(rowBrown.size() > 0) {
            System.out.println("Brown Row: ");
            for (Stone x : rowBrown) {
                x.displayStone();
            }
            System.out.println("__________________________________________");
        }
    }

    /**
     * Uncovers a stone from the pile
     *
     * @param _stone the pulled stone
     * @return whether the move was executed
     */
    public boolean pull(Stone _stone)
    {
        System.out.println(_stone.getColor());
        switch (_stone.getColor())
        {
            //logical checks to see if move is legal

            case "g":
                if (rowGreen.size() == 0 || rowGreen.size() == 1)
                {
                    rowGreen.add(_stone);
                    return true;
                }
                if(rowGreen.size() >= 2)
                {
                    if((rowGreen.get(0).getNumber() < rowGreen.get(1).getNumber()) && _stone.getNumber() > rowGreen.get(rowGreen.size() - 1).getNumber())
                    {
                        rowGreen.add(_stone);
                        return true;
                    }
                    else if((rowGreen.get(0).getNumber() > rowGreen.get(1).getNumber()) && _stone.getNumber() < rowGreen.get(rowGreen.size() - 1).getNumber())
                    {
                        rowGreen.add(_stone);
                        return true;
                    }else
                    {
                            System.out.println("Error");
                            System.out.println("Stone is not placeable");
                            return false;
                    }
                }
                break;
            case "br":
                if (rowBrown.size() == 0 || rowBrown.size() == 1)
                {
                    rowBrown.add(_stone);
                    return true;
                }
                if(rowBrown.size() >= 2)
                {
                    if((rowBrown.get(0).getNumber() < rowBrown.get(1).getNumber()) && _stone.getNumber() > rowBrown.get(rowBrown.size() - 1).getNumber())
                    {
                        rowBrown.add(_stone);
                        return true;
                    }
                    else if((rowBrown.get(0).getNumber() > rowBrown.get(1).getNumber()) && _stone.getNumber() < rowBrown.get(rowBrown.size() - 1).getNumber())
                    {
                        rowBrown.add(_stone);
                        return true;
                    }else
                    {
                        System.out.println("Error");
                        System.out.println("Stone is not placeable");
                        return false;
                    }
                }
                break;
            case "p":
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
            case "y":
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
            case "bl":
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

    /**
     * Calculates the points for a player
     * @return points
     */
    public void calcPoints (){

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
        for (int i = 0; i < rowGreen.size(); i++) {
            if ( rowGreen.size() > 0) {
                bonusPoints += rowGreen.get(i).getBonusPoints();
                if (rowGreen.get(i).getWishingStone() == true){
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
        for (int i = 0; i < rowBrown.size(); i++) {
            if ( rowBrown.size() > 0) {
                bonusPoints += rowBrown.get(i).getBonusPoints();
                if (rowBrown.get(i).getWishingStone() == true){
                    wishingStone++;
                }
            }
        }

        //Points for row length
        points = getPointsOfRow(rowBlue, points);

        points = getPointsOfRow(rowYellow, points);

        points = getPointsOfRow(rowGreen, points);

        points = getPointsOfRow(rowPink, points);

        points = getPointsOfRow(rowBrown, points);


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

        this.score = points;
    }

    /**
     * Gets the points of a given row
     * @param rowColor Color of the specified row
     * @param points Amount of points for the specified row
     * @return points
     */
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

    /**
     * Getter and Setter methods
     *
     */
    public void setName(String _name){this.name = _name;}
    public String getName(){return this.name;}
    public void setAge(int _age){this.age = _age;}
    public int getAge(){return  this.age;}
    public int getScore(){return this.score;}
    public ArrayList<Stone> getRowBlue() {return this.rowBlue;}
    public void setRowBlue(ArrayList<Stone> rowBlue) {this.rowBlue = rowBlue;}
    public ArrayList<Stone> getRowBrown() {return this.rowBrown;}
    public void setRowBrown(ArrayList<Stone> rowBrown) {
        this.rowBrown = rowBlue;
    }
    public ArrayList<Stone> getRowYellow() {return this.rowYellow;}
    public void setRowYellow(ArrayList<Stone> rowYellow) {
        this.rowYellow = rowYellow;
    }
    public ArrayList<Stone> getRowGreen() {return this.rowGreen;}
    public void setRowGreen(ArrayList<Stone> rowPink) {
        this.rowPink = rowPink;
    }
    public ArrayList<Stone> getRowPink() {return this.rowPink;}
    public void setRowPink(ArrayList<Stone> rowPink) {
        this.rowPink = rowPink;
    }
}
