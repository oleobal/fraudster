package fraudster.engine;

import java.util.ArrayList;
import java.lang.IllegalArgumentException;

public class Bank extends Company
{
	private ArrayList<BankAccount> hostedAccounts;

	public Bank(String banksName, Country whereTheyAre, Integer size)
	{
		super(banksName, whereTheyAre, size);
		//this.name = banksName; //field inherited from LegalEntity
		//this.residence = whereTheyAre;
		
		hostedAccounts = new ArrayList<BankAccount>();
	}
	
	/**
	 * yay, we got richer
	 * please give us your ID so we can link it to the account
	 */
	/*
	// removed because there were two ways to create a new BankAccount, here or directly, and I don't want to maintain both.
	public BankAccount newAccount(LegalEntity l)
	{
		BankAccount a = new BankAccount(this, l);
		hostedAccounts.add(a);
		return a;
	} */
	
	/**
	 * In case you instanciated an account yourself and wish to add it.
	 *
	 */
	public void addHostedAccount(BankAccount a) throws IllegalArgumentException
	{
		if (hostedAccounts.contains(a))
			throw new IllegalArgumentException("This account is already managed here");
		
		else
			hostedAccounts.add(a);
	}

	public ArrayList<BankAccount> getAccounts()
	{
		ArrayList<BankAccount> buffer = new ArrayList<BankAccount>();
		for (BankAccount i : hostedAccounts)
		{
			buffer.add(i);
		}
		return buffer;
	}
	
	public String toString()
	{
		return "Bank -- "+name+" ("+residence+")";
	}
}
