import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;					//maybe just make two player move classes
import javax.swing.JPanel;
											//idk if even need to get gameboards from other places

public class PlayerMove extends JPanel {

	private GridLayout gridLayout;		
	private JPanel jpanel;			
	private Ship EnemyFleet[];		
	private JPanel TheFrame;
	private WaterPanel[][] PlayerGameBoard;	
	private int ShipsRemaining=5;
	private int turnsTaken;
	
										
	public PlayerMove(Ship[] p2Fleet){						  
		
		  gridLayout=new GridLayout(10,10,1,1);
		  setPreferredSize(new Dimension(500,500));
		  jpanel=new JPanel(gridLayout);
		  jpanel.setBackground(Color.BLACK);
		  jpanel.setPreferredSize(new Dimension(500, 500));
		  EnemyFleet= p2Fleet;
		  
		  this.setBackground(Color.black);
		  PlayerGameBoard = new WaterPanel[10][10];		
			for (int y=0;y<10;y++) {			
				for (int x=0;x<10;x++) {			
					PlayerGameBoard[x][y] = new WaterPanel(1, x, y);
					jpanel.add(PlayerGameBoard[x][y]);
				}
			}
			add(jpanel);
	}
	
	public int getTurns() {
		turnsTaken = countTurns();
		return turnsTaken;
	}
	
	public int countTurns() {
		int count = 0;
		for (int y=0;y<10;y++) {			
			for (int x=0;x<10;x++) {
			 if(PlayerGameBoard[x][y].Interacted())
				 count++;
			}
		}
		return count;
	}
}
