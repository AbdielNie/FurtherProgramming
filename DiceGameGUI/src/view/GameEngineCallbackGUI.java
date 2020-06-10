package view;


import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import Controller.Controller;
import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.DicePair;
import model.interfaces.Die;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback{
	
	private Controller controller;
	public GameEngineCallbackGUI(Controller controller) {
		// TODO Auto-generated constructor stub
		this.controller=controller;
		
	}
	

	@Override
	public void playerDieUpdate(Player player, Die die, GameEngine gameEngine) {
		// TODO Auto-generated method stub
		
				controller.displayDice(player, die);
				//controller.displayPlayers();
				
		
		
		
	}

	@Override
	public void houseDieUpdate(Die die, GameEngine gameEngine) {
		// TODO Auto-generated method stub
		controller.getDiceGameGUI().displayDice(die);
		//controller.displayPlayers();
		
	}

	@Override
	public void playerResult(Player player, DicePair result, GameEngine gameEngine) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void houseResult(DicePair result, GameEngine gameEngine) {
		// TODO Auto-generated method stub
		
	}
	

}
