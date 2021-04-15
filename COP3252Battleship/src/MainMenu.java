import java.awt.*;
import javax.swing.*;
public class MainMenu extends JFrame
{
	public static void main(String[] args)
	{
		MainMenu menu=new MainMenu(800,400);
	}
	public static JFrame frame;
	public static JButton start;
	public static JButton rules;
	public MainMenu(int height, int width)
	{
		frame=new JFrame();
		frame.setTitle("Battleship");			
		frame.setPreferredSize(new Dimension(height, width));
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel mainpanel=new JPanel();
		//mainpanel.setBackground(Color.black);                           //trying
		mainpanel.setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		JLabel label=new JLabel("Welcome to Battleship!");
		//label.setForeground(Color.RED);                              //trying		
		c.fill=GridBagConstraints.CENTER;
		c.insets=new Insets(2,2,100,2);
		c.gridx=1;
		c.gridy=0;
		c.anchor=GridBagConstraints.PAGE_START;
		mainpanel.add(label,c);
		start=new JButton("New Game");		
		start.addActionListener(new Options());
		c.fill=GridBagConstraints.CENTER;
		c.insets=new Insets(5,5,5,5);
		c.gridx=1;
		c.gridy=3;
		c.anchor=GridBagConstraints.CENTER;
		mainpanel.add(start,c);
		rules=new JButton("Instructions");
		rules.addActionListener(new Options());
		c.fill=GridBagConstraints.CENTER;
		c.insets=new Insets(10,2,2,2);
		c.gridx=1;
		c.gridy=4;
		c.anchor=GridBagConstraints.CENTER;
		mainpanel.add(rules,c);
		frame.add(mainpanel,"Center");
		frame.add(mainpanel);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
		
	}
}