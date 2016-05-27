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

	public BankAccount(Bank b, LegalEntity l)
	{
		this.host = b;
		this.client = l;
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
}
