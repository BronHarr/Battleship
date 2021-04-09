
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
			
			/////////////////////////////////////////////////////////////////////////	Ship placement begins
			PlaceShips StartGame=new PlaceShips();
			
			////////////////////////////////////////////////////////////////////////
		}
		if(event.getSource()==MainMenu.rules)
		{
			Instructions rules=new Instructions(800,400);
			
		}
		
		
	}
}
