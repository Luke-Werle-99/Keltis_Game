package com.backend;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;


public class Game {

    public Stone[][] Player1 = new Stone[5][11];
    public Stone [][] Player2 = new Stone[5][11];
    public Stone [][] Player3 = new Stone[5][11];
    public Stone [][] Player4 = new Stone[5][11];
    public ArrayList<Player> Players = new ArrayList<>(4);
    public static ArrayList<Stone> Stones = new ArrayList(55);
    //private static ArrayList<Stone> UncoveredStones = new ArrayList<>(55);
    public Game() {
        this.Players = new ArrayList<>(4);
        this.Stones = new ArrayList<>(55);
        this.UncoveredStones = new ArrayList<>(55);
    }

    public void gameStart() {
       /* //commandline as temporary interface input
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

        */
    }

    public void gameEnd() {
        System.out.println("GAME OVER !");

        ArrayList<Integer> scores = new ArrayList<Integer>();

        for (int i = 0; i < Players.size(); i++) {
            scores.add(Players.get(i).getScore());
        }

        Collections.sort(scores, Collections.reverseOrder());

        for (int i = 1; i <= Players.size(); i++) {
            String name = "";
            for (Player x : Players) {
                if (x.getScore() == scores.get(i - 1)) {
                    name = x.getName();
                }
            }
            System.out.println(i + ". place is " + name + " with " + scores.get(i - 1) + " points !!!");
        }
    }
    public void addPlayer(Player x){
        Players.add(x);
    }

    public void generateStones() {
        StringBuilder sb = new StringBuilder();
        //initialize pink Stones
        for (int i = 0; i < 11; i++) {
            Stone stein = new Stone();
            stein.setNumber(i);
            stein.setColor("p");
            sb.append("pink");
            sb.append(i);
            stein.setID(sb.toString());
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
            sb.setLength(0);
        }
        //initialize blue Stones
        for (int i = 0; i < 11; i++) {
            Stone stein = new Stone();
            stein.setNumber(i);
            stein.setColor("bl");
            sb.append("blau");
            sb.append(i);
            stein.setID(sb.toString());
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
            sb.setLength(0);
        }
        //initialize brown Stones
        for (int i = 0; i < 11; i++) {
            Stone stein = new Stone();
            stein.setNumber(i);
            stein.setColor("br");
            sb.append("braun");
            sb.append(i);
            stein.setID(sb.toString());
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
            sb.setLength(0);
        }
        //initialize grün Stones
        for (int i = 0; i < 11; i++) {
            Stone stein = new Stone();
            stein.setNumber(i);
            stein.setColor("g");
            sb.append("gruen");
            sb.append(i);
            stein.setID(sb.toString());
            if (i == 7 || i == 4) {
                stein.setWishingStone(true);
            } else if (i == 5 || i == 8) {
                stein.setClover(true);
            } else if (i == 2) {
                stein.setBonusPoints(3);
            } else if (i == 6) {
                stein.setBonusPoints(1);
            }sb.setLength(0);

            Stones.add(stein);
            sb.setLength(0);
        }
        //initialize yellow Stones
        for (int i = 0; i < 11; i++) {
            Stone stein = new Stone();
            stein.setNumber(i);
            stein.setColor("y");
            sb.append("gelb");
            sb.append(i);
            stein.setID(sb.toString());
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
            sb.setLength(0);
        }
        Collections.shuffle(Stones);
    }
/*
    public static void main(String[] args) {
        Game g = new Game();
        g.generateStones();
        g.gameStart();
        g.findOldestPlayer();
        int turn = 0;
        while(g.Stones.size() > 0){
            if(turn >= g.Players.size()){turn = 0;}
            Player currentPlayer = g.Players.get(turn);
            //Stone currentStone = Stones.get(0);
            System.out.println(currentPlayer.getName() + ", you have following rows: ");
            currentPlayer.displayRows();
            newOrUncovered(currentPlayer);
            turn++;
            currentPlayer.calcPoints();
            System.out.println(currentPlayer.getName() + ":  " + currentPlayer.getScore());
        }
    }


 */
    public static void newOrUncovered(Player currentPlayer) {
        /*
        String input2;
        System.out.println(currentPlayer.getName() + " would you like to draw a new stone?");

        input2 = scanner.nextLine().toLowerCase();

        if(input2.equals("yes")){
            drawNewStone(currentPlayer);
        } else if (input2.equals("no")) {
            //check if there are any uncovered stones to choose from
            if(UncoveredStones.size() > 0){
                drawUncoveredStone(currentPlayer);
            }else{
                System.out.println("There are no uncovered stones. You have to choose a new stone");
                drawNewStone(currentPlayer);
            }

        }else{
            System.out.println("Error! Please choose a valid input");
            newOrUncovered(currentPlayer);
        }


         */
    }

    public static void drawNewStone(Player currentPlayer) {
/*
        String input;
        Stone currentStone = Stones.get(0);
        System.out.println(currentPlayer.getName() + " your Stone has the following attributes");
        currentStone.displayStone();
        System.out.println(currentPlayer.getName() + " would you like to take the stone?");
        input = scanner.nextLine().toLowerCase();
        if(input.equals("yes")){
            currentPlayer.pull(currentStone);
            Stones.remove(0);
            if(currentStone.isClover()){
                System.out.println("You are allowed to draw another Stone");
                newOrUncovered(currentPlayer);
            }
        } else if (input.equals("no")) {
            UncoveredStones.add(currentStone);
            Stones.remove(currentStone);
        }else{
            System.out.println("Error! Please enter a valid input");
            drawNewStone(currentPlayer);
        }

    }
    public static void drawUncoveredStone(Player currentPlayer){
        System.out.println("The following stones are uncovered:");
        int index = 0;
        for (Stone x : UncoveredStones) {
            System.out.println("Index: " + index);
            x.displayStone();
            index++;
            System.out.println("___________________________");
        }
        int choice;
        System.out.println("Type the index of the desired stone, or -1 to draw a new stone: ");
        choice = scanner.nextInt();
        //check for valid input
        if(choice <= UncoveredStones.size()){
            currentPlayer.pull(UncoveredStones.get(choice));
        } else if (choice < 0) {
            drawNewStone(currentPlayer);
        } else{
            System.out.println("Error! Please enter a valid input.");
            drawUncoveredStone(currentPlayer);
        }


 */
    }
    public void findOldestPlayer() {
        Player oldest = null;
        int oldestAge = 0;
        for (Player x : Players) {
            if(x.getAge() > oldestAge){
                oldestAge = x.getAge();
                oldest = x;
            }
        }
        Player first = Players.get(0);
        int indexOldest = Players.indexOf(oldest);
        Players.set(0, oldest);
        Players.set(indexOldest, first);
    }
}
