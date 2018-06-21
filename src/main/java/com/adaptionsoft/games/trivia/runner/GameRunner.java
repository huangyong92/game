
package com.adaptionsoft.games.trivia.runner;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;


public class GameRunner {
	public static void main(String[] args) {
		Game aGame = new Game();
		aGame.add("Chet");
		aGame.add("Pat");
		aGame.add("Sue");

		playGames(aGame);
	}

	public static void playGames(Game aGame) {
		Random rand = new Random(10);
		int dice = rand.nextInt(5) + 1;
		boolean isAWinner;
		do {
			aGame.roll(dice);
			isAWinner = isWin(aGame);
		} while (isAWinner);
	}

	public static boolean isWin(Game aGame) {
		Random rand = new Random(10);
		int wrongAnswer = 7;
		int answer = rand.nextInt(9);

		if (answer == wrongAnswer) {
			return aGame.wrongAnswer();
		} else {
			return aGame.wasCorrectlyAnswered();
		}
	}
}
