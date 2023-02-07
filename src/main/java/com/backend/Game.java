package com.backend;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;


public class Game {
    //A list which contains the initiated players
    public static ArrayList<Player> Players = new ArrayList<>(4);
    //A list to safe all generated stones
    public static ArrayList<Stone> Stones = new ArrayList(55);
    //constructor for a game instance with parameters
    public Game() {
        this.Players = new ArrayList<>(4);
        this.Stones = new ArrayList<>(55);
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
    //Method to add players to the Players-ArrayList
    public void addPlayer(Player x){
        Players.add(x);
    }

    /**
     * Generates our Stones. As they are not random we hard assign values to each stone.
     * Shuffles Stones.
     */
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
            } else if (i == 8 || i == 5) {
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
            } else if (i == 3 || i == 4) {
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

    /**
     * Finds the oldest player to determine start player.
     */
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
