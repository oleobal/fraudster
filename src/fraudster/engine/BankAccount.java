package fraudster.engine;

import java.lang.IllegalStateException;

/**
 * links a Bank and a LegalEntity
 */
public class BankAccount
{
	private Bank host;
	private LegalEntity client;
	private Integer balance;

	/**
	 * Creates a new account hosted at the Bank b, owned by the LegalEntity
	 *
	 * If you try making a new BankAccount owned by a Taxpayer, it'll transmit its IllegalStateException
	 */
	public BankAccount(Bank b, LegalEntity l) throws IllegalStateException
	{
		
		this.client = l;
		try
		{
			this.client.addAccount(this);
		}
		catch (IllegalStateException e)
		{
			throw e;
		}
		
		this.host = b;
		this.host.addHostedAccount(this);
		this.balance = 0;
	}

	public Integer getBalance()
	{
		return balance;
	}

	/**
	 * Add (or substract, just use negative amounts) money from (to) the account
	 * Won't accept negative balances
	 */
	public void changeBalance(Integer amount) throws IllegalStateException
	{
		if (balance + amount < 0)
			throw new IllegalStateException("This operation would result in a negative balance");

		balance+=amount;

	}
	
	
	public LegalEntity getOwner()
	{
		return this.client;
	}
	
	public String toString()
	{
		return "Account details :\n"+
				"Owner: "+client+"\n"+
				"Host: "+host+"\n"+
				"Balance ($): "+balance;
	}
}
