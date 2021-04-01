import javax.swing.JFrame;

public class Battleship {

	public static void main(String[] args) {
		BattleShipFrame game = new BattleShipFrame();
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setSize(1000, 1000);
		game.setResizable(true);
		game.setVisible(true);

	}

}
