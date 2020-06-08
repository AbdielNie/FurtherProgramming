package view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import model.interfaces.Die;
import model.interfaces.Player;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.awt.event.ActionEvent;

public class DiceGameGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtBet;
	private DicePanel dicePanel;
	private SummaryPanel panelSummary;
	private JComboBox comboBox;
	private JLabel statusJLabel;
	
	private Controller controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiceGameGUI frame = new DiceGameGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DiceGameGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 732, 432);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Game");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("AddPlayer");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new PlayerPanel(controller).show();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("RemovePlayer");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=comboBox.getSelectedItem().toString();
				controller.removePlayer(id);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("RemoveBet");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=comboBox.getSelectedItem().toString();
				controller.resetBet(id);
				txtBet.setText("0");
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("HouseRolls");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.houseRolls();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Player");
		toolBar.add(lblNewLabel);
		
		comboBox = new JComboBox();
		
		comboBox.setPreferredSize(new Dimension(200, 24));
		toolBar.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Bet");
		toolBar.add(lblNewLabel_1);
		
		txtBet = new JTextField();
		txtBet.setText("0");
		txtBet.setPreferredSize(new Dimension(20, 24));
		toolBar.add(txtBet);
		txtBet.setColumns(10);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Player player=controller.getPlayer(comboBox.getSelectedItem().toString());
					txtBet.setText(player.getBet()+"");
				} catch (Exception e2) {
					// TODO: handle exception
					
				}
				
			}
		});
		
		JButton btnPlaceBet = new JButton("PlaceBet");
		btnPlaceBet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=comboBox.getSelectedItem().toString();
				int bet=Integer.parseInt(txtBet.getText());
				controller.setBet(id, bet);
			}
		});
		toolBar.add(btnPlaceBet);
		
		JButton btnRoll = new JButton("Roll");
		btnRoll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String id=comboBox.getSelectedItem().toString();
					int bet=Integer.parseInt(txtBet.getText());
					controller.roll(id, bet);
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
				
				
			}
		});
		toolBar.add(btnRoll);
		
		JToolBar toolBarBottom = new JToolBar();
		contentPane.add(toolBarBottom, BorderLayout.SOUTH);
		
		statusJLabel = new JLabel("New label");
		toolBarBottom.add(statusJLabel);
		
		panelSummary = new SummaryPanel();
		panelSummary.setPreferredSize(new Dimension(200, 10));
		contentPane.add(panelSummary, BorderLayout.EAST);
		
		dicePanel= new DicePanel();
		contentPane.add(dicePanel, BorderLayout.CENTER);
		
		controller=new Controller(this);
		
		
	}
	
	public void displayPlayers(Collection<Player> players) {
		panelSummary.displayPlayers(players);
		DefaultComboBoxModel defaultComboBoxModel=(DefaultComboBoxModel)comboBox.getModel();
		defaultComboBoxModel.removeAllElements();
		for (Player player : players) {
			defaultComboBoxModel.addElement(player.getPlayerId());
		}
	}
	
	public void displayDice(Player player, Die die) {
		
		dicePanel.setDices(die.getNumber(), die.getValue()-1);
		statusJLabel.setText(player.getPlayerName()+" "+die);
		
	}
	
	public void displayDice(Die die) {
		dicePanel.setDices(die.getNumber(), die.getValue()-1);
		statusJLabel.setText(die.toString());
	}

}
