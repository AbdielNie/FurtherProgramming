package view;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PlayerPanel extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private Controller controller;
	private JTextField textName;
	private JTextField textPoints;
	
	

	
	/**
	 * Create the dialog.
	 */
	public PlayerPanel(Controller controller) {
		this.controller=controller;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(58, 44, 72, 18);
		contentPanel.add(lblNewLabel);
		
		textName = new JTextField();
		textName.setBounds(143, 41, 176, 24);
		contentPanel.add(textName);
		textName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Points");
		lblNewLabel_1.setBounds(58, 102, 72, 18);
		contentPanel.add(lblNewLabel_1);
		
		textPoints = new JTextField();
		textPoints.setBounds(143, 99, 176, 24);
		contentPanel.add(textPoints);
		textPoints.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String name=textName.getText();
						int points=Integer.parseInt(textPoints.getText());
						try {
							PlayerPanel.this.controller.addPlayer(name, points);
							JOptionPane.showMessageDialog(null,"Add a player!");
						} catch (Exception e2) {
							// TODO: handle exception
							JOptionPane.showMessageDialog(null, e2.getMessage());
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
