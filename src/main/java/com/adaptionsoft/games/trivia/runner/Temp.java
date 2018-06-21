package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.Game;

import java.util.Random;

public class Temp {
    private static boolean notAWinner;

    public static void main(String[] args) {
        Game aGame = new Game();

        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");

        playGame(aGame);
    }

    public static void playGame(Game aGame) {
        Random rand = new Random();
        do {
            int diceNumber = rand.nextInt(5) + 1;
            aGame.roll(diceNumber);

            boolean condition = (rand.nextInt(9) == 7);
            if (condition) {
                notAWinner = aGame.wrongAnswer();
            } else {
                notAWinner = aGame.wasCorrectlyAnswered();
            }
        } while (notAWinner);
    }
}
