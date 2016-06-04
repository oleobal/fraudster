package fraudster.view;

import fraudster.controller.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.util.Random;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class MainFrame extends JFrame implements ActionListener
{
	MainGraphical main;
	boolean startUpInProgress; //true if this is the beginning and we're asking the player for a name
	
	MainPanel contentPanel;
	JPanel buttons, commandZone;
	
	JTextArea terminal;
	JScrollPane scrollTerm;
	JTextField commandField; JLabel commandLabel;
	JPanel screen;
	JButton nextDay, home, mail, embassies, worldInit, help, coffee, debug;
	public MainFrame(String s, MainGraphical m)
	{
		super(s);
		main = m;
		this.setPreferredSize(new Dimension(1066, 625));
		this.setResizable(false);
		this.setLocation(200,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(contentPanel = new MainPanel("../assets/screen-small-sticker.png"));
		
		contentPanel.setLayout(new BorderLayout());
		
		
		/*
		JLabel spacer = new JLabel(" ");
		spacer.setPreferredSize(new Dimension(70,50));
		contentPanel.add(spacer, BorderLayout.LINE_START);
		contentPanel.add(spacer, BorderLayout.PAGE_START);
		contentPanel.add(spacer, BorderLayout.PAGE_END);
		*/
		
		
		terminal = new JTextArea("");
		//terminal.setPreferredSize(new Dimension(630,500));
		terminal.setBorder(new EmptyBorder(0,0,0,0));
		terminal.setBackground(Color.BLACK);
		terminal.setForeground(Color.GREEN);
		terminal.setCaretColor(Color.GREEN);
		terminal.setFont(new Font("Courier New", Font.BOLD, 14));
		terminal.setEditable(false);
		terminal.setLineWrap(true);
		
		scrollTerm = new JScrollPane(terminal);
		scrollTerm.setPreferredSize(new Dimension(630,450));
		scrollTerm.setBorder(new EmptyBorder(0,0,0,0));
		scrollTerm.setOpaque(false);
		
		screen = new JPanel();
		screen.setLayout(new BorderLayout());
		screen.setPreferredSize(new Dimension(630,500));
		screen.setBorder(new EmptyBorder(45,65,50,20));
		screen.setOpaque(false);
		screen.add(scrollTerm, BorderLayout.CENTER);
		
		commandLabel = new JLabel("command>");
		commandLabel.setPreferredSize(new Dimension(65,20));
		commandLabel.setFont(new Font("Courier New", Font.BOLD, 14));
		commandLabel.setForeground(Color.ORANGE);
		commandField = new JTextField();
		commandField.setBackground(Color.BLACK);
		commandField.setForeground(Color.YELLOW);
		commandField.setCaretColor(Color.RED);
		commandField.setFont(new Font("Courier New", Font.BOLD, 14));
		commandField.setPreferredSize(new Dimension(545, 20));
		commandField.setBorder(new EmptyBorder(0,0,0,0));
		commandField.addActionListener(this);
		
		commandZone = new JPanel(); commandZone.setOpaque(false);
		//commandZone.setPreferredSize(new Dimension(630,20));
		commandZone.add(commandLabel, BorderLayout.PAGE_END);
		commandZone.add(commandField, BorderLayout.PAGE_END);
		screen.add(commandZone, BorderLayout.PAGE_END);
		contentPanel.add(screen, BorderLayout.CENTER);
		
		
		buttons = new JPanel();
		buttons.setPreferredSize(new Dimension(330, 550));
		buttons.setBorder(new EmptyBorder(20,20,120,20));
		buttons.setLayout(new GridLayout(0,2));
		buttons.setOpaque(false);
			int buttonX = 150, buttonY = 90 ;
			
			nextDay = new JButton();
			try
			{ nextDay.setIcon(new ImageIcon(ImageIO.read(new File("../assets/button-next-day.png")).getScaledInstance(buttonX, buttonY, Image.SCALE_SMOOTH))); }
			catch (IOException e)
			{ System.err.println("Couldn't open button images"); }
			nextDay.addActionListener(this);
			buttons.add(nextDay);
			buttons.add(new JLabel(""));buttons.add(new JLabel(""));buttons.add(new JLabel(""));
			
			home = new JButton();
			try
			{ home.setIcon(new ImageIcon((ImageIO.read(new File("../assets/button-home.png"))).getScaledInstance(buttonX, buttonY, Image.SCALE_SMOOTH))); }
			catch (IOException e)
			{ System.err.println("Couldn't open button images"); }
			home.addActionListener(this);
			buttons.add(home);
			
			
			
			mail = new JButton();
			try
			{ mail.setIcon(new ImageIcon(ImageIO.read(new File("../assets/button-mail.png")).getScaledInstance(buttonX, buttonY, Image.SCALE_SMOOTH))); }
			catch (IOException e)
			{ System.err.println("Couldn't open button images"); }
			mail.addActionListener(this);
			buttons.add(mail);
			
			
			embassies = new JButton();
			try
			{ embassies.setIcon(new ImageIcon(ImageIO.read(new File("../assets/button-embassies.png")).getScaledInstance(buttonX, buttonY, Image.SCALE_SMOOTH))); }
			catch (IOException e)
			{ System.err.println("Couldn't open button images"); }
			embassies.addActionListener(this);
			buttons.add(embassies);
			
			worldInit = new JButton();
			try
			{ worldInit.setIcon(new ImageIcon(ImageIO.read(new File("../assets/button-world-init.png")).getScaledInstance(buttonX, buttonY, Image.SCALE_SMOOTH))); }
			catch (IOException e)
			{ System.err.println("Couldn't open button images"); }
			worldInit.addActionListener(this);
			buttons.add(worldInit);
			
			
			help = new JButton();
			try
			{ help.setIcon(new ImageIcon(ImageIO.read(new File("../assets/button-help.png")).getScaledInstance(buttonX, buttonY, Image.SCALE_SMOOTH))); }
			catch (IOException e)
			{ System.err.println("Couldn't open button images"); }
			help.addActionListener(this);
			buttons.add(help);
			
			coffee = new JButton();
			try
			{ coffee.setIcon(new ImageIcon(ImageIO.read(new File("../assets/button-coffee.png")).getScaledInstance(buttonX, buttonY, Image.SCALE_SMOOTH))); }
			catch (IOException e)
			{ System.err.println("Couldn't open button images"); }
			coffee.addActionListener(this);
			buttons.add(coffee);
			
			debug = new JButton();
			try
			{ debug.setIcon(new ImageIcon(ImageIO.read(new File("../assets/button-debug.png")).getScaledInstance(buttonX, buttonY, Image.SCALE_SMOOTH))); }
			catch (IOException e)
			{ System.err.println("Couldn't open button images"); }
			debug.addActionListener(this);
			buttons.add(debug);
			
		contentPanel.add(buttons, BorderLayout.LINE_END);
		
		
		this.pack();
		this.setVisible(true);
		
		
		//homeScreen();
		startUpInProgress=true;
		startUpScreen();
	}
	
	public void startUpScreen()
	{
		String term = "Welcome in a world where the Pentium II was released fifteen years earlier than in your timeline ; where people are nothing more than amoral numpty meatbags who'll start embezzling money as soon as it hits their pocket."+"\n\n"+
					  "Fortunately, in this world the world governements also have established a global project to end fraud, using the latest technologies. The project was called.. X-COM. Unfortunately, you're not too qualified, so they've put you into the crowdsourced sister project. So without further ado:"+"\n\n"+
					  "Welcome to the W.I.A.F.F. !"+"\n\n"+
					  "The World Initiative Against Financial Fraud is, well, that. Thanks to your provided terminal, you will be able to get mission orders and track down suspects. As a motivational tool, you will be given a hefty reward for each successful mission."+"\n\n"+
		              "          Signed : Col. John Matrix"+"\n\n\n\n"+
					  
					  "The W.I.A.F.F. Terminal uses an intuitive interface. In the future, please refer to the Help button for learning more."+"\n\n"+
					  "To begin, please enter your name in the command bar, at the bottom of the screen. Confirm by pressing enter.";
		
		terminal.setText(term);
	}
	
	public void CountrySelectScreen()
	{ 
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == commandField)
		{
			if (commandField.getText().equals(""))
				return;
			
			if (startUpInProgress == true)
			{
				main.setPlayerName(commandField.getText());
				startUpInProgress = false;
				homeScreen();
			}
			
			
			commandField.setText("");
		}
	}
	
	public void homeScreen()
	{
		String map ="       WELCOME ON YOUR WORLD INITIATIVE AGAINST FINANCIAL FRAUD TERMINAL\n"+"    "+
				"               ,_   .  ._. _.  ."+"\n"+
				"           , _-\\','|~\\~      ~/      ;-'_   _-'     ,;_;_,    ~~-"+"\n"+"    "+
				"  /~~-\\_/-'~'--' \\~~| ',    ,'      /  / ~|-_\\_/~/~      ~~--~~~~'--_"+"\n"+"    "+
				"  /              ,/'-/~ '\\ ,' _  , '|,'|~                   ._/-, /~"+"\n"+"    "+
				"  ~/-'~\\_,       '-,| '|. '   ~  ,\\ /'~                /    /_  /~"+"\n"+"    "+
				".-~      '|        '',\\~|\\       _\\~     ,_  ,               /|"+"\n"+"    "+
				"          '\\        /'~          |_/~\\\\,-,~  \\ \"         ,_,/ |"+"\n"+"    "+
				"           |       /            ._-~'\\_ _~|              \\ ) /"+"\n"+"    "+
				"            \\   __-\\           '/      ~ |\\  \\_          /  ~"+"\n"+"    "+
				"  .,         '\\ |,  ~-_      - |          \\\\_' ~|  /\\  \\~ ,"+"\n"+"    "+
				"               ~-_'  _;       '\\           '-,   \\,' /\\/  |"+"\n"+"    "+
				"                 '\\_,~'\\_       \\_ _,       /'    '  |, /|'"+"\n"+"    "+
				"                   /     \\_       ~ |      /         \\  ~'; -,_."+"\n"+"    "+
				"                   |       ~\\        |    |  ,        '-_, ,; ~ ~\\"+"\n"+"    "+
				"                    \\,      /        \\    / /|            ,-, ,   -,"+"\n"+"    "+
				"                     |    ,/          |  |' |/          ,-   ~ \\   '."+"\n"+"    "+
				"                    ,|   ,/           \\ ,/              \\       |"+"\n"+"    "+
				"                    /    |             ~                 -~~-, /   _"+"\n"+"    "+
				"                    |  ,-'                                    ~    /"+"\n"+"    "+
				"                    / ,'                                      ~"+"\n"+"    "+
				"                    ',|  ~";
		
		String info = "         Welcome, "+main.getPlayerName()+
		"\n         We are "+main.getTodaysDateLong()+
		"\n         It has been "+main.getDay()+" days since you've started)";
		terminal.setText(map+"\n\n"+info); //TODO mail ! yay !
	}
	
	public void debugScreen()
	{
		
	}
}