
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;
public class Instructions extends JFrame
{
    	static JButton mainmenu;
    	public static JFrame rulesframe;
	public Instructions(int h,int w) 
	{
		rulesframe=new JFrame();
		rulesframe.setTitle("Battleship Instructions");
		rulesframe.setPreferredSize(new Dimension(h,w));
		rulesframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel rulespanel=new JPanel();
		JTextArea text=new JTextArea("Game Instructions\n1) At the start of the game each player will place their ships while the other looks away.\n2) Once the ships are placed the players will take turns selecting on the grid to shoot.(Ships will be hidden to both players)\n3) Winner is the first person to sink all enemy ships.\n");
		text.setRows(10);
		text.setColumns(10);		
		rulespanel.add(text);
	    	rulesframe.add(rulespanel,"Center");
		rulesframe.add(rulespanel);
		rulesframe.setResizable(true);
		rulesframe.setVisible(true);
		rulesframe.pack();
    	}
}
