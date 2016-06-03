package fraudster.engine;


import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

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
	Integer date;
	/**
	 * every country
	 */
	ArrayList<Country> countries;
	/**
	 * every investigator
	 */
	HashMap<Country, Investigator> investigators;
	/**
	 * every transaction
	 */
	ArrayList<Transaction> transactions;
	ArrayList<Transaction> suspiciousTransactions;

	//TODO: List of denounciations
	
	public Ledger()
	{
		date = 0;
		countries = new ArrayList<Country>();
		investigators = new HashMap<Country, Investigator>();
		transactions = new ArrayList<Transaction>();
		suspiciousTransactions = new ArrayList<Transaction>();

	}

	/**
	 * add a country to the ledger's list
	 */
	public void addCountry(Country c)
	{
		countries.add(c);
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

	//TODO time (days)
	/*
	 * maybe hold a list of all LegalEntities here
	 * every day we select a few at random and make them deal with each other
	 *
	 * alternatively we just keep a list of countries and ask them for a bank, a company or a taxpayer
	 */
	public void  nextDay()
	{
		Random rand = new Random();

	}
}
