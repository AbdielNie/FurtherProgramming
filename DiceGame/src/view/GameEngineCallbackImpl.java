package view;

import java.lang.reflect.Array;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.DicePair;
import model.interfaces.Die;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

/**
 * 
 * Skeleton example implementation of GameEngineCallback showing Java logging
 * behaviour
 * 
 * @author Caspar Ryan
 * @see view.interfaces.GameEngineCallback
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback {
	private static final Logger logger = Logger.getLogger(GameEngineCallback.class.getName());

	public GameEngineCallbackImpl() {
		logger.setLevel(Level.FINE);
	}

	@Override
	public void playerDieUpdate(Player player, Die die, GameEngine gameEngine) {
		// intermediate results logged at Level.FINE
		logger.log(Level.FINE,
				String.format("%s die %d rolled to %s", player.getPlayerName(), die.getNumber(), die.toString()));
	}

	@Override
	public void playerResult(Player player, DicePair result, GameEngine gameEngine) {
		// final results logged at Level.INFO
		logger.log(Level.INFO, 
				String.format("%s *RESULT*: Dice 1: %s, Dice 2: %s ..Total: %d", 
						player.getPlayerName(), result.getDie1().toString(), result.getDie2().toString(), result.getTotal()));
	}

	@Override
	public void houseDieUpdate(Die die, GameEngine gameEngine) {
		logger.log(Level.FINE,
				String.format("House die %d rolled to %s", die.getNumber(), die.toString()));

	}

	@Override
	public void houseResult(DicePair result, GameEngine gameEngine) {
		logger.log(Level.INFO, 
				String.format("House *RESULT*: Dice 1: %s, Dice 2: %s ..Total: %d", 
						result.getDie1().toString(), result.getDie2().toString(), result.getTotal()));
		String finalPlayerResult = "FINAL PLAYER RESULTS\n";
		
		for (Player player : gameEngine.getAllPlayers()) {
			String playerResult = String.format("Player: id=%s, name=%s, bet=%d, points=%d, RESULT: Dice 1: %s, Dice 2: %s ..Total: %d\n", 
					player.getPlayerId(), player.getPlayerName(), player.getBet(), player.getPoints(),
					player.getResult().getDie1().toString(), player.getResult().getDie2().toString(), player.getResult().getTotal());
			finalPlayerResult	+= playerResult;
		}
		logger.log(Level.INFO,finalPlayerResult);
		// TODO Auto-generated method stub

	}
	

}
