import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class PlaceShipsPlayer2 extends JFrame implements ActionListener {
	
	private GridLayout gridLayout;	
	private JButton[][] GameBoard;
	private int CurrentShipSize;	
	private int FirstClick_X;
	private int FirstClick_Y;
	private boolean OnFirstClick;		//set to true before choosing which ship to set, false after clicking button to place ship
	private Ship BattleFleet[];
	
	private int CurrentShip=0;
	private boolean GameInProgress=false;	//set to true after all 5 ships are placed
	private JPanel MainPanel;
	public JPanel ShipButtons;
	private JButton ClickedShipButton;
	
	private boolean BoardEnabled=false;
	private boolean CurrentlyPlacingShip=false;
	private int ShipsPlacedOnBoard=0;
	private ArrayList<Integer> CoordinatePair;
	
	
	
	public PlaceShipsPlayer2() {
		  super("Player 2  Place Your Ships!");
		  
		  gridLayout=new GridLayout(10,10);	
				  		  	
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
			for (int y=0;y<10;y++) {			//setting up battleship game board
				for (int x=0;x<10;x++) {
					GameBoard[y][x]=new JButton();
					GameBoard[y][x].setBackground(Color.WHITE);
					GameBoard[y][x].addActionListener(this);					
					mygrid.add(GameBoard[y][x]);
				}			
			}
		  DisableBoard("yes");
		  CoordinatePair=new ArrayList<Integer>();
		  add(MainPanel);
		  
		  
	}
		  																
	public void actionPerformed( ActionEvent event ) {					
					
		 for (int y=0;y<10;y++){										
				for (int x=0;x<10;x++) {			//code runs after clicked a button to set ship or when game has actually started
					
					 if (event.getSource()==GameBoard[y][x] && OnFirstClick==false && GameInProgress==false) {					
							if (SecondClickDrawsShip(y,x,CurrentShipSize)==true) { 		//true when a ship is placed on the game board
								OnFirstClick=true;										//resetting OnFirstClick back to true after this event
								ClickedShipButton.setBackground(Color.GRAY);			
								ClickedShipButton.setEnabled(false);
								DisableBoard("yes");
								CurrentlyPlacingShip=false;
								ShipsPlacedOnBoard++;
																	//if all ships have been placed, ShipsPlacedOnBoard=5
								if (ShipsPlacedOnBoard==5) {
									ShipButtons.getComponent(12).setBackground(Color.YELLOW);
									ShipButtons.getComponent(12).setEnabled(true);									
								}
																																															
								
							}
						break;	
					}
						
					else if (event.getSource()==GameBoard[y][x] && GameInProgress==true) {				
							CheckForHit(y,x);					
						}
					
			  }			
	   }//end boardgame for loop 
		 
		 
		 /////////////////////////////////////////////////
		 //      Buttons on left side of screen
		 ////////////////////////////////////////////////
		 for (int i=0;i<=8;i++) {
			 if (event.getSource()==ShipButtons.getComponent(i)) {
				 	if (CurrentlyPlacingShip==true) {
				 		ErasePossibleShipPaths(CurrentShipSize,"DeleteAllPaths");
				 		ClickedShipButton.setBackground(Color.WHITE);
				 	}
			 
				 	ClickedShipButton=(JButton)ShipButtons.getComponent(i);	
				 	ClickedShipButton.setBackground(Color.YELLOW);
				 	GetRandomSquare();
				 	FirstClick_X=CoordinatePair.get(1);
				 	System.out.println(FirstClick_X);
			 		FirstClick_Y=CoordinatePair.get(0);
			 		System.out.println(FirstClick_Y);
			 		DisableBoard("no");	
			 		OnFirstClick=false;
				 	CurrentlyPlacingShip=true;
			 		
				 	if(ClickedShipButton.getText().toString().contentEquals("Ship 1") ) {				 		
				 		CurrentShip=0;		
				 		CurrentShipSize=5;				 						 						 						 		
				 		DrawPossibleShipPaths(FirstClick_Y,FirstClick_X,5);
				 						 		
				 	}
				
				 	else if (ClickedShipButton.getText().toString().contentEquals("Ship 2") ){
				 		CurrentShip=1;		
				 		CurrentShipSize=4;				 						 						 						 		
				 		DrawPossibleShipPaths(FirstClick_Y,FirstClick_X,4);
				 	}
				 	else if (ClickedShipButton.getText().toString().contentEquals("Ship 3")) {
				 		CurrentShip=2;		
				 		CurrentShipSize=3;				 						 						 						 		
				 		DrawPossibleShipPaths(FirstClick_Y,FirstClick_X,3);
				 	}
				 	else if (ClickedShipButton.getText().toString().contentEquals("Ship 4") ){
				 		CurrentShip=3;		
				 		CurrentShipSize=3;				 						 						 						 		
				 		DrawPossibleShipPaths(FirstClick_Y,FirstClick_X,3);
				 	}
				 	else if (ClickedShipButton.getText().toString().contentEquals("Ship 5") ){
				 		CurrentShip=4;		
				 		CurrentShipSize=2;				 						 						 						 		
				 		DrawPossibleShipPaths(FirstClick_Y,FirstClick_X,2);
				 	}
				 	CoordinatePair.clear();
				 	break;
			}	 
		 
		 }//end for loop for ShipButtons
		 
		 
		 if (event.getSource()==ShipButtons.getComponent(10) ) {		//Reset Button
				ResetBoard();	//also disables board
				
			}
			
			else if (event.getSource()==ShipButtons.getComponent(12) ) {		//Ready Button    Clicking starts game
				//clicking this button starts game
				
				
				
					DisableBoard("no");
					GameInProgress=true;		
					for (int k=0;k<10;k++) {
						for (int j=0;j<10;j++) 
							GameBoard[k][j].setBackground(Color.BLUE);											
					}
					
				
			}//end else if
		
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
		
	public boolean SecondClickDrawsShip(int YClick, int XClick,int ShipLength) {		//if user chooses a ship path, ship is created
		if (GameBoard[YClick][XClick].getBackground()==Color.LIGHT_GRAY) {
			PlaceShipOnBoard(YClick,XClick,ShipLength);			
			return true;
		}
	
		else if (GameBoard[YClick][XClick].getBackground()==Color.WHITE) {			//if user chooses a blank square to view ship paths
			ErasePossibleShipPaths(ShipLength, "EraseAllPaths");
			DrawPossibleShipPaths(YClick,XClick,ShipLength);
			FirstClick_X=XClick;
			FirstClick_Y=YClick;
			return false;
		}
									
		else					//if user selects a black square, nothing should happen
		  return false;
		
		
	}
	
	public void PlaceShipOnBoard(int SecondClick_Y,int SecondClick_X,int ShipLength) {		//drawing ship on board
		
		
		for (int i=1;i<ShipLength;i++) {				//finding which direction the user clicked
			
			if (FirstClick_Y+i==SecondClick_Y && FirstClick_X==SecondClick_X) {			//clicking south path, drawing south path
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
	
	
	public void ErasePossibleShipPaths(int ShipLength,String PathToKeep) {	
		
		for (int i=0;i<ShipLength;i++){				//watches out for other ships on the board and board boundaries		
			
			if (PathToKeep!="KeepSouth") {			//only one path is kept when user clicks to create a ship, must erase all other paths
			
				if(FirstClick_Y+ShipLength-1<=9) {
					if (GameBoard[FirstClick_Y+i][FirstClick_X].getBackground()==Color.LIGHT_GRAY) {					
						GameBoard[FirstClick_Y+i][FirstClick_X].setBackground(Color.WHITE);				
					}				
				}					
			}	
		
		if (PathToKeep!="KeepNorth") {	
		
			if(FirstClick_Y-ShipLength+1>=0) {
				if (GameBoard[FirstClick_Y-i][FirstClick_X].getBackground()==Color.LIGHT_GRAY) {					
					GameBoard[FirstClick_Y-i][FirstClick_X].setBackground(Color.WHITE);			
				}
			}			
		}	
					
		if (PathToKeep!="KeepEast") {	
		
			if(FirstClick_X+ShipLength-1<=9) {
				if (GameBoard[FirstClick_Y][FirstClick_X+i].getBackground()==Color.LIGHT_GRAY) {					
					GameBoard[FirstClick_Y][FirstClick_X+i].setBackground(Color.WHITE);
				}
			}			
		}
				
		if (PathToKeep!="KeepWest") {	
		
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
		int DontDrawSouthFlag=0;	
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
		int ShipLength=5;
		for (int i=0;i<5;i++) {
			if (i==3)							//There are two ships of size 3 in classic battleship
				ShipLength++;
			
			BattleFleet[i]=new Ship(ShipLength);
			ShipLength--;
		}
	}
	
	public void CreateButtonsOnLeftColumn() {					//adding buttons to left column
		  ShipButtons=new JPanel();	
		  BoxLayout boxlayout=new BoxLayout(ShipButtons,BoxLayout.Y_AXIS);	  
		  ShipButtons.setLayout(boxlayout);	 
		 
		  for (int i=0;i<7;i++) {			  
			  JButton shipbutton=new JButton();
			  shipbutton.setBackground(Color.WHITE);
			  shipbutton.addActionListener(this);
			  
			  if (i<=4) {
				  shipbutton.setText("Ship " + (i+1) );
				  ShipButtons.add(shipbutton);
				  ShipButtons.add(Box.createRigidArea(new Dimension(0, 15)));
			  }
			  
			  else if (i==5) {
				  shipbutton.setText("Reset");
				  shipbutton.setBackground(Color.BLACK);
				  shipbutton.setForeground(Color.RED);
				  ShipButtons.add(shipbutton);
				  ShipButtons.add(Box.createRigidArea(new Dimension(0, 30)));
			  }
			  
			  else {
				  shipbutton.setText("Ready");
				  shipbutton.setBackground(Color.LIGHT_GRAY);
				  shipbutton.setEnabled(false);
				  ShipButtons.add(shipbutton);
				  
			  }
			  
		  }
		  
		  MainPanel.add(ShipButtons);			
	}
	
	public void ResetBoard() {
		for (int i=0;i<5;i++) {
			BattleFleet[i].ResetShipCoordinates();
		}
		
		
		//FirstClick_X=FirstClick_Y=0;
		//CurrentShip=4;
		for (int i=0;i<10;i++) {
			for (int j=0;j<10;j++) {
				GameBoard[i][j].setBackground(Color.WHITE);
			}
		}
		DisableBoard("yes");
		OnFirstClick=true;
		ShipsPlacedOnBoard=0;
		for (int i=0;i<=8;i++) {										//resetting gray ship buttons on left
			 if (ShipButtons.getComponent(i) instanceof JButton) {
				 	JButton button=(JButton)ShipButtons.getComponent(i);
				 	button.setBackground(Color.WHITE);
				 	button.setEnabled(true);
			 }
		}
				
		ShipButtons.getComponent(12).setBackground(Color.LIGHT_GRAY);
		ShipButtons.getComponent(12).setEnabled(false);
	}
	
	public void DisableBoard(String choice) {
		if (choice=="yes") {
			for (int i=0;i<10;i++) {
				for (int j=0;j<10;j++) {
					GameBoard[i][j].setEnabled(false);
				}
			}
			
		}
		
		else if (choice=="no") {
			for (int i=0;i<10;i++) {
				for (int j=0;j<10;j++) {
					GameBoard[i][j].setEnabled(true);
				}
			}
			
		}
		
	}
	
	public void GetRandomSquare() {		
		 int xcoord,ycoord;
		 Random random=new Random();
		
		do
		{
			xcoord=random.nextInt(10);
			ycoord=random.nextInt(10);
			if (GameBoard[ycoord][xcoord].getBackground()==Color.WHITE ) {
				CoordinatePair.add(ycoord);
				CoordinatePair.add(xcoord);
				return;
			}
			
		}while(true);							
	}
	
	
	

}
