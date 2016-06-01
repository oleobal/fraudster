package fraudster.engine;

import java.lang.IllegalStateException;

// taxpayers can only have one bank account
public class Taxpayer extends LegalEntity
{

	public Taxpayer(String theirName, Country whereTheyLive)
	{
		super(theirName, whereTheyLive);
	}

	/**
	 * Unlike companies, Taxpayers can only have one account.
	 * If our taxpayer already has one, an exception is thrown
	 */
	public void addAccount(BankAccount newAccount) throws IllegalStateException
	{
		if (accounts.isEmpty())
			accounts.add(newAccount);
		else
			throw new IllegalStateException("This taxpayer already has an account");
		
	}
}
