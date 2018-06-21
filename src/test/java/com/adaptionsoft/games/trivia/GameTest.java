package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.Assert;
import org.junit.Test;

public class GameTest {

    @Test
    public void testPlayerMove() {
        Game aGame = new Game();
        aGame.add("Jone");

        int currentPlayer = 0;
        aGame.currentPlayer = currentPlayer;
        aGame.mGameRule.setPlace(currentPlayer, 1);
//        aGame.places[currentPlayer] = 1;
//        aGame.inPenaltyBox[currentPlayer] = false;
        aGame.mGameRule.setPenaltyBoxStatu(currentPlayer, false);
        aGame.roll(3);

        Assert.assertEquals(4, aGame.mGameRule.getplace(currentPlayer));
    }

    @Test
    public void testOverMaxPlace() {
        Game aGame = new Game();
        aGame.add("Jone");

        int currentPlayer = 0;
        aGame.mGameRule.setPlace(currentPlayer, 7);
//        aGame.places[currentPlayer] = 7;
//        aGame.inPenaltyBox[currentPlayer] = false;
        aGame.mGameRule.setPenaltyBoxStatu(currentPlayer, false);
        aGame.roll(6);
        Assert.assertEquals(1, aGame.mGameRule.getplace(currentPlayer));
    }

    @Test
    public void testNotGettingOutOfPenalty() {
        Game aGame = new Game();
        aGame.add("Jone");

        int currentPlayer = 0;
        aGame.currentPlayer = currentPlayer;
//        aGame.inPenaltyBox[currentPlayer] = true;
        aGame.mGameRule.setPenaltyBoxStatu(currentPlayer, true);
        aGame.roll(2);

        Assert.assertEquals(false, aGame.isGettingOutOfPenaltyBox);
    }

    @Test
    public void testOutOfPenalty() {
        Game aGame = new Game();
        aGame.add("Jone");

        int currentPlayer = 0;
        aGame.currentPlayer = currentPlayer;
//        aGame.places[currentPlayer] = 1;
        aGame.mGameRule.setPlace(currentPlayer, 1);
//        aGame.inPenaltyBox[currentPlayer] = true;
        aGame.mGameRule.setPenaltyBoxStatu(currentPlayer, true);
        aGame.roll(1);

        Assert.assertEquals(2, aGame.mGameRule.getplace(currentPlayer));
    }

}
