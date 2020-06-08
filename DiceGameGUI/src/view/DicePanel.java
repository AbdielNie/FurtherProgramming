package view;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class DicePanel extends JPanel {
	
	private Image image1;
	private Image image2;
	private ArrayList<Image> images=new ArrayList<Image>();
	
	/**
	 * Create the panel.
	 */
	public DicePanel() {
		
		
		for (int i = 0; i <6; i++) {
			try {
				Image image=ImageIO.read(new File("images/"+(i+1)+".png"));
				images.add(image);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		image1=images.get(0);
		image2=images.get(1);

	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		if(image1!=null)
		{
			g.drawImage(image1,0,0,this.getWidth()/2,this.getHeight(),0,0,image1.getWidth(this),image2.getHeight(this),this);
		
		}
		if (image2!=null) {
			g.drawImage(image2,this.getWidth()/2,0,this.getWidth(),this.getHeight(),0,0,image2.getWidth(this),image2.getHeight(this),this);
		}
		
		
	}
	
	public void setDices(int number,int dice) {
		if (number==1) {
			image1=images.get(dice);
			
		}
		if (number==2) {
			image2=images.get(dice);
		}
		repaint();
		
		
	}

}
