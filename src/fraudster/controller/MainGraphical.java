package fraudster.controller;

import fraudster.engine.*;
import fraudster.view.*;

import java.util.Calendar;
import java.util.Locale;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class MainGraphical implements Serializable
{
	private Ledger theLedger;
	private transient MainFrame theFrame; //transient : won't get serialized
	private Calendar startCal;
	
	private String playerName; //I have to keep it somewhere while waiting for the country
	private Player thePlayer;
	
	public static void main(String[] args)
	{
		// this does seem a bit silly but it's that or passing the frame the ledger
		// here this object will serve as intermediary between the engine and the view. A controller, if you will.
		
		new MainFrame("Fraudster");
	}
	
	/**
	 * If you're using this, it's a new game
	 * else use the static loadGame that returns a MainGraphical object
	 */
	public MainGraphical(MainFrame frame)
	{
		startCal = Calendar.getInstance();
		startCal.set(1984, 10, 4); // "I lied."
		
		theFrame = frame;
		// this should only be done when not loading a savegame
		theLedger = new Ledger();
		theLedger.populate();
		
	}
	
	public void setPlayerName(String p)
	{
		playerName=p;
	}
	/**
	 * ArrayLists always have the same order, right ? Right ?
	 * Anyway yes, this is important, because it instanciates our player only then. Use setPlayerName first.
	 * to be used as the final step in creating the player
	 * to be completely honest I'm not sure all this was a great idea
	 */
	public Player setPlayerCountry(Integer i)
	{
		thePlayer = new Player(playerName, getCountries().get(i));
		return thePlayer;
	}
	
	public void nextDay()
	{
		theLedger.nextDay();
	}
	
	public Integer getDay()
	{
		return theLedger.getDay();
	}
	
	/**
	 * Gives back a Calendar object which is the starting date of the game + the number of days given
	 */
	public Calendar getDate(Integer days)
	{
		Calendar newCal = (Calendar) startCal.clone();
		newCal.add(Calendar.DAY_OF_MONTH, days); //now, if I read the doc right, it should work
		return newCal;
	}
	
	/**
	 * adds the number of days since the start of the game to a predefined start date
	 */
	public Calendar getTodaysDate()
	{
		Calendar newCal = (Calendar) startCal.clone();
		newCal.add(Calendar.DAY_OF_MONTH, theLedger.getDay());
		return newCal;
	}
	
	public String getTodaysDateShort()
	{
		Calendar newCal = (Calendar) startCal.clone();
		newCal.add(Calendar.DAY_OF_MONTH, theLedger.getDay());
		
		
		return (new SimpleDateFormat("yy-MM-dd")).format(newCal.getTime());
	}
	
	/**
	 * "Sunday, 4th of November, 1985"
	 */ 
	public String getTodaysDateLong()
	{
		Calendar newCal = (Calendar) startCal.clone();
		newCal.add(Calendar.DAY_OF_MONTH, theLedger.getDay());
		return (new SimpleDateFormat("EEEE, 'the' d 'of' MMMM, yyyy", Locale.US)).format(newCal.getTime());
	}
	
	/**
	 * Stringified game log for an entire day
	 */
	public String getLog(Integer day)
	{
		String result = "";
		try
			{
				for (String i : theLedger.getLog(theLedger.getDay()))
				{
					result+=i+"\n\n====================\n\n";
				}
					
			}
		catch (NoSuchElementException e)
		{
			result = "No entries";
		}
		
		return result;
	}
	
	public ArrayList<Country> getCountries()
	{
		try
		{
			return theLedger.getCountries();
		}
		catch (NoSuchElementException e)
		{
			throw e;
		}
	}
	
	public ArrayList<Transaction> getSuspiciousTransactions()
	{
		try
		{
			return theLedger.getSuspiciousTransactions();
		}
		catch (NoSuchElementException e)
		{
			throw e;
		}
	}
	
	/**
	 * returns a reference to the (hopefully sole) Player object, in case
	 * right now I don't want to think about it too much
	 */
	public Player getPlayer()
	{
		return thePlayer;
		
	}
	
	/**
	 * returns a list of all savefiles found
	 */
	public static ArrayList<String> getSaveNames()
	{
		ArrayList<String> result = new ArrayList<String>();
		
		for (File i : (new File("../savegames")).listFiles())
		{
			result.add(i.toString());
		}
		
		return result;
	}
	
	/**
	 * save to file "autosave"
	 */
	public void saveGame()
	{
		saveGame("autosave");
	}
	
	/**
	 * holy hell, this Serializable thing is like a gift from God
	 *
	 * So yes, we'll just be serializing this object (MainGraphical) which happens to contain a Ledger that holds all the game engine and a Player that reminds us of who the player is. The GUI is obviously not written to file.
	 */
	public void saveGame(String filename)
	{
		try
		{
			/*
			FileOutputStream fout = new FileOutputStream("../savegames/"+filename); //irrelevant exceptions
			ObjectOutputStream oos = new ObjectOutputStream(fout); //throws IOException, SecurityException, NullPointerException  
			oos.writeObject(theLedger); // IOExceptions
			oos.close();
			
			fout = new FileOutputStream("../savegames/"+filename+"_player"); //irrelevant exceptions
			oos = new ObjectOutputStream(fout); //throws IOException, SecurityException, NullPointerException  
			oos.writeObject(thePlayer); // IOExceptions
			oos.close();
			*/
			// might as well write this actually. As in this, the object.
			FileOutputStream fout = new FileOutputStream("../savegames/"+filename); //irrelevant exceptions
			ObjectOutputStream oos = new ObjectOutputStream(fout); //throws IOException, SecurityException, NullPointerException  
			oos.writeObject(this); // IOExceptions
			oos.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * this is static because it is called from the main
	 * an object obviously can't rewrite itself
	 *
	 * returns null if a problem occurred
	 */
	public static MainGraphical loadGame(String filename)
	{
		try
		{
			FileInputStream fin = new FileInputStream("../savegames/"+filename);
			ObjectInputStream ois = new ObjectInputStream(fin);
			MainGraphical main = (MainGraphical) ois.readObject();
			ois.close();
			return main;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//TODO uh, things ? idk, it failed. dammit.
		}
		return null;
	}
	
	public void newGame()
	{
		theLedger = new Ledger();
		theLedger.populate();
		
	}
	
}