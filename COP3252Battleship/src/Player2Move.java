import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

    
public class Player2Move extends JFrame implements ActionListener {

	private GridLayout gridLayout;		
	private JPanel jpanel;			
	private Ship EnemyFleet[];		
	public static JFrame TheFrame;
	public JButton[][] PlayerGameBoard;
	private int ShipsRemaining=5;
	
	public Player2Move() {
		
		  
		TheFrame=new JFrame("Player 2  Make Your Move!");
		  gridLayout=new GridLayout(10,10);
		  jpanel=new JPanel(gridLayout);
		  EnemyFleet=PlaceShips.BattleFleet;
		  
		  PlayerGameBoard=new JButton[10][10];		
			for (int y=0;y<10;y++) {			
				for (int x=0;x<10;x++) {								
					PlayerGameBoard[y][x]=new JButton();
					PlayerGameBoard[y][x].setBackground(Color.BLUE);
					PlayerGameBoard[y][x].addActionListener(this);					
					jpanel.add(PlayerGameBoard[y][x]);
				}			
			}
			
			TheFrame.add(jpanel);		
			TheFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			TheFrame.setSize(500, 400);
			TheFrame.setResizable(true);		
			TheFrame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event) {
		for (int y=0;y<10;y++){										
			for (int x=0;x<10;x++) {
				if (event.getSource()==PlayerGameBoard[y][x]) {
					CheckForHit(y,x);
					
												//TODO: find a way to make this delay and not be laggy
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					TheFrame.setVisible(false);
					PlayerMove.TheFrame.setVisible(true);					
				}
		
	   }
	}
}
	
	public void CheckForHit(int y,int x) {
		for (int i=0;i<5;i++) {			
			if (EnemyFleet[i].IsAHit(y,x)) {
					System.out.println("is a hit");										
					PlayerGameBoard[y][x].setBackground(Color.RED);										
					if (EnemyFleet[i].ShipHasSunk()) {
						ShipsRemaining--;
						if (ShipsRemaining==0) {
							System.out.println("Player 2 Wins!!!");	
						}
					}
					PlayerGameBoard[y][x].setEnabled(false);
					return;
			}
			
			else if (!EnemyFleet[i].IsAHit(y, x)) {
				PlayerGameBoard[y][x].setBackground(Color.WHITE);
				PlayerGameBoard[y][x].setEnabled(false);
				System.out.println("not a hit");
				
			}
		}
	
	}
	
}
