import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BattleShipFrame extends JFrame implements ActionListener{
	
	private Container container;
	private BorderLayout gui; //north and west regions for coordinate labels, south and east for gui
	private int currentPlayerTurn;
	private Ship[] p1Fleet;
	private Ship[] p2Fleet;
	private PlayerMove p1;
	private Player2Move p2;
	private Timer timer;
	
	public static void main(String args[]) {
		BattleShipFrame bf = new BattleShipFrame();
	}
	
	public BattleShipFrame(){
		
		p1Fleet = PlaceShips.BattleFleet;
		p2Fleet = PlaceShipsPlayer2.BattleFleet;
		gui = new BorderLayout(5, 2);
		setSize(1000,900);
		setLayout(gui);
		container = getContentPane();
		p2 = new Player2Move(p1Fleet); 
		p1 = new PlayerMove(p2Fleet);
		currentPlayerTurn = 1;

		this.add(p1, BorderLayout.CENTER);
		this.setVisible(true);

		timer = new Timer(5000, this);
		timer.start();
		
	}
	
	public void actionPerformed(ActionEvent time) {
		if(isReady()) {
			NextTurn();
		}
			
	}
	
	public boolean isReady() {
		//if player has taken their turn, return true
		return 	(p1.getTurns() > p2.getTurns() && currentPlayerTurn == 1)
				||
				(p1.getTurns() == p2.getTurns() && currentPlayerTurn == 2);
	}
	
	public void NextTurn() {
		if(currentPlayerTurn == 1) {
		 remove(p1);
		 add(p2, BorderLayout.CENTER);
		}
		else {
			remove(p2);
			add(p1, BorderLayout.CENTER);
		}
	}
	
}
