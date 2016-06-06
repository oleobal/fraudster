package fraudster.view;

import fraudster.controller.*;
import fraudster.engine.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.util.Random;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.lang.NumberFormatException;


public class MainFrame extends JFrame implements ActionListener
{
	private MainGraphical main;
	private Boolean gameLocked; //whether side buttons should be locked
	private Player thePlayer;
	
	private MainPanel contentPanel;
	private JPanel buttons, commandZone;
	
	private JTextArea terminal;
	private JScrollPane scrollTerm;
	private CommandField commandField; JLabel commandLabel;
	private JPanel screen;
	private JButton nextDay, home, mail, embassies, worldInit, help, coffee, debug;
	
	/**
	 * Not ready for gaming until it has recieved a reference to its MainGraphical !
	 * Until then it's just for loading savegames
	 */
	public MainFrame(String s)
	{
		super(s);
		//main = m;
		// now done in a particular function
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
		commandField = new CommandField();
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
		gameLocked=true;
		//startUpScreen();
		logOnScreen();
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == commandField)
		{
			if (commandField.getText().equals(""))
				return;
			
			if (commandField.source=="startUpScreen") // name selction
			{
				main.setPlayerName(commandField.getText());
				commandField.reset();
				countrySelectScreen();
				
			}
			else if (commandField.source=="countrySelectScreen") // country select
			{
				
				try
				{
					Integer lol =Integer.parseInt(commandField.getText());
					if ( lol < commandField.getMin()  || lol > commandField.getMax())
					{
						terminal.setText(terminal.getText()+"\n\nThere was an error in your last command. Only a number from "+commandField.getMin()+" to "+commandField.getMax()+", please.");
						commandField.setText("");
					}
					
					else
					{
						thePlayer = main.setPlayerCountry(lol);
						commandField.reset();
						gameLocked=false;
						homeScreen();
					}
					
				}
				catch (NumberFormatException exc)
				{ 
					terminal.setText(terminal.getText()+"\n\nThere was an error in your last command. Only a number, please.");
					commandField.setText("");
				}
				
				
				
			}
			
			else if (commandField.source.equals("countriesScreen")) //I guess that's a bitbetter than random global booleans
			{
				try
				{
					Integer lol =Integer.parseInt(commandField.getText());
					if ( lol < commandField.getMin()  || lol > commandField.getMax())
					{
						terminal.setText(terminal.getText()+"\n\nThere was an error in your last command. Only a number from "+commandField.getMin()+" to "+commandField.getMax()+", please.");
						commandField.setText("");
					}
					
					else
					{
						singleCountryScreen(lol);
						commandField.reset();
					}
					
				}
				catch (NumberFormatException exc)
				{ 
					terminal.setText(terminal.getText()+"\n\nThere was an error in your last command. Only a number, please.");
					commandField.setText("");
				}
			}
			
			else if (commandField.source.equals("logOnScreen")) //new game / load 
			{
				if (commandField.getText().equals("new")) //new game
					{
						main = new MainGraphical(this);
						commandField.reset();
						startUpScreen();
					}
				else  //load a save file..
				{
					try
					{
						Integer lol =Integer.parseInt(commandField.getText());
						if ( lol < commandField.getMin()  || lol > commandField.getMax())
						{
							terminal.setText(terminal.getText()+"\n\nThere was an error in your last command. Only a number from "+commandField.getMin()+" to "+commandField.getMax()+", please.");
							commandField.setText("");
						}
						
						else // ..successfully
						{
							//let's just hope this won't end terribly
							//this whole "let's store public things onto commandField" thing isn't completely set up for success
							@SuppressWarnings("unchecked")
							String file = ((ArrayList<String>)(commandField.storage)).get(lol);
							main = MainGraphical.loadGame(file);
							if (main == null)
							{
								JOptionPane.showMessageDialog(this,
								"Could not read save file !",
								"No save file",
								JOptionPane.ERROR_MESSAGE);
								
								System.exit(-1); //gawd
							}
							thePlayer = main.getPlayer();;//FIXME : nullpointerexception here for some reason
							
							commandField.reset();
							gameLocked=false;
							homeScreen();
						}
						
					}
					catch (NumberFormatException exc)
					{ 
						terminal.setText(terminal.getText()+"\n\nThere was an error in your last command. Only a number or \"new\", please.");
						commandField.setText("");
					}
				}
				
			}
			
			
			commandField.setText("");
		}
		
		if (!gameLocked)
		{
			if (e.getSource() == nextDay)
			{
				main.nextDay();
				logOffScreen();
			}
		
			if (e.getSource() == home)
			{
				homeScreen();
			}
			
			if (e.getSource() == mail)
			{
			}
			
			if (e.getSource() == embassies)
			{
				countriesScreen();
			}
			
			if (e.getSource() == help)
			{
				helpScreen();
			}
			
			if (e.getSource() == coffee)
			{
				coffeeScreen();
			}
			
			if (e.getSource () == debug)
			{
				debugScreen();
			}
			
		}
	}
	
	/**
	 * Whether you want to load a saved game or start a new game
	 */
	public void logOnScreen()
	{
		String term = "Hello ! Do you wish to start a new quest for self-fulfillment or just to load an existing profile ?\n\n\n\n"+
					"There is a command bar at the bottom of the screen :\n"+
		            "input \"new\" for a new game, or the number of a saved game file to load it ;\n"+
					"then, confirm by pressing the Enter key.\n\n"+
					"Here is the list of save files I've found:\n\n";
					
		ArrayList<String> fileNames = MainGraphical.getSaveNames();
		int k=0;
		if (fileNames.isEmpty())
				term+="[no savefiles found]\n";
		else
		{
			for (String i : fileNames)
			{
				term+="["+k+"] "+i+"\n";
				k++;
			}
		}
		terminal.setText(term);
		commandField.requestFocus();
		commandField.minVal = 0;
		commandField.maxVal = k-1;
		commandField.source="logOnScreen";
		commandField.storage=fileNames;
	}

	/**
	 * transition between days
	 */
	public void logOffScreen() //I guess a dimming effect would be ideal, but..
	{
		String term = "This computer has been shut down."+"\n"+
		              "See you tomorrow ! It'll be day "+main.getDay()+".\n\n\n\n\n\n\n\n\n\n"+

					  "(your game has been saved as autosave)\n\n"+
					  "(Press any button to turn your terminal back on.)";
		
		main.saveGame();
		terminal.setText(term);
		
	}
		
	/**
	 * (new game) presentation, input your name
	 */
	public void startUpScreen()
	{
		String term = "Welcome in a world where the Pentium II was released fifteen years earlier than in your timeline ; where people are nothing more than amoral numpty meatbags who'll start embezzling money as soon as it hits their pocket."+"\n\n"+
					  "Fortunately, in this world the world governements also have established a global project to end fraud, using the latest technologies. The project was called.. X-COM. Unfortunately, you're not too qualified, so they've put you into the crowdsourced sister project. So without further ado:"+"\n\n"+
					  "Welcome to the W.I.A.F.F. !"+"\n\n"+
					  "The World Initiative Against Financial Fraud is, well, that. It's a alliance of states, with each providing a nigh-specialist to operate in their name. Thanks to your provided workstation, you will be able to get mission orders and track down suspects. As a motivational tool, you will be given a hefty reward for each successful mission."+"\n\n"+
		              "          Signed : Col. John Matrix"+"\n\n\n\n"+
					  
					  "The W.I.A.F.F. Terminal uses an intuitive interface. In the future, please refer to the Help button for learning more."+"\n\n"+
					  "To begin, please enter your name in the command bar, at the bottom of the screen. Confirm by pressing enter.";
		
		terminal.setText(term);
		commandField.requestFocus();
		commandField.source="startUpScreen";
	}
	
	/**
	 * (new game) select your country
	 */
	public void countrySelectScreen()
	{ 
		String result = "";
		try
		{
			ArrayList<Country> countries = main.getCountries();
			int k=0; result="Please select the country you're working for:\n\n";
			
			for (Country i:countries)
			{
				result+="["+k+"] "+i+"\n";
				k++;
			}
			result+="\nType the number of your country in the command bar.";
			commandField.requestFocus();
			commandField.setMax(k-1);
			commandField.setMin(0);
			commandField.setNumOnly(true);
			commandField.source="countrySelectScreen";
			terminal.setText(result);
		}
		catch (NoSuchElementException e)
		{ result = "No countries"; }
		
		
	}
	public void countriesScreen()
	{
		String result = "";
		try
		{
			ArrayList<Country> countries = main.getCountries();
			int k=0; result="Please select the country you wish to learn more about:\n\n";
			for (Country i:countries)
			{
				result+="["+k+"] "+i+"\n";
				k++;
			}
			result+="\nType the number of the country in the command bar.";
			commandField.source = "countriesScreen";
			commandField.setMax(k-1);
			commandField.setMin(0);
			commandField.setNumOnly(true);
			terminal.setText(result);
		}
		catch (NoSuchElementException e)
		{ result = "No countries"; }
	}
	
	
	
	
	/**
	 * info about a particular country
	 */
	public void singleCountryScreen(Integer c)
	{
		String result = "";
		try
		{
			ArrayList<Country> countries = main.getCountries(); Country target = countries.get(c);
			result += "Country info: "+target+"\n========================================\n\n";
			result +="Relations:\n---------\n";
			result +="(In number of additional days to each request from that country. Absent = zero.)\n\n";
			for (Map.Entry<Country, Integer> entry : target.getRelations().entrySet())
			{
				result+=entry.getKey()+" -- "+entry.getValue();
			}
			
			commandField.source = "singleCountryScreen";
			terminal.setText(result);
		}
		catch (NoSuchElementException e)
		{ result = "No countries"; }
	}
	
	
	/**
	 * just some static help
	 */
	public void helpScreen()
	{
		String term = "List of buttons :"+"\n"+
		              "-----------------"+"\n\n"+
		              "Next day: you log off and go to sleep at home while the game advances by one day."+"\n"+
					  "Home: nice world map"+"\n"+
					  "Mail: where you get to consult your e-mail. Answers from queries will arrive here."+"\n"+
					  "Embassies: allows you to see the list of countries, see statistics and request things from them."+"\n"+
					  "World Initiative: the WAIFF communicates through this. This is where you can see suspicious transactions and report your findings."+"\n"+
					  "Help: this."+"\n"+
					  "Coffee: get a cup of coffee."+"\n"+
					  "Debug: prints the game log of the day."+"\n\n"+
					  
					  "On each screen you can interact through the command bar at the bottom."+"\n\n\n"+
					  
					  
					  "Game mechanics:"+"\n"+
					  "---------------"+"\n\n"+
					  "Each Country has nationals that answer to them : compagnies, banks (which are compagnies) and taxpayers."+"\n"+
					  "Taxpayers have only one account in their name, but when it gets too full they get the urge to fraud. They'll then start asking compagnies they own to embezzle money for them, making them frauding too. They might also create compagnies, if needs be."+"\n"+
					  "Some might be taking advantage of lower taxes on entreprises, but most of the time the aim is to get their money out of their country."+"\n\n"+
		              "Fortunately, the WAIFF keeps a list of transactions signaled as suspicious. Using them as a starting point, you'll have to ask around for more information until you can trace money from a (non-)taxpayer's pocket to a foreign compagny.";
					  
		terminal.setText(term);terminal.setCaretPosition(0);
	}
	
	/**
	 * some status info
	 */
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
		
		String info = "         Welcome, "+thePlayer.getName()+" from "+thePlayer.getCountry()+
		"\n         We are "+main.getTodaysDateLong()+
		"\n         It has been "+main.getDay()+" days since you've started.";
		terminal.setText(map+"\n\n"+info); //TODO mail ! yay !
	}
	
	/**
	 * credits. Maybe put in the save function here too
	 */
	public void coffeeScreen()
	{
		String term = ""+
		"                                  ("+"\n"+
		"                                    )     ("+"\n"+
		"                             ___...(-------)-....___"+"\n"+
		"                         .-\"\"       )    (          \"\"-."+"\n"+
		"                   .-'``'|-._             )         _.-|"+"\n"+
		"                  /  .--.|   `\"\"---...........---\"\"`   |"+"\n"+
		"                 /  /    |                             |"+"\n"+
		"                 |  |    |                             |"+"\n"+
		"                  \\  \\   |                             |"+"\n"+
		"                   `\\ `\\ |                             |"+"\n"+
		"                     `\\ `|                             |"+"\n"+
		"                     _/ /\\                             /"+"\n"+
		"                    (__/  \\                           /"+"\n"+
		"                 _..---\"\"` \\                         /`\"\"---.._"+"\n"+
		"              .-'           \\                       /          '-."+"\n"+
		"             :               `-.__             __.-'              :"+"\n"+
		"             :                  ) \"\"---...---\"\" (                 :"+"\n"+
		"              '._               `\"--...___...--\"`              _.'"+"\n"+
		"                \\\"\"--..__                              __..--\"\"/"+"\n"+
		"                 '._     \"\"\"----.....______.....----\"\"\"     _.'"+"\n"+
		"                    `\"\"--..,,_____            _____,,..--\"\"`"+"\n"+
		"                                  `\"\"\"----\"\"\"`"+"\n\n\n"+
		"Your coffee is being delivered. Meanwhile, thank our intern developer:\nOlivier L\u00E9obal";
		
		terminal.setText(term);
	}
	
	/**
	 * prints out the day's log
	 */
	public void debugScreen()
	{
		String term = "Log for day "+main.getDay()+"\n---------------\n";
		term+=main.getLog(main.getDay());
		
		terminal.setText(term);terminal.setCaretPosition(0);
	}
}