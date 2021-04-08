import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlaceShips extends JFrame implements ActionListener {
	
	//version on github
	
	private GridLayout gridLayout;	
	private JButton[][] GameBoard;
	private int CurrentShipSize;	
	private int FirstClick_X;
	private int FirstClick_Y;
	private boolean OnFirstClick;
	private Ship BattleFleet[];
	
	private int CurrentShip=4;
	private boolean GameInProgress=false;
	private JPanel MainPanel;
	private JPanel AllButtons;
	 
	
	public PlaceShips() {
		  super("Place Ships");
		  
		  gridLayout=new GridLayout(10,10);			//original
		// jpanel=new JPanel(gridLayout);			//original
		  
		  	
		  MainPanel=new JPanel();
		  BoxLayout boxlayout1=new BoxLayout(MainPanel,BoxLayout.X_AXIS);
		  MainPanel.setLayout(boxlayout1);
		  
		  
		  CreateButtonsOnLeftColumn();		//adding buttons to left hand side	  
		  
		  
		  JPanel GridBoard=new JPanel();
		  BoxLayout boxlayout3=new BoxLayout(GridBoard,BoxLayout.Y_AXIS);	 
		  GridBoard.setLayout(boxlayout3);			
		  
		  
		  JPanel mygrid=new JPanel(gridLayout);		  
		  MainPanel.add(mygrid);
		  
		  
		  BattleFleet=new Ship[5];				//storing ship objects inside of array "BattleFleet"
		  InitializeShips();		  
		  OnFirstClick=true;
		  FirstClick_X=FirstClick_Y=0;
		  
		  GameBoard=new JButton[10][10];		
			for (int y=0;y<10;y++) {			
				for (int x=0;x<10;x++) {
					GameBoard[y][x]=new JButton();
					GameBoard[y][x].setBackground(Color.WHITE);
					GameBoard[y][x].addActionListener(this);					
					mygrid.add(GameBoard[y][x]);
				}			
			}
		  
		  add(MainPanel);
		  
		  
	}
		  																//when user clicks on button, its highlighted a color
	public void actionPerformed( ActionEvent event ) {					//gray out buttons after ship is placed
		if (CurrentShip>=0) 
			CurrentShipSize=BattleFleet[CurrentShip].GetShipSize();			
		
		
		 for (int y=0;y<10;y++){										
				for (int x=0;x<10;x++) {
					
					if (event.getSource()==GameBoard[y][x] && OnFirstClick==true && GameInProgress==false){		
						DrawPossibleShipPaths(y,x,CurrentShipSize);				
						OnFirstClick=false;
						FirstClick_Y=y;
						FirstClick_X=x;
						break;
						}
					
					else if (event.getSource()==GameBoard[y][x] && OnFirstClick==false && GameInProgress==false) {					
							if (SecondClickDrawsShip(y,x,CurrentShipSize)==true) { 		//only if true does the round end of placing the ship
								OnFirstClick=true;
								CurrentShip--;			//moves to next ship in BattleFleet once ship is placed
								
								if (CurrentShip<0) {		//this runs once all ships have been placed
											
									GameInProgress=true;		//Let the game begin!
									for (int i=0;i<10;i++) {
										for (int j=0;j<10;j++) {
											GameBoard[i][j].setBackground(Color.BLUE);
										}
									
									}
								}					
								break;
							}
					}
					
					
					
					
					/*
					else if (event.getSource()==AllButtons.getComponent(0) ) {		//Ship 1 Button
						JButton ShipButton=(JButton) AllButtons.getComponent(0);
						ShipButton.setBackground(Color.YELLOW);
					}
					
					else if (event.getSource()==AllButtons.getComponent(2) ) {		//Ship 2 Button
						System.out.println("works2");
					}
					
					else if (event.getSource()==AllButtons.getComponent(4) ) {		//Ship 3 Button
						System.out.println("works3");
					}
					
					else if (event.getSource()==AllButtons.getComponent(6) ) {		//Ship 4 Button
						System.out.println("works4");
					}
					
					else if (event.getSource()==AllButtons.getComponent(8) ) {		//Ship 5 Button
						System.out.println("works5");
					}
					*/
					
						
					else if (event.getSource()==GameBoard[y][x] && GameInProgress==true) {				
							CheckForHit(y,x);					
						}
					
					
					
			  }			
	   } 
		 
		 for (int i=0;i<=8;i++) {
			 if (event.getSource()==AllButtons.getComponent(i))
				 	System.out.println("hit");
				
			}	 
		 
		    if (event.getSource()==AllButtons.getComponent(10) ) {		//Reset Button
				System.out.println("works reset");
			}
			
			else if (event.getSource()==AllButtons.getComponent(12) ) {		//Ready Button
				System.out.println("works ready");
			}
			
		 
		 
  }
	
	public void CheckForHit(int y,int x) {
		for (int i=0;i<5;i++) {			
			if (BattleFleet[i].IsAHit(y,x)) {
					System.out.println("is a hit");										
					GameBoard[y][x].setBackground(Color.RED);										
					if (BattleFleet[i].ShipHasSunk()) {
						System.out.println("ship "+ (i+1) +" has sunk");
					}
					GameBoard[y][x].setEnabled(false);
					return;
			}
			
			else if (!BattleFleet[i].IsAHit(y, x)) {
				GameBoard[y][x].setBackground(Color.WHITE);
				GameBoard[y][x].setEnabled(false);
				System.out.println("not a hit");
				
			}
		}
			
		
		
	}
		
	public boolean SecondClickDrawsShip(int YClick, int XClick,int ShipLength) {		//if user chooses a ship path
		if (GameBoard[YClick][XClick].getBackground()==Color.LIGHT_GRAY) {
			PlaceShipOnBoard(YClick,XClick,ShipLength);			
			return true;
		}
	
		else if (GameBoard[YClick][XClick].getBackground()==Color.WHITE) {	//if user chooses a blank square to view ship paths
			ErasePossibleShipPaths(ShipLength, "EraseAllPaths");
			DrawPossibleShipPaths(YClick,XClick,ShipLength);
			FirstClick_X=XClick;
			FirstClick_Y=YClick;
			return false;
		}
									
		else					//if user selects original square(color is black)	
		  return false;
		
		
	}
	
	public void PlaceShipOnBoard(int SecondClick_Y,int SecondClick_X,int ShipLength) {		//drawing ship on board
		
		//if (GameBoard[SecondClick_Y][SecondClick_X].getBackground()==Color.WHITE)
			//return;
		
		
		for (int i=1;i<ShipLength;i++) {				//finding which direction the user clicked
			
			if (FirstClick_Y+i==SecondClick_Y && FirstClick_X==SecondClick_X) {			//clicking south path
				for (int j=1;j<ShipLength;j++) {			
					GameBoard[FirstClick_Y+j][FirstClick_X].setBackground(Color.BLACK);
					BattleFleet[CurrentShip].AddShipCoordinates(FirstClick_Y+j, FirstClick_X);
				}	
				ErasePossibleShipPaths(ShipLength,"KeepSouth");
				GameBoard[FirstClick_Y][FirstClick_X].setBackground(Color.BLACK);
				BattleFleet[CurrentShip].AddShipCoordinates(FirstClick_Y, FirstClick_X);
				return;
			}
			
			else if (FirstClick_Y-i==SecondClick_Y && FirstClick_X==SecondClick_X) {				//clicking north path
				for (int j=1;j<ShipLength;j++) {			
					GameBoard[FirstClick_Y-j][FirstClick_X].setBackground(Color.BLACK);
					BattleFleet[CurrentShip].AddShipCoordinates(FirstClick_Y-j, FirstClick_X);
				}	
				ErasePossibleShipPaths(ShipLength,"KeepNorth");
				GameBoard[FirstClick_Y][FirstClick_X].setBackground(Color.BLACK);
				BattleFleet[CurrentShip].AddShipCoordinates(FirstClick_Y, FirstClick_X);
				return;
			}
			
			else if (FirstClick_X+i==SecondClick_X && FirstClick_Y==SecondClick_Y) {			//clicking east path
				for (int j=1;j<ShipLength;j++) {			
					GameBoard[FirstClick_Y][FirstClick_X+j].setBackground(Color.BLACK);
					BattleFleet[CurrentShip].AddShipCoordinates(FirstClick_Y, FirstClick_X+j);
				}
				ErasePossibleShipPaths(ShipLength,"KeepEast");
				GameBoard[FirstClick_Y][FirstClick_X].setBackground(Color.BLACK);
				BattleFleet[CurrentShip].AddShipCoordinates(FirstClick_Y, FirstClick_X);
				return;
			}
			
			else if (FirstClick_X-i==SecondClick_X && FirstClick_Y==SecondClick_Y) {		//clicking west path
				for (int j=1;j<ShipLength;j++) {			
					GameBoard[FirstClick_Y][FirstClick_X-j].setBackground(Color.BLACK);	
					BattleFleet[CurrentShip].AddShipCoordinates(FirstClick_Y, FirstClick_X-j);
				}
				ErasePossibleShipPaths(ShipLength,"KeepWest");
				GameBoard[FirstClick_Y][FirstClick_X].setBackground(Color.BLACK);
				BattleFleet[CurrentShip].AddShipCoordinates(FirstClick_Y, FirstClick_X);
				return;
			}
			
			
		}
		
	}
	
	
	public void ErasePossibleShipPaths(int ShipLength,String PathsToKeep) {		
		for (int i=0;i<ShipLength;i++){				//need to watch out for other ships on the board and boundaries
		
		if (PathsToKeep!="KeepSouth") {	
			
			if(FirstClick_Y+ShipLength-1<=9) {
				if (GameBoard[FirstClick_Y+i][FirstClick_X].getBackground()==Color.LIGHT_GRAY) {					
					GameBoard[FirstClick_Y+i][FirstClick_X].setBackground(Color.WHITE);				
				}
				
			}
								
		}	
			
		
		
		if (PathsToKeep!="KeepNorth") {	
		
			if(FirstClick_Y-ShipLength+1>=0) {
				if (GameBoard[FirstClick_Y-i][FirstClick_X].getBackground()==Color.LIGHT_GRAY) {					
					GameBoard[FirstClick_Y-i][FirstClick_X].setBackground(Color.WHITE);			
				}
			}
			
		}	
			
		
		if (PathsToKeep!="KeepEast") {	
		
			if(FirstClick_X+ShipLength-1<=9) {
				if (GameBoard[FirstClick_Y][FirstClick_X+i].getBackground()==Color.LIGHT_GRAY) {					
					GameBoard[FirstClick_Y][FirstClick_X+i].setBackground(Color.WHITE);
				}
			}
			
		}
		
		
		if (PathsToKeep!="KeepWest") {	
		
			if(FirstClick_X-ShipLength+1>=0) {
				if (GameBoard[FirstClick_Y][FirstClick_X-i].getBackground()==Color.LIGHT_GRAY) {					
					GameBoard[FirstClick_Y][FirstClick_X-i].setBackground(Color.WHITE);	
				}
			}
		}	
			
		}
		
		GameBoard[FirstClick_Y][FirstClick_X].setBackground(Color.WHITE);
		
	}
	
	public void DrawPossibleShipPaths(int Y,int X,int Length) {		
		int DontDrawSouthFlag=0;	//first one
		int DontDrawNorthFlag=0;
		int DontDrawEastFlag=0;
		int DontDrawWestFlag=0;
		
		
		if (Y+Length-1<=9) {				//checking boundary of gameboard first
			for (int i=0;i<Length;i++) {	//checking if path collides with already placed ship
				if (GameBoard[Y+i][X].getBackground()==Color.BLACK)
					DontDrawSouthFlag++;
			}
			
			
			if (DontDrawSouthFlag==0) {		
				for (int i=0;i<Length;i++) 
					GameBoard[Y+i][X].setBackground(Color.LIGHT_GRAY);
				}
			
		}
				
		
		if (Y-Length+1>=0) {			//draws north line
			
			for (int i=0;i<Length;i++) {
				if (GameBoard[Y-i][X].getBackground()==Color.BLACK) {		
					DontDrawNorthFlag++;
				}
			}
			
			if (DontDrawNorthFlag==0) {
				for (int i=0;i<Length;i++) 
					GameBoard[Y-i][X].setBackground(Color.LIGHT_GRAY);
				
			}
			
		}
		
				
		if (X+Length-1<=9) {		//draws east line
		
			for (int i=0;i<Length;i++) {
				if (GameBoard[Y][X+i].getBackground()==Color.BLACK)
					DontDrawEastFlag++;
			}
			
			if (DontDrawEastFlag==0) {	
				for (int i=0;i<Length;i++) 
					GameBoard[Y][X+i].setBackground(Color.LIGHT_GRAY);
			}
				
					
		}	
				
		if (X-Length+1>=0) {		//draws west line
		
			for (int i=0;i<Length;i++) {
				if (GameBoard[Y][X-i].getBackground()==Color.BLACK)
					DontDrawWestFlag++;
			}
			
			if (DontDrawWestFlag==0) {				
				for (int i=0;i<Length;i++) 
					GameBoard[Y][X-i].setBackground(Color.LIGHT_GRAY);
			}
			 
		}
		
		if (DontDrawWestFlag!=0 && DontDrawEastFlag!=0 && DontDrawNorthFlag!=0 && DontDrawSouthFlag!=0)			
			GameBoard[Y][X].setBackground(Color.WHITE);				
			
		
		else
		   GameBoard[Y][X].setBackground(Color.BLACK);
	}
	
	public void InitializeShips() {
		int ShipLength=2;
		for (int i=0;i<5;i++) {
			if (i==2)							//There are two ships of size 3 in classic battleship
				ShipLength--;
			
			BattleFleet[i]=new Ship(ShipLength);
			ShipLength++;
		}
	}
	
	public void CreateButtonsOnLeftColumn() {
		  AllButtons=new JPanel();	
		  BoxLayout boxlayout=new BoxLayout(AllButtons,BoxLayout.Y_AXIS);	  
		  AllButtons.setLayout(boxlayout);	 
		 
		  for (int i=0;i<7;i++) {			  
			  JButton shipbutton=new JButton();
			  shipbutton.addActionListener(this);
			  
			  if (i<=4) {
				  shipbutton.setText("Ship " + (i+1) );
				  AllButtons.add(shipbutton);
				  AllButtons.add(Box.createRigidArea(new Dimension(0, 15)));
			  }
			  
			  else if (i==5) {
				  shipbutton.setText("Reset");
				  shipbutton.setBackground(Color.BLACK);
				  shipbutton.setForeground(Color.RED);
				  AllButtons.add(shipbutton);
				  AllButtons.add(Box.createRigidArea(new Dimension(0, 30)));
			  }
			  
			  else {
				  shipbutton.setText("Ready");
				  shipbutton.setBackground(Color.YELLOW);
				  AllButtons.add(shipbutton);
				  shipbutton.setVisible(false);
			  }
			  
		  }
		  
		  MainPanel.add(AllButtons);	
		
	}
	
/*
	public static void main(String[] args) {
		PlaceShips StartGame=new PlaceShips();
		StartGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		StartGame.setSize(400, 300);
		StartGame.setResizable(true);		
		StartGame.setVisible(true);


	}
*/
}
