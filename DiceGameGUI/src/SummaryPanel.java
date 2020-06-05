import javax.swing.JPanel;
import javax.swing.ListModel;

import model.interfaces.Player;

import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;

public class SummaryPanel extends JPanel {
	
	private JList list;
	/**
	 * Create the panel.
	 */
	public SummaryPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		list = new JList();
		scrollPane.setViewportView(list);

	}
	
	public void displayPlayers(Collection<Player> players) {
		DefaultListModel defaultListModel=new DefaultListModel<>();
		list.setModel(defaultListModel);
		defaultListModel.clear();
		for (Player player : players) {
			defaultListModel.addElement(player);
		}
	}

}
