package fraudster.engine;

import java.util.ArrayList;

public class Bank extends Company
{
	private ArrayList<BankAccount> hostedAccounts;

	public Bank(String banksName, Country whereTheyAre)
	{
		super(banksName, whereTheyAre);
		//this.name = banksName; //field inherited from LegalEntity
		//this.residence = whereTheyAre;
		
		hostedAccounts = new ArrayList<BankAccount>();
	}
	
	/**
	 * yay, we got richer
	 * please give us your ID so we can link it to the account
	 */
	public BankAccount newAccount(LegalEntity l)
	{
		BankAccount a = new BankAccount(this, l);
		hostedAccounts.add(a);
		return a;
	}

}
