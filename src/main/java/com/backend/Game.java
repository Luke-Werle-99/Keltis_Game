package com.backend;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;


public class Game
{
    private ArrayList<Player> Players = null;
    private ArrayList<Stone> Stones = new ArrayList(55);
    private ArrayList<Stone> UncoveredStones = new ArrayList<>(55);


    private void gameStart()
    {

    }

    private void gameEnd()
    {
        System.out.println("GAME OVER !");

        ArrayList<Integer> scores = new ArrayList<Integer>();

        for(int i=0 ; i < Players.size() ; i++)
        {
            scores.add(Players.get(i).calcPoints());
        }

        Collections.sort(scores,Collections.reverseOrder());

        for(int i=1 ; i <= Players.size() ; i++)
        {
            String name = "";
            for (Player x:Players)
            {
                if (x.getScore() == scores.get(i-1))
                {
                    name = x.getName();
                }
            }

            System.out.println( i + ". place is " + name + "with" + scores.get(i-1) + "points !!!");
        }
    }

    public void generateStones() {
        //initialize red Stones
        for(int i = 0; i < 11; i++){
            Stone stein = new Stone();
            stein.setNumber(i);
            stein.setColor('r');
            if(i == 1|| i == 4){
                stein.setWishingStone(true);
            } else if (i == 2 || i == 9) {
                stein.setClover(true);
            } else if (i == 5 ) {
                stein.setBonusPoints(1);
            }else if (i == 7 ) {
                stein.setBonusPoints(3);
            }
            Stones.add(stein);
        }
        //initialize blue Stones
        for(int i = 0; i < 11; i++){
            Stone stein = new Stone();
            stein.setNumber(i);
            stein.setColor('b');
            if(i == 2|| i == 3){
                stein.setWishingStone(true);
            } else if (i == 5 || i == 6) {
                stein.setClover(true);
            } else if (i == 8 ) {
                stein.setBonusPoints(3);
            }else if (i == 9) {
                stein.setBonusPoints(1);
            }
            Stones.add(stein);
        }
        //initialize pink Stones
        for(int i = 0; i < 11; i++){
            Stone stein = new Stone();
            stein.setNumber(i);
            stein.setColor('p');
            if(i == 6|| i == 9){
                stein.setWishingStone(true);
            } else if (i == 3 || i == 4) {
                stein.setClover(true);
            } else if (i == 1 ) {
                stein.setBonusPoints(1);
            }else if (i == 5 ) {
                stein.setBonusPoints(3);
            }
            Stones.add(stein);
        }
        //initialize orange Stones
        for(int i = 0; i < 11; i++){
            Stone stein = new Stone();
            stein.setNumber(i);
            stein.setColor('o');
            if(i == 7|| i == 4){
                stein.setWishingStone(true);
            } else if (i == 5 || i == 8) {
                stein.setClover(true);
            } else if (i == 2 ) {
                stein.setBonusPoints(3);
            }else if (i == 6 ) {
                stein.setBonusPoints(1);
            }
            Stones.add(stein);
        }
        //initialize yellow Stones
        for(int i = 0; i < 11; i++){
            Stone stein = new Stone();
            stein.setNumber(i);
            stein.setColor('y');
            if(i == 6|| i == 8){
                stein.setWishingStone(true);
            } else if (i == 1 || i == 7) {
                stein.setClover(true);
            } else if (i == 3 ) {
                stein.setBonusPoints(3);
            }else if (i == 4 ) {
                stein.setBonusPoints(1);
            }
            Stones.add(stein);
        }
        //display the generated Stones for debugging
        /*for (Stone x:Stones ) {
            System.out.println(x.getNumber());
        }

        */
        Collections.shuffle(Stones); // uses java.collections

    }
    public void addPlayers(ArrayList<Player> _players) {
       Players = _players;
    }

    public Stone uncoverStone(){
        UncoveredStones.add(0, Stones.get(0));
        Stones.remove(0);
        return UncoveredStones.get(0);
    }
    private void playerTurn(Player _player, Stone _stone){
        if (_stone != null){
            UncoveredStones.remove(_stone);
        }

        //_stone.setStatus(true);
    }

    public ArrayList<Stone> getUncoveredStones(){return UncoveredStones;}

    public static void main(String[] args) {
        ArrayList<Player> Players = new ArrayList(4);
        Game g = new Game();
        g.generateStones();

            //commandline as temporary interface input
        Scanner scanner = new Scanner(System.in);
        int amount = 0;
        System.out.println("Please enter the amount of players: ");
        amount = scanner.nextInt();
        scanner.nextLine();
        //create Players from commandline input
        for (int i = 0; i < amount; i++) {
            System.out.println("Please enter the name and age of the players");
            String input = scanner.nextLine();
            String[] split = input.split(" ");
            Player player = new Player(split[0], Integer.parseInt(split[1]));
            Players.add(player);
        }
        scanner.close();
        //print input for debugging
        for (Player x: Players) {
            System.out.println(x.getName() + " " + x.getAge());
        }
        g.addPlayers(Players);
    }
}
