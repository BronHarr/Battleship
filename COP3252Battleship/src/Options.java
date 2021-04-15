
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;



public class Options implements ActionListener
{
	public Options(){}
	
	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource()==MainMenu.start)
		{
			
			MainMenu.frame.setVisible(false);
			PlaceShips StartGame=new PlaceShips();		//Ship placement begins			
			
		}
		if(event.getSource()==MainMenu.rules)
		{
			Instructions rules=new Instructions(800,400);
			
			
			
		}
		
	}
}
