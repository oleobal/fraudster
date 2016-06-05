package fraudster.engine;


import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

import java.util.NoSuchElementException;

/**
 * holds list of investigators, suspect transactions and denounciations
 *
 * also holds a list of countries it uses to get the system moving
 *
 * also holds the date, in days since the start of the game
 */
public class Ledger
{
	/**
	 * the date, as a number of days since the start of the game
	 */
	private Integer date;
	/**
	 * every country
	 */
	private ArrayList<Country> countries;
	/**
	 * every investigator
	 */
	private HashMap<Country, Investigator> investigators;
	/**
	 * every transaction
	 */
	private ArrayList<Transaction> transactions;
	private ArrayList<Transaction> suspiciousTransactions;

	/**
	 * logging system for debugging
	 */
	private HashMap<Integer, ArrayList<String>> log;
	
	//TODO: List of denounciations
	
	public Ledger()
	{
		date = 0;
		countries = new ArrayList<Country>();
		investigators = new HashMap<Country, Investigator>();
		transactions = new ArrayList<Transaction>();
		suspiciousTransactions = new ArrayList<Transaction>();
		log = new HashMap<Integer, ArrayList<String>>();
	}

	/**
	 * add a country to the ledger's list
	 */
	public void addCountry(Country c)
	{
		countries.add(c);
	}
	
	/**
	 * Just a list of all countries. Use with moderation.
	 */
	public ArrayList<Country> getCountries() throws NoSuchElementException
	{
		if (countries.isEmpty())
			throw new NoSuchElementException("No countries");
		// I'm going to go ahead and say there's little chance this warning ever will be relevant
		@SuppressWarnings("unchecked")
		ArrayList<Country> lol = (ArrayList<Country>)countries.clone();
		return lol;
	}

	//TODO way to flag transactions as suspicious that's not too dumb
	/**
	 * create a new Transaction dated from today and store it in the ledger
	 *
	 * sent back to you just in case you need it
	 */
	public Transaction newTransaction(BankAccount from, BankAccount to, Integer howMuch) throws IllegalStateException, IllegalArgumentException
	{
		try
		{
			Transaction lol = new Transaction(from, to, howMuch, date);
			transactions.add(lol);
			return lol;
		}
		catch (RuntimeException e)
		{
			throw e;
		}

	}

	/**
	 * get all transactions for a given day
	 */
	public ArrayList<Transaction> getTransactions(Integer date)
	{
		ArrayList<Transaction> returnedList = new ArrayList<Transaction>();
		for (Transaction i : transactions)
		{
			if (i.getDate() == date)
				returnedList.add(i);
		}
		
		return returnedList;
	}
	
	//TODO time (days)
	/*
	 * maybe hold a list of all LegalEntities here
	 * every day we select a few at random and make them deal with each other
	 *
	 * alternatively we just keep a list of countries and ask them for a bank, a company or a taxpayer
	 */
	/**
	 * This asks the countries to tell their nationals to activate their doBusiness() method.
	 * and adds 1 day to the days counter, obviously
	 */
	public void  nextDay()
	{
		date++;
		for (Country i : countries)
		{
			i.doBusiness();
		}

	}

	/**
	 * fills the world with random values
	 * 10 countries, with each 100 taxpayers and 50 compagnies and 1 bank
	 */
	public void populate()
	{
		String[] countryNames = {"France", "Monaco", "Luxembourg", "Germany", "the United Kingdom", "Lichtenstein", "Panama", "Turkey", "the Virgin Islands", "the United States", "the Soviet Union", "the People's China", "Italy", "Singapour", "Brazil", "Spain", "Mexico", "Australia", "Canada", "Egypt", "South Africa", "Saudi Arabia", "Switzerland", "Yugoslavia"};
		
		String buffer; int rInt;
		Random rand = new Random();
		for (int k=0;k<countryNames.length;k++) //Fisher-Yates
		{
			rInt = rand.nextInt(countryNames.length);
			buffer = countryNames[k];
			countryNames[k] = countryNames[rInt];
			countryNames[rInt] = buffer;
			
		}
		
		String[] bankAdj = {"New", "Great", "Development", "Social", "Investment", "General"};
		
		Country c ; //for each country, 100 taxpayers and 50 compagnies
		for (int k=0;k<10;k++)
		{
			//TODO tweak numbers ?
			//TODO set up branches, owners and all
			c = new Country(countryNames[k], this);
			for (int i=0; i<100;i++)
			{
				new Taxpayer(c);
			}
			
			for (int i=0;i<50;i++)
			{
				new Company(c);
			}
			
			//TODO more banks
			new Bank(bankAdj[rand.nextInt(bankAdj.length)]+" Bank of "+c, c, 3);
		}
		
	}
	
	/**
	 * records in the log
	 */
	public void log(String message)
	{
		if (log.containsKey(this.date))
			(log.get(this.date)).add(message);
		else
		{
			log.put(this.date, new ArrayList<String>());
			(log.get(this.date)).add(message);
		}
	}
	
	public ArrayList<String> getLog(Integer day) throws NoSuchElementException
	{
		if (log.containsKey(day))
			return log.get(day);
		else
			throw new NoSuchElementException("No entry in the ledger for day "+day);
	}
	
	public int getDay()
	{
		return this.date;
	}
}
