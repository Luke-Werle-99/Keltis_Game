package com.backend;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;


public class Game {
    private ArrayList<Player> Players = new ArrayList<>(4);
    private static ArrayList<Stone> Stones = new ArrayList(55);
    private static ArrayList<Stone> UncoveredStones = new ArrayList<>(55);
    public static Scanner scanner = new Scanner(System.in);

    private void gameStart() {
        //commandline as temporary interface input
        int amount = 0;
        System.out.println("Please enter the amount of players: ");
        amount = scanner.nextInt();
        scanner.nextLine();
        //create Players from commandline input
        for (int i = 0; i < amount; i++) {
            System.out.println("Please enter the name and age of Player " + (i+1));
            String input = scanner.nextLine();
            String[] split = input.split(" ");
            Player player = new Player(split[0], Integer.parseInt(split[1]));
            Players.add(player);
        }
    }

    private void gameEnd() {
        System.out.println("GAME OVER !");

        ArrayList<Integer> scores = new ArrayList<Integer>();

        for (int i = 0; i < Players.size(); i++) {
            scores.add(Players.get(i).calcPoints());
        }

        Collections.sort(scores, Collections.reverseOrder());

        for (int i = 1; i <= Players.size(); i++) {
            String name = "";
            for (Player x : Players) {
                if (x.getScore() == scores.get(i - 1)) {
                    name = x.getName();
                }
            }
            System.out.println(i + ". place is " + name + "with" + scores.get(i - 1) + "points !!!");
        }
    }

    public void generateStones() {
        //initialize red Stones
        for (int i = 0; i < 11; i++) {
            Stone stein = new Stone();
            stein.setNumber(i);
            stein.setColor('r');
            if (i == 1 || i == 4) {
                stein.setWishingStone(true);
            } else if (i == 2 || i == 9) {
                stein.setClover(true);
            } else if (i == 5) {
                stein.setBonusPoints(1);
            } else if (i == 7) {
                stein.setBonusPoints(3);
            }
            Stones.add(stein);
        }
        //initialize blue Stones
        for (int i = 0; i < 11; i++) {
            Stone stein = new Stone();
            stein.setNumber(i);
            stein.setColor('b');
            if (i == 2 || i == 3) {
                stein.setWishingStone(true);
            } else if (i == 5 || i == 6) {
                stein.setClover(true);
            } else if (i == 8) {
                stein.setBonusPoints(3);
            } else if (i == 9) {
                stein.setBonusPoints(1);
            }
            Stones.add(stein);
        }
        //initialize pink Stones
        for (int i = 0; i < 11; i++) {
            Stone stein = new Stone();
            stein.setNumber(i);
            stein.setColor('p');
            if (i == 6 || i == 9) {
                stein.setWishingStone(true);
            } else if (i == 3 || i == 4) {
                stein.setClover(true);
            } else if (i == 1) {
                stein.setBonusPoints(1);
            } else if (i == 5) {
                stein.setBonusPoints(3);
            }
            Stones.add(stein);
        }
        //initialize orange Stones
        for (int i = 0; i < 11; i++) {
            Stone stein = new Stone();
            stein.setNumber(i);
            stein.setColor('o');
            if (i == 7 || i == 4) {
                stein.setWishingStone(true);
            } else if (i == 5 || i == 8) {
                stein.setClover(true);
            } else if (i == 2) {
                stein.setBonusPoints(3);
            } else if (i == 6) {
                stein.setBonusPoints(1);
            }
            Stones.add(stein);
        }
        //initialize yellow Stones
        for (int i = 0; i < 11; i++) {
            Stone stein = new Stone();
            stein.setNumber(i);
            stein.setColor('y');
            if (i == 6 || i == 8) {
                stein.setWishingStone(true);
            } else if (i == 1 || i == 7) {
                stein.setClover(true);
            } else if (i == 3) {
                stein.setBonusPoints(3);
            } else if (i == 4) {
                stein.setBonusPoints(1);
            }
            Stones.add(stein);
        }
        Collections.shuffle(Stones);
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.generateStones();
        g.gameStart();
        g.findOldestPlayer();
        int turn = 0;
        while(Stones.size() > 0){
            if(turn > g.Players.size()){turn = 0;}
            Player currentPlayer = g.Players.get(turn);
            //Stone currentStone = Stones.get(0);
            newOrUncovered(currentPlayer);
            turn++;
            currentPlayer.calcPoints();
            System.out.println(currentPlayer.getName() + ":  " + currentPlayer.getScore());
        }
    }

    private static void newOrUncovered(Player currentPlayer) {

        int input2;
        System.out.println(currentPlayer.getName() + " would you like to draw a new stone? Yes: 1  No: 2");

        input2 = scanner.nextInt();

        if(input2 == 1){
            drawNewStone(currentPlayer);
        } else if (input2== 2) {
            System.out.println("Here is a list of the uncovered Stones: ");
            for (Stone x: UncoveredStones) {
                displayStone(x);
            }
        }else{
            System.out.println("Error! Please choose a valid input");
            newOrUncovered(currentPlayer);
        }

    }

    private static void drawNewStone(Player currentPlayer) {
        String input;
        Stone currentStone = Stones.get(0);
        System.out.println(currentPlayer.getName() + " your Stone has the following attributes");
        displayStone(currentStone);
        System.out.println(currentPlayer.getName() + " would you like to take the stone?");
        input = scanner.nextLine().toLowerCase();
        if(input.equals("yes")){
            currentPlayer.pull(currentStone);
            Stones.remove(0);
            if(currentStone.isClover()){
                System.out.println("You are allowed to draw another Stone");
                drawNewStone(currentPlayer);
            }
        } else if (input.equals("no")) {
            UncoveredStones.add(currentStone);
        }else{
            System.out.println("Error! Please enter a valid input");
            drawNewStone(currentPlayer);
        }

    }

    private static void displayStone(Stone currentStone) {
        System.out.println("Color:  " + currentStone.getColor());
        System.out.println("Number:  " + currentStone.getNumber());
        System.out.println("Bonus Points:  " + currentStone.getBonusPoints());
        if(currentStone.isClover()){
            System.out.println("Has a clover");
        }else {
            System.out.println("Has NO a clover");
        }
        if(currentStone.getWishingStone()){
            System.out.println("Has a wishing Stone");
        }else {
            System.out.println("Has NO a wishing Stone");
        }

    }

    private void findOldestPlayer() {
        Player oldest = null;
        int oldestAge = 0;
        for (Player x : Players) {
            if(x.getAge() > oldestAge){
                oldestAge = x.getAge();
                oldest = x;
            }
        }
        Player first = Players.get(0);
        Players.set(0, oldest);
        if(!Players.contains(first)){
            Players.add(first);
        }
    }
}
