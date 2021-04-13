import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
											

public class PlayerMove extends JPanel {

	private GridLayout gridLayout;		
	private JPanel jpanel;			
	private WaterPanel[][] PlayerGameBoard;	
	private int turnsTaken=0;
					
	public PlayerMove(){						  
		
		gridLayout= new GridLayout(10,10,1,1);
		setPreferredSize(new Dimension(500,500));
		jpanel= new JPanel(gridLayout);
		jpanel.setBackground(Color.BLACK);
		jpanel.setPreferredSize(new Dimension(500, 500));
		
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
		countTurns();
		return turnsTaken;
	}
	
	public void countTurns() {
		int count = 0;
		for (int y=0;y<10;y++) {			
			for (int x=0;x<10;x++) {
			 if(PlayerGameBoard[x][y].Interacted())
				 count++;
			}
		}
		turnsTaken = count;
	}
	
	public void TurnBoardOff() {					
		
		for (int i=0;i<10;i++) {
			for (int j=0;j<10;j++) {
				PlayerGameBoard[i][j].TurnOff();
			}
		}
	}	

	public void TurnBoardOn() {				
	
		for (int i=0;i<10;i++) {
			for (int j=0;j<10;j++) {
				PlayerGameBoard[i][j].TurnOn();	
			}
		}
	}		
}











