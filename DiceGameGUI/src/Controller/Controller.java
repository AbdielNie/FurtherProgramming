package Controller;
import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.Die;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.DiceGameGUI;
import view.GameEngineCallbackGUI;

public class Controller {
	
	private GameEngine gameEngine=new GameEngineImpl();
	private GameEngineCallbackGUI gameEngineCallbackGUI;
	private DiceGameGUI diceGameGUI;
	
	
	public Controller(DiceGameGUI diceGameGUI) {
		// TODO Auto-generated constructor stub
		gameEngineCallbackGUI=new GameEngineCallbackGUI(this);
		gameEngine.addGameEngineCallback(gameEngineCallbackGUI);
		this.diceGameGUI=diceGameGUI;
		addPlayer("test", 100);
	}
	
	public void addPlayer(String name,int points) {
		String playerId="Plyer"+gameEngine.getAllPlayers().size()+"";
		Player player=new SimplePlayer(playerId, name, points);
		gameEngine.addPlayer(player);
		displayPlayers();
		
	}
	
	public void displayDice(Player player,Die die) {
		diceGameGUI.displayDice(player, die);
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
	
	public DiceGameGUI getDiceGameGUI() {
		return diceGameGUI;
	}

}
