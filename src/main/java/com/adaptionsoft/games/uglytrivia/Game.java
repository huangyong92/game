package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    public ArrayList players = new ArrayList();
//	private int[] places = new int[6];
//	private int[] purses  = new int[6];
//	private boolean[] inPenaltyBox  = new boolean[6];

	public int currentPlayer = 0;
	public boolean isGettingOutOfPenaltyBox;

	public LinkedList popQuestions = new LinkedList();
	public LinkedList scienceQuestions = new LinkedList();
	public LinkedList sportsQuestions = new LinkedList();
	public LinkedList rockQuestions = new LinkedList();

	public GameRule mGameRule;

	public  Game(){
		mGameRule = new GameRule(this);

		//todo
    	for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast(createRockQuestion(i));
    	}
    }

	public String createRockQuestion(int index){
		return "Rock Question " + index;
	}
	
	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean add(String playerName) {
	    players.add(playerName);
	    mGameRule.setPlace(howManyPlayers(), 0);
//	    setPlace(howManyPlayers(), 0);
//	    places[howManyPlayers()] = 0;
		mGameRule.setpurse(howManyPlayers(), 0);
//	    purses[howManyPlayers()] = 0;
//	    inPenaltyBox[howManyPlayers()] = false;
	    mGameRule.setPenaltyBoxStatu(howManyPlayers(), false);

	    System.out.println(playerName + " was added");
	    System.out.println("They are player number " + players.size());
		return true;
	}
	
	public int howManyPlayers() {
		return players.size();
	}

	public void roll(int rollNumber) {
		mGameRule.roll(rollNumber);
	}

	public boolean wasCorrectlyAnswered() {
//		if (inPenaltyBox[currentPlayer]){
		if (mGameRule.getPenaltyBoxStatu(currentPlayer)){
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				int gainPurse = mGameRule.getpurse(currentPlayer) + 1;
				mGameRule.setpurse(currentPlayer, gainPurse);
//				purses[currentPlayer]++;
				System.out.println(players.get(currentPlayer) 
						+ " now has "
//						mGameRule.+ purses[currentPlayer]
						+ mGameRule.getpurse(currentPlayer)
						+ " Gold Coins.");
				
				boolean winner = didPlayerWin();
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				
				return winner;
			} else {
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				return false;
			}
		} else {
		
			System.out.println("Answer was corrent!!!!");
			int gainPurse = mGameRule.getpurse(currentPlayer) + 1;
			mGameRule.setpurse(currentPlayer, gainPurse);
//			purses[currentPlayer]++;
			System.out.println(players.get(currentPlayer) 
					+ " now has "
//					mGameRule.+ purses[currentPlayer]
					+ mGameRule.getpurse(currentPlayer)
					+ " Gold Coins.");
			
			boolean winner = didPlayerWin();
			currentPlayer++;
			if (currentPlayer == players.size()) currentPlayer = 0;
			
			return winner;
		}
	}
	
	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(players.get(currentPlayer)+ " was sent to the penalty box");
//		inPenaltyBox[currentPlayer] = true;
		mGameRule.setPenaltyBoxStatu(currentPlayer, true);

		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
		return false;
	}

	private boolean didPlayerWin() {
		return (mGameRule.getpurse(currentPlayer) == 6);
	}
}
