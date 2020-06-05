package model;

import model.interfaces.DicePair;
import model.interfaces.Player;

public class SimplePlayer implements Player {
	private String playerName;
	private String playerId;
	private int bet = 0;
	private int points = 0;
	private DicePair result = null;

	public SimplePlayer(String playerId, String playerName, int points) {
			
		if (playerId == null ) {
			throw new IllegalArgumentException("Player ID can not be null.");
		}
		
		if (playerName == null) {
			throw new IllegalArgumentException("Player name can not be null.");
		}
		
		if (points <= 0) {
			throw new IllegalArgumentException("Point must be greater than 0.");
		}
		
		this.playerId = playerId;
		this.playerName = playerName;
		this.points = points;
		
		}
		
		

	@Override
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@Override
	public int getPoints() {
		return points;
	}

	@Override
	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public String getPlayerId() {
		return playerId;
	}

	@Override
	public boolean setBet(int bet) {
		if (bet == 0 || bet > points) {
			resetBet();
			return false;
		} else {
			this.bet = bet;
			return true;
		}
	}

	@Override
	public int getBet() {
		if (bet > 0)
			return bet;
		else
			return 0;
	}

	@Override
	public void resetBet() {
		bet = 0;
	}

	@Override
	public DicePair getResult() {
		return result;
	}

	@Override
	public void setResult(DicePair rollResult) {
		this.result = rollResult;
	}

	@Override
	public String toString() {
		
		if (result==null) {
			return String.format("Player: id=%s, name=%s, bet=%d, points=%d\n", 
					playerId, playerName, bet, points);
		}
		
		return String.format("Player: id=%s, name=%s, bet=%d, points=%d, RESULT: Dice 1: %s, Dice 2: %s ..Total: %d\n", 
				playerId, playerName, bet, points,
				result.getDie1().toString(), result.getDie2().toString(),result.getTotal());
	}

}
