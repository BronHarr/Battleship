import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

    
public class Player2Move extends JPanel {

	private GridLayout gridLayout;		
	private JPanel jpanel;			
	private Ship EnemyFleet[];		
	private WaterPanel[][] PlayerGameBoard;
	private int ShipsRemaining=5;
	private int turnsTaken;
	
	public Player2Move(Ship[] p1Fleet) {
		
		gridLayout= new GridLayout(10,10,1,1);
		setPreferredSize(new Dimension(500,500));
		jpanel= new JPanel(gridLayout);
		EnemyFleet=p1Fleet;
		jpanel.setSize(500, 500);
		this.setBackground(Color.black);
		PlayerGameBoard = new WaterPanel[10][10];		
			for (int y=0;y<10;y++) {			
				for (int x=0;x<10;x++) {					
					PlayerGameBoard[x][y] = new WaterPanel(2, x, y);
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
