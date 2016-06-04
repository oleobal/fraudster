package fraudster.controller;

import fraudster.engine.*;
import fraudster.view.*;

import java.util.Calendar;
import java.util.Locale;
import java.text.SimpleDateFormat;

public class MainGraphical
{
	Ledger theLedger;
	MainFrame theFrame;
	Calendar startCal;
	
	String playerName;
	
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
	public String getPlayerName()
	{
		return playerName;
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
}