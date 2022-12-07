package com.backend;

public class Stone
{
    private int number;
    private char color;
    private int bonusPoints;
    private boolean wishingStone;
    private boolean clover;
    private boolean status;

    private static Stone stone(int _number, char _color, int _bonusPoints, boolean _wishingStone, boolean _clover, boolean _status){
        this.number = _number;
        this.color = _color;
        this.bonusPoints = _bonusPoints;
        this.wishingStone = _wishingStone;
        this.clover = _clover;
        this.status = _status;
    }
    private static Stone stone(){
        this.number = 1;
        this.color = 'r';
        this.bonusPoints = 0;
        this.wishingStone = false;
        this.clover = false;
        this.status = true;
    }
    protected static setStatus(boolean _status){
        this.status = _status;
    }

    public static void main(String[] args) {
        Stone one = new Stone();
        System.out.println(one);


    }

}
