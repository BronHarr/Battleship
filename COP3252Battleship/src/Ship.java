import java.awt.Button;
import java.awt.Color;

import javax.swing.JButton;

public class Ship {
	
	//version on github

	private JButton[][] LocationOnGameBoard;
	private int ShipSize;
	private int HitCounter;

	
	public Ship(int ShipSize) {
		this.ShipSize=ShipSize;
		LocationOnGameBoard=new JButton[10][10];
		HitCounter=0;
		
		for (int i=0;i<10;i++) {
			for (int j=0;j<10;j++) {
				LocationOnGameBoard[i][j]=new JButton();
				LocationOnGameBoard[i][j].setEnabled(false);;				
			}
		}
	}
	
	public int GetShipSize() {
		return ShipSize;
	}
	
	public void AddShipCoordinates(int y,int x) {
			LocationOnGameBoard[y][x].setEnabled(true);;		//using enabled for a valid coordinate
	}
	
	public boolean IsAHit(int y,int x) {
		
		if (LocationOnGameBoard[y][x].isEnabled()) {
				HitCounter++;
				return true;
		}
		return false;
	}
	
	
	
	public boolean ShipHasSunk() {
		return (HitCounter==ShipSize);
		
	}
	
	public void ResetShipCoordinates() {
		
		for (int i=0;i<10;i++) {
			for (int j=0;j<10;j++) {
				if (LocationOnGameBoard[i][j].isEnabled()) {
					LocationOnGameBoard[i][j].setEnabled(false);
				}											
			}
		}		
	}
	
}
