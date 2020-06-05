package client;

import model.DicePairImpl;
import model.DieImpl;
import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.DicePair;
import model.interfaces.Die;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import validate.Validator;
import view.GameEngineCallbackImpl;
import view.interfaces.GameEngineCallback;

public class MyTesting {
	public static void main(String args[]) {
		MyTesting myTesting = new MyTesting();

		myTesting.testingValid();
		myTesting.invalidPlayerAddNullName();
		myTesting.invalidPlayerAddNullId();
		myTesting.invalidPlayerZeroPoint();
		
		myTesting.addPlayerExisted();
		myTesting.addPlayerNameExisted();
		myTesting.addPlayerIdExisted();
		
		myTesting.addCallback();
		myTesting.removeCallback();
		myTesting.removeCallbackFailed();
		
		myTesting.placeBetLessThanPoint();

		myTesting.invalidDelayPlayer1();
		myTesting.invalidDelayPlayer2();
		myTesting.invalidDelayPlayer3();
		myTesting.invalidDelayPlayer4();
		myTesting.invalidDelayPlayer5();
		myTesting.invalidDelayPlayer6();
		myTesting.invalidDelayPlayer7();
		myTesting.invalidDelayPlayer8();
		

		myTesting.invalidDelayHouse1();
		myTesting.invalidDelayHouse2();
		myTesting.invalidDelayHouse3();
		myTesting.invalidDelayHouse4();
		myTesting.invalidDelayHouse5();
		myTesting.invalidDelayHouse6();
		myTesting.invalidDelayHouse7();
		myTesting.invalidDelayHouse8();
		
		myTesting.applyWinLossBeforePlayer();
		myTesting.applyWinLossBeforePlayerRoll();
		myTesting.applayWinLossPlayerWin();
		myTesting.applayWinLossHouseWin();
		myTesting.applayWinLossTie();
		
		myTesting.hoursrRollWithoutaddingPlayer();
		myTesting.hoursrRollFirst();
	}

	/**
	 * <p>
	 * This method simulates a valid standard game play with one call back by
	 * following steps:
	 * <LI>add two player
	 * <LI>place bet
	 * <LI>player roll
	 * <LI>house roll
	 * <LI>win/loss apply
	 * <LI>add a new play
	 * <LI>play the game again
	 * <LI>remove a play
	 * <LI>play the game again
	 * </UL>
	 * </p>
	 */
	private void testingValid() {
		GameEngine gameEngine = new GameEngineImpl();
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());

		Player p1 = new SimplePlayer("1", "The Roller", 5000);
		Player p2 = new SimplePlayer("2", "The Loser", 1000);
		Player p3 = new SimplePlayer("3", "The Third Player", 2000);

		gameEngine.addPlayer(p1);
		gameEngine.addPlayer(p2);
		for (Player player : gameEngine.getAllPlayers()) {
			gameEngine.placeBet(player, 100);
			gameEngine.rollPlayer(player, 100, 1000, 100, 50, 500, 50);
		}
		gameEngine.rollHouse(100, 1000, 200, 50, 500, 25);

		// add another player
		gameEngine.addPlayer(p3);
		for (Player player : gameEngine.getAllPlayers()) {
			gameEngine.placeBet(player, 150);
			gameEngine.rollPlayer(player, 100, 1000, 100, 50, 500, 50);
		}
		gameEngine.rollHouse(100, 1000, 200, 50, 500, 25);

		// remove a player
		gameEngine.removePlayer(p2);
		for (Player player : gameEngine.getAllPlayers()) {
			gameEngine.placeBet(player, 200);
			gameEngine.rollPlayer(player, 100, 1000, 100, 50, 500, 50);
		}
		gameEngine.rollHouse(100, 1000, 200, 50, 500, 25);
	}

	// add invalid player with null name
	private void invalidPlayerAddNullName() {
		GameEngine gameEngine = new GameEngineImpl();
		try {
			gameEngine.addPlayer(new SimplePlayer("1", null, 5000));
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	// add invalid player with null ID
	private void invalidPlayerAddNullId() {
		GameEngine gameEngine = new GameEngineImpl();
		try {
			gameEngine.addPlayer(new SimplePlayer(null, "The Roller", 5000));
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	// add invalid player with 0 point
	private void invalidPlayerZeroPoint() {
		GameEngine gameEngine = new GameEngineImpl();
		try {
			gameEngine.addPlayer(new SimplePlayer("1", "The Roller", 0));
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	// add a player which already existed (same id, same name)
	private void addPlayerExisted() {
		GameEngine gameEngine = new GameEngineImpl();
		Player p1 = new SimplePlayer("1", "The Roller", 5000);
		Player p2 = new SimplePlayer("2", "The Loser", 1000);
		Player p3 = new SimplePlayer("2", "The Third Player", 2000);

		gameEngine.addPlayer(p1);
		gameEngine.addPlayer(p2);

		gameEngine.addPlayer(p3);

		for (Player player : gameEngine.getAllPlayers()) {
			System.out.printf("Player Id: %s  Player Name: %s\n", player.getPlayerId(), player.getPlayerName());
		}
		System.out.println();
	}

	// add a player with same name but different id
	private void addPlayerNameExisted() {
		GameEngine gameEngine = new GameEngineImpl();
		Player p1 = new SimplePlayer("1", "The Roller", 5000);
		Player p2 = new SimplePlayer("2", "The Loser", 1000);
		Player p3 = new SimplePlayer("3", "The Roller", 5000);

		gameEngine.addPlayer(p1);
		gameEngine.addPlayer(p2);

		gameEngine.addPlayer(p3);

		for (Player player : gameEngine.getAllPlayers()) {
			System.out.printf("Player Id: %s  Player Name: %s\n", player.getPlayerId(), player.getPlayerName());
		}
		System.out.println();
	}

	// add a player with different name but same id
	private void addPlayerIdExisted() {
		GameEngine gameEngine = new GameEngineImpl();
		Player p1 = new SimplePlayer("1", "The Roller", 5000);
		Player p2 = new SimplePlayer("2", "The Loser", 1000);
		Player p3 = new SimplePlayer("1", "The Roller", 5000);

		gameEngine.addPlayer(p1);
		gameEngine.addPlayer(p2);

		gameEngine.addPlayer(p3);

		for (Player player : gameEngine.getAllPlayers()) {
			System.out.printf("Player Id: %s  Player Name: %s\n", player.getPlayerId(), player.getPlayerName());
		}
		System.out.println();
	}

	// add a second callback
	private void addCallback() {
		GameEngine gameEngine = new GameEngineImpl();
		GameEngineCallback cb1 = new GameEngineCallbackImpl();
		GameEngineCallback cb2 = new GameEngineCallbackImpl();

		gameEngine.addGameEngineCallback(cb1);
		gameEngine.addGameEngineCallback(cb2);
		System.out.println(gameEngine.removeGameEngineCallback(cb1));

	}

	// remove one callback in cb list
	private void removeCallback() {
		GameEngine gameEngine = new GameEngineImpl();
		GameEngineCallback cb1 = new GameEngineCallbackImpl();
		GameEngineCallback cb2 = new GameEngineCallbackImpl();

		gameEngine.addGameEngineCallback(cb1);
		gameEngine.addGameEngineCallback(cb2);
		System.out.printf("remove cb in the list, result: %s\n\n", gameEngine.removeGameEngineCallback(cb1));
	}

	// remove not exist callback
	private void removeCallbackFailed() {
		GameEngine gameEngine = new GameEngineImpl();
		GameEngineCallback cb1 = new GameEngineCallbackImpl();
		GameEngineCallback cb2 = new GameEngineCallbackImpl();

		gameEngine.addGameEngineCallback(cb1);
		System.out.printf("remove cb not exist, result: %s\n\n", gameEngine.removeGameEngineCallback(cb2));
	}

	//player place bet less than current point
	private void placeBetLessThanPoint() {
		GameEngine gameEngine = new GameEngineImpl();
		Player player = new SimplePlayer("1", "The Roller", 200);

		gameEngine.addPlayer(player);
		gameEngine.placeBet(player, 300);
		System.out.println(player.getBet());
		System.out.println();
	}

	// player roll with initialDelay1 parameter < 0
	private void invalidDelayPlayer1() {
		GameEngine gameEngine = new GameEngineImpl();
		Player player = new SimplePlayer("1", "The Roller", 5000);

		gameEngine.addPlayer(player);
		gameEngine.placeBet(player, 100);
		try {
			gameEngine.rollPlayer(player, -1, 1000, 100, 50, 500, 50);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}
	
	// player roll with finalDelay1 parameter < 0
	private void invalidDelayPlayer2() {
		GameEngine gameEngine = new GameEngineImpl();
		Player player = new SimplePlayer("1", "The Roller", 5000);

		gameEngine.addPlayer(player);
		gameEngine.placeBet(player, 100);
		try {
			gameEngine.rollPlayer(player, 100, -1000, 100, 50, 500, 50);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}
	
	// player roll with initialDelay2 parameter < 0
	private void invalidDelayPlayer3() {
		GameEngine gameEngine = new GameEngineImpl();
		Player player = new SimplePlayer("1", "The Roller", 5000);

		gameEngine.addPlayer(player);
		gameEngine.placeBet(player, 100);
		try {
			gameEngine.rollPlayer(player, 100, 1000, 100, -50, 500, 50);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}
	
	// player roll with finalDelay2 parameter < 0
	private void invalidDelayPlayer4() {
		GameEngine gameEngine = new GameEngineImpl();
		Player player = new SimplePlayer("1", "The Roller", 5000);

		gameEngine.addPlayer(player);
		gameEngine.placeBet(player, 100);
		try {
			gameEngine.rollPlayer(player, 100, 1000, 100, 50, -500, 50);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}
	
	//player roll with finalDelay < initialDelay
	private void invalidDelayPlayer5() {
		GameEngine gameEngine = new GameEngineImpl();
		Player player = new SimplePlayer("1", "The Roller", 5000);

		gameEngine.addPlayer(player);
		gameEngine.placeBet(player, 100);
		try {
			gameEngine.rollPlayer(player, 1000, 100, 100, 50, 500, 50);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}
	
	//player roll with finalDelay < initialDelay
	private void invalidDelayPlayer6() {
		GameEngine gameEngine = new GameEngineImpl();
		Player player = new SimplePlayer("1", "The Roller", 5000);

		gameEngine.addPlayer(player);
		gameEngine.placeBet(player, 100);
		try {
			gameEngine.rollPlayer(player, 100, 1000, 100, 500, 50, 50);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}
	
	// delayIncrement > (finalDelay - initialDelay)
	private void invalidDelayPlayer7() {
		GameEngine gameEngine = new GameEngineImpl();
		Player player = new SimplePlayer("1", "The Roller", 5000);

		gameEngine.addPlayer(player);
		gameEngine.placeBet(player, 100);
		try {
			gameEngine.rollPlayer(player, 100, 1000, 1000, 50, 500, 50);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}
	
	// delayIncrement > (finalDelay - initialDelay)
	private void invalidDelayPlayer8() {
		GameEngine gameEngine = new GameEngineImpl();
		Player player = new SimplePlayer("1", "The Roller", 5000);

		gameEngine.addPlayer(player);
		gameEngine.placeBet(player, 100);
		try {
			gameEngine.rollPlayer(player, 100, 1000, 100, 50, 500, 500);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}
	
	// house roll with initialDelay1 parameter < 0
	private void invalidDelayHouse1() {
		GameEngine gameEngine = new GameEngineImpl();
		Player player = new SimplePlayer("1", "The Roller", 5000);

		gameEngine.addPlayer(player);
		gameEngine.placeBet(player, 100);
		gameEngine.rollPlayer(player, 100, 1000, 100, 50, 500, 50);
		try {
			gameEngine.rollHouse(-100, 1000, 100, 50, 500, 50);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}
	
	// house roll with finalDelay1 parameter < 0
	private void invalidDelayHouse2() {
		GameEngine gameEngine = new GameEngineImpl();
		Player player = new SimplePlayer("1", "The Roller", 5000);

		gameEngine.addPlayer(player);
		gameEngine.placeBet(player, 100);
		gameEngine.rollPlayer(player, 100, 1000, 100, 50, 500, 50);
		try {
			gameEngine.rollHouse(100, -1000, 100, 50, 500, 50);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}
	
	// house roll with initialDelay2 parameter < 0
	private void invalidDelayHouse3() {
		GameEngine gameEngine = new GameEngineImpl();
		Player player = new SimplePlayer("1", "The Roller", 5000);

		gameEngine.addPlayer(player);
		gameEngine.placeBet(player, 100);
		gameEngine.rollPlayer(player, 100, 1000, 100, 50, 500, 50);
		try {
			gameEngine.rollHouse(100, 1000, 100, -50, 500, 50);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}
	
	// house roll with finalDelay2 parameter < 0
	private void invalidDelayHouse4() {
		GameEngine gameEngine = new GameEngineImpl();
		Player player = new SimplePlayer("1", "The Roller", 5000);

		gameEngine.addPlayer(player);
		gameEngine.placeBet(player, 100);
		gameEngine.rollPlayer(player, 100, 1000, 100, 50, 500, 50);
		try {
			gameEngine.rollHouse(100, 1000, 100, 50, -500, 50);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}
	
	//house roll with finalDelay < initialDelay
	private void invalidDelayHouse5() {
		GameEngine gameEngine = new GameEngineImpl();
		Player player = new SimplePlayer("1", "The Roller", 5000);

		gameEngine.addPlayer(player);
		gameEngine.placeBet(player, 100);
		gameEngine.rollPlayer(player, 100, 1000, 100, 50, 500, 50);
		try {
			gameEngine.rollHouse(1000, 100, 100, 50, 500, 50);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}
	
	//house roll with finalDelay < initialDelay
	private void invalidDelayHouse6() {
		GameEngine gameEngine = new GameEngineImpl();
		Player player = new SimplePlayer("1", "The Roller", 5000);

		gameEngine.addPlayer(player);
		gameEngine.placeBet(player, 100);
		gameEngine.rollPlayer(player, 100, 1000, 100, 50, 500, 50);
		try {
			gameEngine.rollHouse(100, 1000, 100, 500, 50, 50);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}
	
	// delayIncrement > (finalDelay - initialDelay)
	private void invalidDelayHouse7() {
		GameEngine gameEngine = new GameEngineImpl();
		Player player = new SimplePlayer("1", "The Roller", 5000);

		gameEngine.addPlayer(player);
		gameEngine.placeBet(player, 100);
		gameEngine.rollPlayer(player, 100, 1000, 100, 50, 500, 50);
		try {
			gameEngine.rollHouse(100, 1000, 1000, 500, 500, 50);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}
	
	// delayIncrement > (finalDelay - initialDelay)
	private void invalidDelayHouse8() {
		GameEngine gameEngine = new GameEngineImpl();
		Player player = new SimplePlayer("1", "The Roller", 5000);

		gameEngine.addPlayer(player);
		gameEngine.placeBet(player, 100);
		gameEngine.rollPlayer(player, 100, 1000, 100, 50, 500, 50);
		try {
			gameEngine.rollHouse(100, 1000, 100, 50, 500, 500);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}
	
	// apply win/loss: player win
	private void applayWinLossPlayerWin() {
		GameEngine gameEngine = new GameEngineImpl();
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
		DicePair playerDicePair = new DicePairImpl(new DieImpl(1, 4, 6), new DieImpl(2, 3, 6));
		DicePair houseResult = new DicePairImpl(new DieImpl(1, 1, 6), new DieImpl(2, 1, 6));

		Player winer = new SimplePlayer("1", "The Roller", 5000);
		winer.setBet(100);
		winer.setResult(playerDicePair);

		System.out.printf("Player's points before roll: %d\n", winer.getPoints());
		gameEngine.applyWinLoss(winer, houseResult);
		System.out.printf("Player's points after roll: %d\n", winer.getPoints());
		System.out.println();
	}

	// apply win/loss: house win
	private void applayWinLossHouseWin() {
		GameEngine gameEngine = new GameEngineImpl();
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
		DicePair playerDicePair = new DicePairImpl(new DieImpl(1, 1, 6), new DieImpl(2, 1, 6));
		DicePair houseResult = new DicePairImpl(new DieImpl(1, 4, 6), new DieImpl(2, 3, 6));

		Player winer = new SimplePlayer("1", "The Roller", 5000);
		winer.setBet(100);
		winer.setResult(playerDicePair);

		System.out.printf("Player's points before roll: %d\n", winer.getPoints());
		gameEngine.applyWinLoss(winer, houseResult);
		System.out.printf("Player's points after roll: %d\n", winer.getPoints());
		System.out.println();
	}

	// apply win/loss: tie
	private void applayWinLossTie() {
		GameEngine gameEngine = new GameEngineImpl();
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
		DicePair playerDicePair = new DicePairImpl(new DieImpl(1, 2, 6), new DieImpl(2, 1, 6));
		DicePair houseResult = new DicePairImpl(new DieImpl(1, 1, 6), new DieImpl(2, 2, 6));

		Player winer = new SimplePlayer("1", "The Roller", 5000);
		winer.setBet(100);
		winer.setResult(playerDicePair);

		System.out.printf("Player's points before roll: %d\n", winer.getPoints());
		gameEngine.applyWinLoss(winer, houseResult);
		System.out.printf("Player's points after roll: %d\n", winer.getPoints());
		System.out.println();
	}

	// apply win/loss before adding any player
	private void applyWinLossBeforePlayer() {
		GameEngine gameEngine = new GameEngineImpl();
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
		DicePair dp = new DicePairImpl(new DieImpl(1, 2, 6), new DieImpl(2, 3, 6));

		try {
			gameEngine.applyWinLoss(null, dp);
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	// apply win/loss before player roll
	private void applyWinLossBeforePlayerRoll() {
		GameEngine gameEngine = new GameEngineImpl();
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());

		DicePair dp = new DicePairImpl(new DieImpl(1, 2, 6), new DieImpl(2, 3, 6));

		try {
			gameEngine.applyWinLoss(new SimplePlayer("1", "The Roller", 5000), dp);
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	// house rolls the die before any player has been added
	private void hoursrRollWithoutaddingPlayer() {
		GameEngine gameEngine = new GameEngineImpl();
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
		try {
			gameEngine.rollHouse(100, 1000, 200, 50, 500, 25);
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	// house rolls the die before player roll
	private void hoursrRollFirst() {
		GameEngine gameEngine = new GameEngineImpl();
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());

		gameEngine.addPlayer(new SimplePlayer("1", "The Roller", 5000));

		try {
			gameEngine.rollHouse(100, 1000, 200, 50, 500, 25);
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

}
