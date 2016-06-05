package fraudster.controller;

import fraudster.engine.*;
import fraudster.view.*;

import java.util.Calendar;
import java.util.Locale;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MainGraphical
{
	private Ledger theLedger;
	private MainFrame theFrame;
	private Calendar startCal;
	
	private String playerName; //I have to keep it somewhere while waiting for the country
	private Player thePlayer;
	
	public static void main(String[] args)
	{
		MainGraphical theMain = new MainGraphical();
		// this does seem a bit silly but it's that or passing the frame the ledger
		// here this object will serve as intermediary between the engine and the view. A controller, if you will.
		

	}
	
	public MainGraphical()
	{
		startCal = Calendar.getInstance();
		startCal.set(1984, 10, 4); // "I lied."
		
		theLedger = new Ledger();
		theLedger.populate();
		
		theFrame = new MainFrame("Fraudster", this);
		
		
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
					result+=i+"\n";
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
}