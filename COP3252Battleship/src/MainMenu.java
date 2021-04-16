import java.awt.*;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
public class MainMenu extends JFrame
{
	
	public static void main(String[] args)
	{
		MainMenu menu=new MainMenu(800,400);
		
		try {										    
			AudioInputStream Effect=AudioSystem.getAudioInputStream(new File(".//src//loadscreenmusic.wav"));
			Clip PlaySound=AudioSystem.getClip();
			PlaySound.open(Effect);												//https://mixkit.co/free-sound-effects/win/
			PlaySound.start();			    
		}

		catch(Exception e) {
			System.out.println("Error playing main menu sound effect");
		}
		
	}
	
	public static JFrame frame;
	public static JButton start;
	public static JButton rules;
	private Font MenuFont;
	public MainMenu(int height, int width)
	{
		MenuFont = new Font("Calibri", Font.BOLD, 40);
		frame=new JFrame();
		frame.setTitle("Battleship");			
		frame.setPreferredSize(new Dimension(height, width));
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

		Icon mainicon = new ImageIcon( ".//src//boardimage.jpeg" );		//adobe stock photo obtained with free membership trial
				
		JPanel mainpanel=new JPanel()
		{
					
			protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.drawImage(((ImageIcon) mainicon).getImage(), 0,0, null);
				
			}
		};
	
		//mainpanel.setBackground(bug1);                           //trying
		mainpanel.setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		JLabel label=new JLabel("Welcome to Battleship!");
		label.setForeground(Color.WHITE); 
		label.setFont(MenuFont);
		c.fill=GridBagConstraints.CENTER;
		c.insets=new Insets(2,2,100,2);
		c.gridx=1;
		c.gridy=0;
		c.anchor=GridBagConstraints.PAGE_START;
		mainpanel.add(label,c);
		start=new JButton("New Game");		
		start.addActionListener(new Options());
		
		start.setPreferredSize(new Dimension(150, 70));
		start.setBackground(Color.WHITE);
		start.setFocusable(false);
		c.fill=GridBagConstraints.CENTER;
		c.insets=new Insets(5,5,5,5);
		c.gridx=1;
		c.gridy=3;
		c.anchor=GridBagConstraints.CENTER;
		mainpanel.add(start,c);
		rules=new JButton("Instructions");
		rules.addActionListener(new Options());
		rules.setPreferredSize(new Dimension(150, 70));
		rules.setBackground(Color.WHITE);
		rules.setFocusable(false);
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