package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.Assert;
import org.junit.Test;

public class AnswerTest {

    @Test
    public void testIsWin() {
        Game aGame = new Game();
        aGame.add("Jone");

        int currentPlayer = 0;
        aGame.mGameRule.setpurse(currentPlayer, 5);
//        aGame.mGameRule.purses[currentPlayer] = 5;
//        aGame.inPenaltyBox[currentPlayer] = false;
        aGame.mGameRule.setPenaltyBoxStatu(currentPlayer, false);

        boolean isWin = aGame.wasCorrectlyAnswered();

        Assert.assertEquals(true, isWin);
    }

    @Test
    public void testCorrectlyAnswerButNoGetOut() {
        Game aGame = new Game();
        aGame.add("Jone");

        int currentPlayer = 0;
//        aGame.mGameRule.purses[currentPlayer] = 5;
        aGame.mGameRule.setpurse(currentPlayer, 5);
//        aGame.inPenaltyBox[currentPlayer] = true;
        aGame.mGameRule.setPenaltyBoxStatu(currentPlayer, true);
        aGame.isGettingOutOfPenaltyBox = false;

        aGame.wasCorrectlyAnswered();

        Assert.assertEquals(5, aGame.mGameRule.getpurse(currentPlayer));
    }

    @Test
    public void testCorrectlyAnswerAndOutofPenalty() {
        Game aGame = new Game();
        aGame.add("Jone");

        int currentPlayer = 0;
//        aGame.mGameRule.purses[currentPlayer] = 4;
        aGame.mGameRule.setpurse(currentPlayer, 4);
//        aGame.inPenaltyBox[currentPlayer] = true;
        aGame.mGameRule.setPenaltyBoxStatu(currentPlayer, true);
        aGame.isGettingOutOfPenaltyBox = true;

        aGame.wasCorrectlyAnswered();

        Assert.assertEquals(5, aGame.mGameRule.getpurse(currentPlayer));
    }


}
