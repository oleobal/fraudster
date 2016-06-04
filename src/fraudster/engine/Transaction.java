package fraudster.engine;


import java.lang.IllegalStateException;
import java.lang.IllegalArgumentException;

/**
 * Transfer of money between two accounts
 */
public class Transaction
{
	BankAccount sender, recipient;
	Integer amount, date;
	
	/**
	 * (important) Don't instanciate transactions directly ; do it from the Ledger's newTransaction method so it can be registered
	 *
	 * Will throw :
	 * IllegalArgumentException if you give it negative amounts to transfer
	 * IllegalStateException from one of the accounts if they'd object to it (eg it makes their balance negative, for instance)
	 *
	 * 'they'd object to it', get it ? haha
	 */
	public Transaction(BankAccount from, BankAccount to, Integer howMuch, Integer time) throws IllegalStateException, IllegalArgumentException
	{
		if (howMuch<0)
		{
			throw new IllegalArgumentException("No negative transfers");
		}
		
		try
		{
			from.changeBalance(-howMuch);
			this.sender = from;
		}
		catch (IllegalStateException e)
		{
			throw e;
		}
		try
		{
			to.changeBalance(howMuch);
			this.recipient = to;
		}
		catch (IllegalStateException e) //shouldn't exactly be a problem..
		{
			throw e;
		}
		
		this.amount = howMuch;
		this.date = time;
	}
	
	public int getDate()
	{
		return date;
	}
	
	public String toString()
	{
		return "Transaction:\n"+
			   "DATE: "+date+"\n\n"+
		       "SENDER "+sender+"\n\n"+
		       "RECIPIENT "+recipient+"\n\n"+
			   "AMOUNT ($): "+amount;
	}
	
	
}
