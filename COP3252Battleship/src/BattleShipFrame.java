import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BattleShipFrame extends JFrame {
	private Container container;
	private BorderLayout gui; //north and west regions for coordinate labels, south and east for gui
	private String axis_y; 
	private JPanel p1_board; //each player will have different board states to be swapped in the center region
	private JPanel p2_board;
	private GridLayout gridLayout;
	
	
	public BattleShipFrame(){
		super();
		
		gui = new BorderLayout(40,20);
		setLayout(gui);
		container = getContentPane();
		p1_board = new JPanel(); // TODO: JPanel graphics
		p1_board.setLayout( new GridLayout(10, 10, 2, 2));
		p1_board.setBackground(Color.black);
		p1_board.setSize(1000, 1000);
		p2_board = new JPanel();
		p2_board.setLayout( new GridLayout(10, 10));
		
		for(int i = 0; i < 100; i++) {
			p1_board.add(new WaterPanel());
			p2_board.add(new WaterPanel());
		}
		//TODO: initialize ships
		add(p1_board, BorderLayout.CENTER);
		
	}
	
}
