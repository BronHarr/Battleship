
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Options implements ActionListener
{
	public Options(){}
	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource()==MainMenu.start)
		{
			//PlaceShips();
			MainMenu.frame.setVisible(false);
		}
		if(event.getSource()==MainMenu.rules)
		{
			Instructions rules=new Instructions(800,400);
		}
	}
}
