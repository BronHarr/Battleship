import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class BattleShipFrame extends JFrame implements ActionListener{
	
	private Container container;
	private BorderLayout gui; 		//South region has a static label that can be changed depending on event
	public static PlayerMove p1;
	public static Player2Move p2;
	public static JLabel TurnLabel;
	public static JLabel NotificationLabel;
	public static JButton Back2Menu;
	private boolean gameover = false;
	
	private int currentPlayerTurn;
	private Ship[] p1Fleet;
	private Ship[] p2Fleet;
	
	private String p1Str = "<html><font size=+36>Player ONE</font><html> " ;
	private String p2Str = "<html><font size=+36>Player TWO</font><html> " ;
	private final String FIRE = "<html><br><center><p><font color=red><font size=+40>F I R E !</font></font><p><html>"; 
	
	private Timer timer;
	private int delay = 2000;		
	
	
	
	public BattleShipFrame(){
		
		p1Fleet = PlaceShips.BattleFleet;
		p2Fleet = PlaceShipsPlayer2.BattleFleet;
		TurnLabel = new JLabel(p1Str + FIRE);
		TurnLabel.setHorizontalAlignment(JLabel.CENTER);
		TurnLabel.setVerticalAlignment(JLabel.CENTER);
		TurnLabel.setBackground(Color.black);
		TurnLabel.setForeground(Color.white);
		NotificationLabel = new JLabel("<html><font size=+20>...</font><html>");
		NotificationLabel.setHorizontalAlignment(JLabel.CENTER);
		NotificationLabel.setForeground(Color.white);
		gui = new BorderLayout();
		setSize(800,700);
		setLayout(gui);
		container = getContentPane();
		p2 = new Player2Move(); 
		p1 = new PlayerMove();
		currentPlayerTurn = 2;

		add(p2, BorderLayout.CENTER);
		add(TurnLabel, BorderLayout.SOUTH);
		add(NotificationLabel, BorderLayout.NORTH);
		setVisible(true);
		//once a second, check if board is ready to switch players
		timer = new Timer(delay, this);
		timer.start();
		container.setBackground(Color.black);
		this.setResizable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	public void actionPerformed(ActionEvent event) { 
		if(event.getSource() == p1 || event.getSource() == p2) {} //w.o this line game can crash if first turn
																 //click happens too soon
		else if(turnTaken() && !gameover) {//turn has been taken							
			timer.stop();
			
			int sunken = FleetSunk();//count sunk ships on both sides
			if(sunken == 1) { //p1 fleet is destroyed
				p1.TurnBoardOff();
				p2.TurnBoardOff();
				gameover = true;
				NotificationLabel.setText( "<html><Font size=+50>Player Two Wins!</font>");
				NotificationLabel.setForeground(Color.yellow);
				Back2Menu = new JButton("MAIN MENU");
				Back2Menu.addActionListener(this);
				remove(TurnLabel);
				add(Back2Menu, BorderLayout.SOUTH);
				revalidate();
				repaint();
				PlayWinningSound();
			}
			else if(sunken == 2) { //p2 fleet is destroyed
				p1.TurnBoardOff();
				p2.TurnBoardOff();
				gameover = true;
				NotificationLabel.setText( "<html><Font size=+50>Player One Wins!</font>");
				NotificationLabel.setForeground(Color.yellow);
				Back2Menu = new JButton("MAIN MENU");
				Back2Menu.addActionListener(this);
				remove(TurnLabel);
				Back2Menu = new JButton("MAIN MENU");
				Back2Menu.addActionListener(this);
				remove(TurnLabel);
				add(Back2Menu, BorderLayout.SOUTH);
				revalidate();
				repaint();
				PlayWinningSound();
			}
			else {
				p1.TurnBoardOn();
				p2.TurnBoardOn();
 				NextTurn();
			}
		}
		
		if(event.getSource() == Back2Menu){
			MainMenu menu = new MainMenu(800,400);
			dispose();
			
		}
		///////////////////////////////////////////////////////////////////////////////////////////////
	}///////////////////////////////////////////////////////////////////////////////////////////////////
	//returns 1 or 2 if either player has lost all ships
	
	public void PlayWinningSound() {
		try {										    
			AudioInputStream Effect=AudioSystem.getAudioInputStream(new File(".//src//winning sound.wav"));
			Clip PlaySound=AudioSystem.getClip();		// playing free sound effect from https://mixkit.co/free-sound-effects/win/
			PlaySound.open(Effect);			
			PlaySound.start();			    
		}

		catch(Exception e) {
			System.out.println("Error playing winning effect");
		}
		
	}
	
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

	public boolean turnTaken() {
		//if current player has taken their turn, return true
		return 	(p2.getTurns() > p1.getTurns() && currentPlayerTurn == 1) || (p2.getTurns() == p1.getTurns() && currentPlayerTurn == 2);
	}
	
	public void NextTurn() {
		TurnLabel.setForeground(Color.WHITE);
		NotificationLabel.setText("<html><font size=+20>...</font><html>" );
		NotificationLabel.setForeground(Color.WHITE);
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