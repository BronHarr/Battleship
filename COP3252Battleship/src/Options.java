
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
			PlaceShips StartGame=new PlaceShips();
			StartGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			StartGame.setSize(500, 400);
			StartGame.setResizable(true);		
			StartGame.setVisible(true);
			////////////////////////////////////////////////////////////////////////
		}
		if(event.getSource()==MainMenu.rules)
		{
			Instructions rules=new Instructions(800,400);
			
		}
	}
}
