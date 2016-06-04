package fraudster.controller;

import fraudster.engine.*;
import fraudster.view.*;

import java.util.Random;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class MainGraphical
{
	public static void main(String[] args)
	{
		JFrame mainFrame;
		MainPanel contentPanel;
		
		
		Ledger theLedger = new Ledger();
		theLedger.populate();
		
		mainFrame = new JFrame("Fraudster");
		mainFrame.setPreferredSize(new Dimension(1066, 625));
		mainFrame.setResizable(false);
		mainFrame.setLocation(200,200);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setContentPane(contentPanel = new MainPanel("../assets/screen-small-sticker.png"));
		
		contentPanel.setLayout(new BorderLayout());
		
		
		/*
		JLabel spacer = new JLabel(" ");
		spacer.setPreferredSize(new Dimension(70,50));
		contentPanel.add(spacer, BorderLayout.LINE_START);
		contentPanel.add(spacer, BorderLayout.PAGE_START);
		contentPanel.add(spacer, BorderLayout.PAGE_END);
		*/
		
		JPanel screen = new JPanel();
		screen.setPreferredSize(new Dimension(630,500));
		screen.setBorder(new EmptyBorder(45,60,50,0));
		screen.setOpaque(false);
			String map =
					"               ,_   .  ._. _.  ."+"\n"+
					"           , _-\\','|~\\~      ~/      ;-'_   _-'     ,;_;_,    ~~-"+"\n"+
					"  /~~-\\_/-'~'--' \\~~| ',    ,'      /  / ~|-_\\_/~/~      ~~--~~~~'--_"+"\n"+
					"  /              ,/'-/~ '\\ ,' _  , '|,'|~                   ._/-, /~"+"\n"+
					"  ~/-'~\\_,       '-,| '|. '   ~  ,\\ /'~                /    /_  /~"+"\n"+
					".-~      '|        '',\\~|\\       _\\~     ,_  ,               /|"+"\n"+
					"          '\\        /'~          |_/~\\\\,-,~  \\ \"         ,_,/ |"+"\n"+
					"           |       /            ._-~'\\_ _~|              \\ ) /"+"\n"+
					"            \\   __-\\           '/      ~ |\\  \\_          /  ~"+"\n"+
					"  .,         '\\ |,  ~-_      - |          \\\\_' ~|  /\\  \\~ ,"+"\n"+
					"               ~-_'  _;       '\\           '-,   \\,' /\\/  |"+"\n"+
					"                 '\\_,~'\\_       \\_ _,       /'    '  |, /|'"+"\n"+
					"                   /     \\_       ~ |      /         \\  ~'; -,_."+"\n"+
					"                   |       ~\\        |    |  ,        '-_, ,; ~ ~\\"+"\n"+
					"                    \\,      /        \\    / /|            ,-, ,   -,"+"\n"+
					"                     |    ,/          |  |' |/          ,-   ~ \\   '."+"\n"+
					"                    ,|   ,/           \\ ,/              \\       |"+"\n"+
					"                    /    |             ~                 -~~-, /   _"+"\n"+
					"                    |  ,-'                                    ~    /"+"\n"+
					"                    / ,'                                      ~"+"\n"+
					"                    ',|  ~";
			JTextArea terminal = new JTextArea("     WELCOME ON YOUR WORLD INITIATIVE AGAINST FINANCIAL FRAUD TERMINAL\n"+map); //hand alignment, hell yeah
			terminal.setPreferredSize(new Dimension(630,500));
			terminal.setBorder(new EmptyBorder(0,0,0,0));
			terminal.setBackground(Color.BLACK);
			terminal.setForeground(Color.GREEN);
			terminal.setCaretColor(Color.GREEN);
			terminal.setFont(new Font("Courier New", Font.BOLD, 14));
			screen.add(terminal);
		contentPanel.add(screen, BorderLayout.CENTER);
		
		JPanel buttons = new JPanel();
		buttons.setPreferredSize(new Dimension(330, 550));
		buttons.setBorder(new EmptyBorder(20,20,120,20));
		buttons.setLayout(new GridLayout(0,2));
		buttons.setOpaque(false);
			int buttonX = 150, buttonY = 90 ;
			
			JButton nextDay = new JButton();
			try
			{ nextDay.setIcon(new ImageIcon(ImageIO.read(new File("../assets/button-next-day.png")).getScaledInstance(buttonX, buttonY, Image.SCALE_SMOOTH))); }
			catch (IOException e)
			{ System.err.println("Couldn't open button images"); }
			buttons.add(nextDay);
			buttons.add(new JLabel(""));buttons.add(new JLabel(""));buttons.add(new JLabel(""));
			
			JButton home = new JButton();
			try
			{ home.setIcon(new ImageIcon((ImageIO.read(new File("../assets/button-home.png"))).getScaledInstance(buttonX, buttonY, Image.SCALE_SMOOTH))); }
			catch (IOException e)
			{ System.err.println("Couldn't open button images"); }
			buttons.add(home);
			
			
			
			JButton mail = new JButton();
			try
			{ mail.setIcon(new ImageIcon(ImageIO.read(new File("../assets/button-mail.png")).getScaledInstance(buttonX, buttonY, Image.SCALE_SMOOTH))); }
			catch (IOException e)
			{ System.err.println("Couldn't open button images"); }
			buttons.add(mail);
			
			
			JButton embassies = new JButton();
			try
			{ embassies.setIcon(new ImageIcon(ImageIO.read(new File("../assets/button-embassies.png")).getScaledInstance(buttonX, buttonY, Image.SCALE_SMOOTH))); }
			catch (IOException e)
			{ System.err.println("Couldn't open button images"); }
			buttons.add(embassies);
			
			JButton worldInit = new JButton();
			try
			{ worldInit.setIcon(new ImageIcon(ImageIO.read(new File("../assets/button-world-init.png")).getScaledInstance(buttonX, buttonY, Image.SCALE_SMOOTH))); }
			catch (IOException e)
			{ System.err.println("Couldn't open button images"); }
			buttons.add(worldInit);
			
			
			JButton help = new JButton();
			try
			{ help.setIcon(new ImageIcon(ImageIO.read(new File("../assets/button-help.png")).getScaledInstance(buttonX, buttonY, Image.SCALE_SMOOTH))); }
			catch (IOException e)
			{ System.err.println("Couldn't open button images"); }
			buttons.add(help);
			
			JButton coffee = new JButton();
			try
			{ coffee.setIcon(new ImageIcon(ImageIO.read(new File("../assets/button-coffee.png")).getScaledInstance(buttonX, buttonY, Image.SCALE_SMOOTH))); }
			catch (IOException e)
			{ System.err.println("Couldn't open button images"); }
			buttons.add(coffee);
			
			
		contentPanel.add(buttons, BorderLayout.LINE_END);
		
		
		
		
		
		
		
		mainFrame.pack();
		mainFrame.setVisible(true);
		
	}
	
}