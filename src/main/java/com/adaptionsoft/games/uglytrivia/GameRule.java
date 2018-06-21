package com.adaptionsoft.games.uglytrivia;

import java.util.Arrays;
import java.util.LinkedList;

public class GameRule {

    private final Game mGame;
    private int mRollNumber;
    private int mCurrentPlayer = 0;
    private boolean mIsGettingOutOfPenaltyBox;

    private boolean[] mInPenaltyBox;
    private int[] mPlaces;
    private int[] mPurses;

    private LinkedList popQuestions = new LinkedList();
    private LinkedList scienceQuestions = new LinkedList();
    private LinkedList sportsQuestions = new LinkedList();
    private LinkedList rockQuestions = new LinkedList();

    public GameRule(Game mGame) {
        this.mGame = mGame;
        mCurrentPlayer = mGame.currentPlayer;
        mIsGettingOutOfPenaltyBox = mGame.isGettingOutOfPenaltyBox;
//        mInPenaltyBox = mGame.getPenaltyBox();
        mPlaces = new int[6];
        mPurses = new int[6];
        mInPenaltyBox = new boolean[6];
//        mPlaces = mGame.places;
//        mPlaces = mGame.getPlaces();

        popQuestions = mGame.popQuestions;
        scienceQuestions = mGame.scienceQuestions;
        sportsQuestions = mGame.sportsQuestions;
        rockQuestions = mGame.rockQuestions;
    }

    public void roll(int rollNumber) {
        mRollNumber = rollNumber;
        showRollInfo();
        boolean isRolledOdd = (mRollNumber % 2 != 0);
        boolean isInPenalty = mInPenaltyBox[mCurrentPlayer];

        if (isInPenalty) {
            if (isRolledOdd) {
                outOfPenalty();
            } else {
                notGettingOutOfPenalty();
            }
        } else {
            playerMove();
        }
    }

    private void showRollInfo() {
        System.out.println(mGame.players.get(mCurrentPlayer) + " is the current player");
        System.out.println("They have rolled a " + mRollNumber);
    }

    private void outOfPenalty() {
        mIsGettingOutOfPenaltyBox = true;
        System.out.println(mGame.players.get(mCurrentPlayer) + " is getting out of the penalty box");
        playerMove();
    }

    private void notGettingOutOfPenalty() {
        mIsGettingOutOfPenaltyBox = false;
        System.out.println(mGame.players.get(mCurrentPlayer) + " is not getting out of the penalty box");
    }

    private void playerMove() {
        gotoANewPlace();
        showPlayerPlaceInfo();
        showQuestionCategoryInfo();
        askQuestion();
    }

    private void gotoANewPlace() {
        mPlaces[mCurrentPlayer] = mPlaces[mCurrentPlayer] + mRollNumber;

        //todo
        int maxPlace = 11;
        if (mPlaces[mCurrentPlayer] > maxPlace)
            mPlaces[mCurrentPlayer] = mPlaces[mCurrentPlayer] - 12;
    }

    private void showPlayerPlaceInfo() {
        System.out.println(mGame.players.get(mCurrentPlayer)
                + "'s new location is "
                + mPlaces[mCurrentPlayer]);
    }

    private void showQuestionCategoryInfo() {
        System.out.println("The category is " + currentQuestionCategory());
    }

    private void askQuestion() {
        if (currentQuestionCategory() == "Pop")
            System.out.println(popQuestions.removeFirst());
        if (currentQuestionCategory() == "Science")
            System.out.println(scienceQuestions.removeFirst());
        if (currentQuestionCategory() == "Sports")
            System.out.println(sportsQuestions.removeFirst());
        if (currentQuestionCategory() == "Rock")
            System.out.println(rockQuestions.removeFirst());
    }

    private String currentQuestionCategory() {
        if (mPlaces[mCurrentPlayer] == 0) return "Pop";
        if (mPlaces[mCurrentPlayer] == 4) return "Pop";
        if (mPlaces[mCurrentPlayer] == 8) return "Pop";
        if (mPlaces[mCurrentPlayer] == 1) return "Science";
        if (mPlaces[mCurrentPlayer] == 5) return "Science";
        if (mPlaces[mCurrentPlayer] == 9) return "Science";
        if (mPlaces[mCurrentPlayer] == 2) return "Sports";
        if (mPlaces[mCurrentPlayer] == 6) return "Sports";
        if (mPlaces[mCurrentPlayer] == 10) return "Sports";
        return "Rock";
    }

    public void setPlace(int index, int place) {
        if (index < 0 || index > mPlaces.length) {
            throw new IndexOutOfBoundsException();
        }

        mPlaces[index] = place;
    }

    public void setPlaces(int[] places) {
        this.mPlaces = places;
    }

    public int getplace(int index) {
        if (index < 0 || index > mPlaces.length) {
            throw new IndexOutOfBoundsException();
        }

        return mPlaces[index];
    }

    public int[] getPlaces() {
        return mPlaces;
    }

    public void setpurse(int index, int purse) {
        if (index < 0 || index > mPurses.length) {
            throw new IndexOutOfBoundsException();
        }

        mPurses[index] = purse;
    }

    public void setpurses(int[] purses) {
        this.mPurses = purses;
    }

    public int getpurse(int index) {
        if (index < 0 || index > mPurses.length) {
            throw new IndexOutOfBoundsException();
        }

        return mPurses[index];
    }

    public int[] getPurses() {
        return mPurses;
    }

    public void setPenaltyBoxStatu(int index, boolean penaltyBox) {
        if (index < 0 || index > mInPenaltyBox.length) {
            throw new IndexOutOfBoundsException();
        }

        mInPenaltyBox[index] = penaltyBox;
    }

    public void setPenaltyBox(boolean[] penaltyBox) {
        this.mInPenaltyBox = penaltyBox;
    }

    public boolean getPenaltyBoxStatu(int index) {
        if (index < 0 || index > mInPenaltyBox.length) {
            throw new IndexOutOfBoundsException();
        }

        return mInPenaltyBox[index];
    }

    public boolean[] getPenaltyBox() {
        return mInPenaltyBox;
    }

}
