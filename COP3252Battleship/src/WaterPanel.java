import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class WaterPanel extends JPanel{
	private boolean missed = false;
	private boolean ShipHit = false;
	private final int whichPlayer;
	private int xCoord;
	private int yCoord;
	private Ship[] fleet;
	
	public WaterPanel(int player, int x, int y){
	
		missed = false;
		whichPlayer = player;
		xCoord = x;
		yCoord = y;
		if(whichPlayer == 1) {
			fleet = PlaceShips.BattleFleet;
		}
		else {
			fleet = PlaceShipsPlayer2.BattleFleet;
		}
		//when clicked, update cell to show 
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				  if(!Interacted()) {
				   CheckForHit();
				   update();
				  }	
			}
		});
	}
	
	//called when turns are counted
	public boolean Interacted() {
		return(missed||ShipHit);
	}
	public boolean Missed() {
		return missed;
	}
	public boolean isHit() {
		return ShipHit;
	}
	
	private void CheckForHit() {		
		int i = 0;
		while(i < 5) {
			if(fleet[i].IsAHit(yCoord, xCoord)) 
				ShipHit = true;
			i++;
		}
		update();
		
		if(!ShipHit) {
			missed = true;
			update();
		}
		
	}
	
	public void update() {
		repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(167,216,230));
		g2d.fillRect(0,0,this.getWidth(),this.getHeight());

		g2d.setStroke(new BasicStroke(3));
		g2d.setColor(Color.white);
		
		//draws an 'X' over the cell when clicked
		if(missed) {
			g2d.drawLine(0, 0, this.getWidth(), this.getHeight());
			g2d.drawLine(this.getWidth(), 0, 0, this.getHeight());
		}//red circle to represent hit
		else if(ShipHit) {
			g2d.setColor(Color.darkGray);
			g2d.setStroke(new BasicStroke(5));
			g2d.drawLine(this.getWidth()/2, 0, (this.getWidth()/2)+3, this.getHeight()/2);
			g2d.drawLine(this.getWidth()/2, 0, (this.getWidth()/2)-3, this.getHeight()/2);
			g2d.drawLine(0, this.getHeight()/2, (this.getWidth()/2), (this.getHeight()/2)-3);
			g2d.drawLine(0, this.getHeight()/2, (this.getWidth()/2), (this.getHeight()/2)+3);
			
			g2d.drawLine(this.getWidth()/2, this.getHeight(), (this.getWidth()/2)+3, this.getHeight()/2);
			g2d.drawLine(this.getWidth()/2, this.getHeight(), (this.getWidth()/2)-3, this.getHeight()/2);
			g2d.drawLine(this.getWidth(), this.getHeight()/2, (this.getWidth()/2), (this.getHeight()/2)-3);
			g2d.drawLine(this.getWidth(), this.getHeight()/2, (this.getWidth()/2), (this.getHeight()/2)+3);
			
			g2d.setColor(Color.red);
			g2d.fillOval((this.getWidth()/2)-7, (this.getHeight()/2)-7, 15, 15);

		}
	}
	
	
}
