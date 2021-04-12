
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
public class Options implements ActionListener
{
	public Options(){}
	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource()==MainMenu.start)
		{
			
			MainMenu.frame.setVisible(false);
			
			/////////////////////////////////////////////////////////////////////////	Game Begins
			PlaceShips Player1=new PlaceShips();
			Player1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Player1.setSize(500, 400);
			Player1.setResizable(true);		
			Player1.setVisible(true);
			////////////////////////////////////////////////////////////////////////

		}
		if(event.getSource()==MainMenu.rules)
		{
			Instructions rules=new Instructions(800,400);
			
		}
	}
}
