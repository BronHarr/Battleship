import java.awt.*;
import javax.swing.*;
public class Instructions extends JFrame
{
    static JButton mainmenu;
    public static JFrame rulesframe;
	public static JButton exit;
	public Instructions(int h,int w) 
	{
		rulesframe=new JFrame();
		rulesframe.setTitle("Battleship Instructions");
		rulesframe.setPreferredSize(new Dimension(h,w));
		rulesframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
		Icon mainicon = new ImageIcon( ".//src//boardimage.jpeg" );		//adobe stock photo obtained with free membership trial		
		JPanel rulespanel=new JPanel()
		{
					
			protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.drawImage(((ImageIcon) mainicon).getImage(), 0,0, null);
				
			}
		};
		
		
		JTextArea text=new JTextArea("Game Instructions\n1) At the start of the game each player will place their ships while the other looks away.\n2) Once the ships are placed the players will take turns selecting on the grid to shoot.(Ships will be hidden to both players)\n3) Winner is the first person to sink all enemy ships.\n");
		text.setEditable(false);
		text.setRows(10);
		text.setColumns(10);		
		rulespanel.add(text);
	    rulesframe.add(rulespanel,"Center");
		rulesframe.add(rulespanel);
		rulesframe.setResizable(true);
		rulesframe.setVisible(true);
		rulesframe.pack();
		JButton button = new JButton("Exit Intructions");
		button.setBounds(300,200,150,75);
		rulesframe.add(button);
		rulesframe.setLayout(null);
      	rulesframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      	button.addActionListener(e->{rulesframe.dispose();});
    	}
}
