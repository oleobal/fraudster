package fraudster.engine;


import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import java.io.Serializable;
import java.util.NoSuchElementException;

/**
 * holds list of investigators, suspect transactions and denounciations
 *
 * also holds a list of countries it uses to get the system moving
 *
 * also holds the date, in days since the start of the game
 */
public class Ledger implements Serializable
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
	private CopyOnWriteArrayList<Transaction> transactions;
	private CopyOnWriteArrayList<Transaction> suspiciousTransactions;
	
	/**
	 * holds accounts that will recieve suspicious transactions for the day
	 * cleaned every day
	 * 
	 * originally I put that on the country level but that was very dumb
	 */
	private CopyOnWriteArrayList<BankAccount> nextTransactionSuspectAccounts ;

	/**
	 * logging system for debugging
	 */
	private HashMap<Integer, ArrayList<String>> log;
	// Yes, java.util.logging.Logger, I know..
	
	//TODO: List of denounciations
	
	public Ledger()
	{
		date = 0;
		countries = new ArrayList<Country>();
		investigators = new HashMap<Country, Investigator>();
		transactions = new CopyOnWriteArrayList<Transaction>();
		suspiciousTransactions = new CopyOnWriteArrayList<Transaction>();
		nextTransactionSuspectAccounts=new CopyOnWriteArrayList<BankAccount>();
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
	
	/**
	 * called by Country.signalFraud();
	 * because originally it was at country level
	 * but that was stupid
	 * @param suspectAccount
	 */
	public void signalFraud(BankAccount suspectAccount)
	{
		nextTransactionSuspectAccounts.add(suspectAccount);
		log("(ledger) fraud registered ("+nextTransactionSuspectAccounts.size()+" elements total for today)");
	}

	//TODO way to flag transactions as suspicious that's not too dumb
	/**
	 * create a new Transaction dated from today and store it in the ledger
	 *
	 * sent back to you just in case you need it
	 * 
	 * also adds the transaction to the list of suspicious transactions if the recipient account has been signaled
	 */
	public Transaction newTransaction(BankAccount from, BankAccount to, Integer howMuch) throws IllegalStateException, IllegalArgumentException
	{
		try
		{
			Transaction lol = new Transaction(from, to, howMuch, date);
			transactions.add(lol);
			if (nextTransactionSuspectAccounts.contains(to))
			{
				suspiciousTransactions.add(lol); //we got you !
			}
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
	
	/**
	 * @return a copy of the suspicious transactions list
	 */
	public ArrayList<Transaction> getSuspiciousTransactions()
	{
		return new ArrayList<Transaction>(suspiciousTransactions);
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
		log("(ledger) Clearing daily suspect accounts list of "+nextTransactionSuspectAccounts.size()+ " elements.");
		this.nextTransactionSuspectAccounts.clear();
		
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
			c = new Country(countryNames[k], this);
			for (int i=0; i<80+rand.nextInt(60);i++)
			{
				new Taxpayer(c);
			}
			
			for (int i=0;i<20+rand.nextInt(60);i++)
			{
				new Company(c);
			}
			
			//TODO more banks
			new Bank(bankAdj[rand.nextInt(bankAdj.length)]+" Bank of "+c, c, 3);
		}
		
		// making some rich people own companies
		LegalEntity t, co1;
		for (Country ct : countries)
		{
			for (int i=0; i<50;i++)
			{
				t = ct.getRandomNational("rich-taxpayer");
				co1 = ct.getRandomNational("company-not-bank");
				t.addPossession(co1);
			//FIXME: si on ajoute un champ possedePar, faudra taper ici
			}
		}

		// making some rich compagnies own compagnies
		LegalEntity co2;
		for (Country ct : countries)
		{
			for (int i=0; i<50;i++)
			{
				co1 = ct.getRandomNational("rich-company");
				co2 = ct.getRandomNational("company-not-bank");
				co1.addPossession(co2);
			}
		}
		

		//relations between countries
		// TODO: make this more reasonable (one day)
		int c0,c1;
		for (int i=0;i<100;i++)
		{
			c0 = rand.nextInt(countries.size());c1 = rand.nextInt(countries.size());
			if (c0==c1)
				continue;
			countries.get(c0).changeRelations(countries.get(c1), 1+rand.nextInt(7));
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
