public class Ship{
		
	private boolean[][] LocationOnGameBoard;
	private int ShipSize;
	private int HitCounter;
	private String ShipName;
	
	public Ship(int ShipSize, String name) {
		this.ShipSize=ShipSize;
		LocationOnGameBoard=new boolean[10][10];
		HitCounter=0;
		ShipName=name;
		
		for (int i=0;i<10;i++) {
			for (int j=0;j<10;j++) {				
				LocationOnGameBoard[i][j] = false;				
			}
		}
	}
	
	public String GetShipName() {
		return ShipName;
	}
	
	public int GetShipSize() {
		return ShipSize;
	}
	
	public void AddShipCoordinates(int y,int x) {
			LocationOnGameBoard[y][x] = true;		//using enabled for a valid coordinate
	}
	
	public boolean IsAHit(int y,int x) {
		
		if (LocationOnGameBoard[y][x]) {
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
				if (LocationOnGameBoard[i][j]) {
					LocationOnGameBoard[i][j] = false;
				}											
			}
		}		
	}
}
