import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class BattleShipFrame extends JFrame implements ActionListener{
	
	private Container container;
	private BorderLayout gui; //South region has a static label that can be changed depending on event
	public static PlayerMove p1;
	public static Player2Move p2;
	public static JLabel TurnLabel;
	
	private int currentPlayerTurn;
	private Ship[] p1Fleet;
	private Ship[] p2Fleet;
	
	private String p1Str = "<html><font size=+36>Player ONE</font><html> " ;
	private String p2Str = "<html><font size=+36>Player TWO</font><html> " ;
	private final String FIRE = "<html><br><center><p><font color=red><font size=+40>F I R E !</font></font><p><html>"; 
	
	
	
	private Timer timer;
	private int delay = 1000;
	
	
	/*public static void main(String args[]) {
		BattleShipFrame bsf = new BattleShipFrame();
	}*/
	
	public BattleShipFrame(){
		
		p1Fleet = PlaceShips.BattleFleet;
		p2Fleet = PlaceShipsPlayer2.BattleFleet;
		TurnLabel = new JLabel(p1Str + FIRE);
		TurnLabel.setHorizontalAlignment(JLabel.CENTER);
		TurnLabel.setVerticalAlignment(JLabel.CENTER);
		TurnLabel.setBackground(Color.black);
		TurnLabel.setForeground(Color.white);
		gui = new BorderLayout();
		setSize(800,660);
		setLayout(gui);
		container = getContentPane();
		p2 = new Player2Move(); 
		p1 = new PlayerMove();
		currentPlayerTurn = 2;

		add(p2, BorderLayout.CENTER);
		add(TurnLabel, BorderLayout.SOUTH);
		setVisible(true);
		//once a second, check if board is ready to switch players
		timer = new Timer(delay, this);
		timer.start();
		container.setBackground(Color.black);
		this.setResizable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	public void actionPerformed(ActionEvent time) { //potential actions to take based on
													//conditions checked every second
		if(isReady()) {								
			timer.stop();
			
			int sunken = FleetSunk();
			if(sunken == 1) { //p1 fleet is destroyed
				p1.TurnBoardOn();
				p2.TurnBoardOn();
				JLabel winner = new JLabel( "<html><Font size=+50>Player Two Wins!</font>");
				winner.setForeground(Color.yellow);
				winner.setHorizontalAlignment(JLabel.CENTER);
				remove(p1);
				add(winner, BorderLayout.NORTH);
				remove(TurnLabel);
				revalidate();
				repaint();
				wait(5000);
				dispose();
				MainMenu menu=new MainMenu(800,400);
			}
			else if(sunken == 2) { //p2 fleet is destroyed
				p1.TurnBoardOn();
				p2.TurnBoardOn();
				JLabel winner = new JLabel( "<html><Font size=+50>Player One Wins!</font>");
				winner.setForeground(Color.yellow);
				winner.setHorizontalAlignment(JLabel.CENTER);
				remove(p2);
				add(winner, BorderLayout.NORTH);
				remove(TurnLabel);
				revalidate();
				repaint();
				wait(5000);
				dispose();
				MainMenu menu=new MainMenu(800,400);
			}
			else {
				p1.TurnBoardOn();
				p2.TurnBoardOn();
 				NextTurn();
			}
		}	
	}
	
	//returns 1 or 2 if either player has lost all ships
	private int FleetSunk() {
		int p1SunkShips = 0;
		int p2SunkShips = 0;
		
		for(int i = 0; i < 5; i++) {
			if(p1Fleet[i].ShipHasSunk())
				p1SunkShips++;
			if(p2Fleet[i].ShipHasSunk())
				p2SunkShips++;
		}
		if(p1SunkShips == 5 || p2SunkShips == 5) {
			if(p1SunkShips == 5)
			 return 1;
			else
			 return 2;
		}
		else
		 return 0;
	}
	public static void wait(int ms)
	{
    		try
    		{
        		Thread.sleep(ms);
    		}
    		catch(InterruptedException ex)
    		{
        		Thread.currentThread().interrupt();
    		}
	}

	public boolean isReady() {
		//if player has taken their turn, return true
		return 	(p2.getTurns() > p1.getTurns() && currentPlayerTurn == 1) || (p2.getTurns() == p1.getTurns() && currentPlayerTurn == 2);
	}
	
	public void NextTurn() {
		if(currentPlayerTurn == 1) {
		 remove(p2);
		 add(p1, BorderLayout.CENTER);
		 TurnLabel.setText(p2Str + FIRE);
		 currentPlayerTurn = 2;
		}
		else {
		 remove(p1);
		 add(p2, BorderLayout.CENTER);
		 TurnLabel.setText(p1Str + FIRE);
		 currentPlayerTurn = 1;
		}
		revalidate();
		repaint();
		timer.start();
	}
	
}
