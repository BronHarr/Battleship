import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class WaterPanel extends JPanel{
	private boolean missed;
	
	public WaterPanel(){
		missed = false;
		//when clicked, update cell to show 
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(!missed) {
				 update();
				 //TODO: implement Turn-Taking, probably in main
				}
			}
		});
	}
	
	public void update() {
		missed = true;
		repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(167,216,230));
		g2d.fillRect(0,0,this.getWidth(),this.getHeight());

		g2d.setStroke(new BasicStroke(3));
		g2d.setColor(Color.black);
		//draws an 'X' over the cell when clicked
		if(missed) {
			g2d.drawLine(0, 0, this.getWidth(), this.getHeight());
			g2d.drawLine(this.getWidth(), 0, 0, this.getHeight());
		}
	}
	
	
}
