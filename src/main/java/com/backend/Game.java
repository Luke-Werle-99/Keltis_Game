package com.backend;
import java.util.ArrayList;
import java.util.Scanner;

public class Game
{
    private static ArrayList<Player> Players = new ArrayList(4);
    private static ArrayList<Stone> Stones = new ArrayList(55);


    private void gameStart()
    {

    }

    private void gameEnd()
    {

    }
    public static void generateStones() {
        //initialize red Stones
        for(int i = 0; i < 11; i++){
            Stone stein = new Stone();
            stein.setNumber(i);
            stein.setColor('r');
            Stones.add(stein);
        }
        //initialize blue Stones
        for(int i = 0; i < 11; i++){
            Stone stein = new Stone();
            stein.setNumber(i);
            stein.setColor('b');
            Stones.add(stein);
        }
        //initialize pink Stones
        for(int i = 0; i < 11; i++){
            Stone stein = new Stone();
            stein.setNumber(i);
            stein.setColor('p');
            Stones.add(stein);
        }
        //initialize orange Stones
        for(int i = 0; i < 11; i++){
            Stone stein = new Stone();
            stein.setNumber(i);
            stein.setColor('o');
            Stones.add(stein);
        }
        //initialize yellow Stones
        for(int i = 0; i < 11; i++){
            Stone stein = new Stone();
            stein.setNumber(i);
            stein.setColor('y');
            Stones.add(stein);
        }
        //display the generated Stones for debugging
        /*for (Stone x:Stones ) {
            System.out.println(x.getNumber());
        }

        */
    }
    public static void addPlayers() {
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
    }

    public static void main(String[] args) {
        generateStones();
        addPlayers();
    }

}