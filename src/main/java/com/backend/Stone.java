package com.backend;

public class Stone
{
    final private int number;
    final private char color;
    final private int bonusPoints;
    final private boolean wishingStone;

    final private boolean clover;
    private boolean status;

    public Stone(int _number, char _color, int _bonusPoints, boolean _wishingStone, boolean _clover, boolean _status){
        this.number = _number;
        this.color = _color;
        this.bonusPoints = _bonusPoints;
        this.wishingStone = _wishingStone;
        this.clover = _clover;
        this.status = _status;

    }
    private Stone(){
        this.number = 1;
        this.color = 'r';
        this.bonusPoints = 0;
        this.wishingStone = false;
        this.clover = false;
        this.status = true;

    }
    public void setStatus(boolean _status){
        this.status = _status;
    }

    public char getColor(){ return this.color;}

    public int getBonusPoints(){return this.bonusPoints;}
    public boolean getWishingStone(){return this.wishingStone;}
    public boolean isClover() {return this.clover;}



    public static void main(String[] args) {
        Stone one = new Stone();
        System.out.println(one.color);


    }

}
