

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.DicePair;
import model.interfaces.Die;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback{
	
	private DiceGameGUI diceGameGUI;
	private GameEngine gameEngine=new GameEngineImpl();
	public GameEngineCallbackGUI(DiceGameGUI diceGameGUI) {
		// TODO Auto-generated constructor stub
		this.diceGameGUI=diceGameGUI;
		gameEngine.addGameEngineCallback(this);
		addPlayer("test", 100);
	}
	

	@Override
	public void playerDieUpdate(Player player, Die die, GameEngine gameEngine) {
		// TODO Auto-generated method stub
		
				diceGameGUI.displayDice(player, die);
				displayPlayers();
		
		
		
	}

	@Override
	public void houseDieUpdate(Die die, GameEngine gameEngine) {
		// TODO Auto-generated method stub
		diceGameGUI.displayDice(die);
		displayPlayers();
		
	}

	@Override
	public void playerResult(Player player, DicePair result, GameEngine gameEngine) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void houseResult(DicePair result, GameEngine gameEngine) {
		// TODO Auto-generated method stub
		
	}
	
	public void addPlayer(String name,int points) {
		String playerId="Plyer"+gameEngine.getAllPlayers().size()+"";
		Player player=new SimplePlayer(playerId, name, points);
		gameEngine.addPlayer(player);
		displayPlayers();
		
	}
	
	public void displayPlayers() {
		
		diceGameGUI.displayPlayers(gameEngine.getAllPlayers());
		
	}
	
	public void roll(String id,int bet) {
		Player player=gameEngine.getPlayer(id);
		gameEngine.placeBet(player, bet);
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				gameEngine.rollPlayer(player, 100, 1000, 100, 50, 500, 50);
			}
		}).start();
		
		
	}
	
	public void removePlayer(String id) {
		gameEngine.removePlayer(gameEngine.getPlayer(id));
		displayPlayers();
	}
	
	public Player getPlayer(String id) {
		return gameEngine.getPlayer(id);
	}
	
	public void setBet(String id,int bet) {
		gameEngine.getPlayer(id).setBet(bet);
	}
	
	public void resetBet(String id) {
		gameEngine.getPlayer(id).resetBet();
	}
	
	public void houseRolls() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					gameEngine.rollHouse(100, 1000, 100, 50, 500, 50);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		}).start();
		
	}

}
