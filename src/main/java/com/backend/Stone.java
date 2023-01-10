package com.backend;

public class Stone
{
    private  int number;
    private  char color;
    private  int bonusPoints;
    private  boolean wishingStone;
    private  boolean clover;
    //private boolean status;



    public Stone(int _number, char _color, int _bonusPoints, boolean _wishingStone, boolean _clover, boolean _status){
        this.number = _number;
        this.color = _color;
        this.bonusPoints = _bonusPoints;
        this.wishingStone = _wishingStone;
        this.clover = _clover;
        //this.status = _status;

    }
    public Stone(){
        this.number = 1;
        this.color = 'r';
        this.bonusPoints = 0;
        this.wishingStone = false;
        this.clover = false;
        //this.status = true;

    }
    public void displayStone() {
    System.out.println("Color:  " + this.color);
    System.out.println("Number:  " + this.number);
    System.out.println("Bonus Points:  " + this.bonusPoints );
    if(this.clover == true){
        System.out.println("Has a clover");
    }else {
        System.out.println("Has NO a clover");
    }
    if(this.wishingStone == true){
        System.out.println("Has a wishing Stone");
    }else {
        System.out.println("Has NO a wishing Stone");
    }

}
    public int getNumber() { return number;}
    public void setNumber(int _number){ this.number = _number;}
    public char getColor(){ return this.color;}
    public void setColor(char _color){ this.color = _color;}
    public int getBonusPoints(){return this.bonusPoints;}
    public void setBonusPoints(int _bonusPoints){this.bonusPoints = _bonusPoints;}
    public boolean getWishingStone(){return this.wishingStone;}
    public void setWishingStone(boolean _wishingStone){this.wishingStone = _wishingStone;}
    public boolean isClover() {return this.clover;}
    public void setClover(boolean _clover){this.clover = _clover;}
    //public void setStatus(boolean _status){this.status = _status;}
    //public boolean getStatus(){return this.status;}







}
