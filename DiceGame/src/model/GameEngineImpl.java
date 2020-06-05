package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import model.interfaces.DicePair;
import model.interfaces.Die;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine {
	
	private Map<String, Player> players = new HashMap<String, Player>();
	private List<GameEngineCallback> gameEngineCallbacks = new ArrayList<GameEngineCallback>();
	
	@Override
	public void rollPlayer(Player player, int initialDelay1, int finalDelay1, int delayIncrement1, int initialDelay2,
			int finalDelay2, int delayIncrement2) {
		
		rollParameterChecker(initialDelay1, finalDelay1, delayIncrement1, initialDelay2, finalDelay2, delayIncrement2);
		
		DicePair result = (roll(player, initialDelay1, finalDelay1, delayIncrement1, initialDelay2, finalDelay2, delayIncrement2));
		player.setResult(result);
		for (GameEngineCallback gameEngineCallback : gameEngineCallbacks) {
			gameEngineCallback.playerResult(player, result, this);
		}
	}
	
	/**
	 * This method is used to check whether the parameters for rolling function are valid
	 */
	private void rollParameterChecker(int initialDelay1, int finalDelay1, int delayIncrement1, int initialDelay2,
			int finalDelay2, int delayIncrement2) {
		if (initialDelay1 < 0 || finalDelay1 < 0 || initialDelay2 < 0 || finalDelay2 < 0 
				|| finalDelay1 < initialDelay1 || finalDelay2 < initialDelay2 
				|| delayIncrement1 > (finalDelay1 - initialDelay1) ||delayIncrement2 > (finalDelay2 - initialDelay2))
			throw new IllegalArgumentException("Illegal parameters for rolling.");
	}

	/**
	 * This method is used to roll a pair of dice by calling rollDie function.
	 * @return DicePair
	 */
	private DicePair roll(Player player, int initialDelay1, int finalDelay1, int delayIncrement1, int initialDelay2,
			int finalDelay2, int delayIncrement2) {
		ExecutorService threadpool = Executors.newCachedThreadPool();

		Future<Die> futureTask1 = threadpool
				.submit(() -> rollDie(player, 1, initialDelay1, finalDelay1, delayIncrement1));
		Future<Die> futureTask2 = threadpool
				.submit(() -> rollDie(player, 2, initialDelay2, finalDelay2, delayIncrement2));

		while (true) {
			if (futureTask1.isDone() && futureTask2.isDone()) {
				try {
					Die die1 = futureTask1.get();
					Die die2 = futureTask2.get();
					threadpool.shutdown();
					DicePair result = new DicePairImpl(die1, die2);
					return result;
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * This method is used to roll a single die.
	 * @return Die
	 */
	private Die rollDie(Player player, int dieNum, int initialDelay, int finalDelay, int delayIncrement) {
		int delay = initialDelay;
		Random random = new Random();
		Die die = null;

		while (delay < finalDelay) {
			die = new DieImpl(dieNum, random.nextInt(Die.NUM_FACES) + 1, Die.NUM_FACES);
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (GameEngineCallback gameEngineCallback : gameEngineCallbacks) {
				if (player != null) {
					gameEngineCallback.playerDieUpdate(player, die, this);
				} else {
					gameEngineCallback.houseDieUpdate(die, this);
				}
			}
			delay += delayIncrement;
		}
		return die;
	}

	@Override
	public void rollHouse(int initialDelay1, int finalDelay1, int delayIncrement1, int initialDelay2, int finalDelay2,
			int delayIncrement2) {
		
		if (players.isEmpty()) {
			throw new NullPointerException("No players added.");
		}
		
		rollParameterChecker(initialDelay1, finalDelay1, delayIncrement1, initialDelay2, finalDelay2, delayIncrement2);
		
		DicePair houseResult = roll(null, initialDelay1, finalDelay1, delayIncrement1, initialDelay2, finalDelay2,
				delayIncrement2);
		
		for (Player player : players.values()) {
			
			if (player.getResult() == null) {
				throw new NullPointerException("Player need to roll first to get the result.");
			}
			
			applyWinLoss(player, houseResult);
		}
		for (GameEngineCallback gameEngineCallback : gameEngineCallbacks) {
			gameEngineCallback.houseResult(houseResult, this);
		}
		for (Player player : players.values()) {
			 player.resetBet();
		}
	}

	@Override
	public void applyWinLoss(Player player, DicePair houseResult) {
		
		if (player == null) {
			throw new NullPointerException("No player added.");
		}
		
		if (player.getResult() == null) {
			throw new NullPointerException("Player need to roll first.");
		}

		if (player.getResult().compareTo(houseResult) > 0) {
			player.setPoints(player.getPoints()+ player.getBet());
		}
		else if(player.getResult().compareTo(houseResult) < 0) {
			player.setPoints(player.getPoints()- player.getBet());
		}
	}

	@Override
	public void addPlayer(Player player) {
		players.put(player.getPlayerId(), player);
	}

	@Override
	public Player getPlayer(String id) {
		return players.get(id);
	}

	@Override
	public boolean removePlayer(Player player) {
		return players.remove(player.getPlayerId()) != null;
	}

	@Override
	public boolean placeBet(Player player, int bet) {
		return player.setBet(bet);
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		gameEngineCallbacks.add(gameEngineCallback);
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		return gameEngineCallbacks.remove(gameEngineCallback);
	}

	@Override
	public Collection<Player> getAllPlayers() {
		return players.values();
	}

}
