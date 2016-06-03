package fraudster.engine;

import java.lang.IllegalStateException;
import java.util.Random;
// taxpayers can only have one bank account
public class Taxpayer extends LegalEntity
{
	
	
	/**
	 * random name
	 */
	public Taxpayer(Country whereTheyLive)
	{
		super("N/A", whereTheyLive);
		String[] firstNames = {"John", "Felicia", "Cecil", "Jesus", "Max", "Vladimir", "Tien", "Harold", "Charles", "Anna"};
		String[] lastNames = {"Doe","Hardy", "Khonnagh", "Thompson", "Zola", "Thomson", "ter Meer", "Schmidt", "Anderson", "Zhen", "Meier", "Rodriguez", "Nutarelli", "Pavlov", "Domsky", "McConnaugh"};
		Random rand=new Random();
		name=firstNames[rand.nextInt(firstNames.length)]+" "+lastNames[rand.nextInt(lastNames.length)];
	}

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


	public String toString()
	{
		return "Taxpayer -- "+name+" ("+residence+")";
	}
}
