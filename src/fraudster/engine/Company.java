package fraudster.engine;

import java.util.Random;
import java.lang.IllegalArgumentException;

/**
 * Empty shell companies require only 5 lines of code
 */
public class Company extends LegalEntity
{
	public Company(String n, Country c, Integer w)
	{
	 super(n, c, w);
	}

	/**
	 * random name
	 */
	public Company(Country c, Integer w)
	{
		super("N/A", c, w);
		
		// TODO expand to other languages
		// apparently people use chinese names for embezzlements to complicate things
		String[] firstArg = {"Pecha", "Little", "Development", "Air", "Victory", "Standard", "Ware", "Software", "Agile", "Poly", "Work", "Hardware", "Culinary", "Delicious", "Bio", "Organic", "Colour", "Red", "Swift"};
		String[] secondArg = {"Banana", "Life", "Fishes", "Coop", "Oil", "Mix", "House", "Fun", "Love", "Home"};
		String[] thirdArg = {"Incorporated", "Inc.", "Cooperative", "Coop.", "PLC", "Limited", "Ltd.", "Corporation", "Corp.", "Company", "LLP", "LLC"};
		
		Random rand = new Random();
		String n=firstArg[rand.nextInt(firstArg.length)]+" "+secondArg[rand.nextInt(secondArg.length)]+" "+thirdArg[rand.nextInt(thirdArg.length)];
		this.name=n;
		
		
	}
	
	/**
	 * random name and wealth
	 */
	public Company(Country c)
	{
		super("N/A", c, 0);
		
		// TODO expand to other languages
		// apparently people use chinese names for embezzlements to complicate things
		String[] firstArg = {"Pecha", "Little", "Development", "Air", "Victory", "Standard", "Ware", "Software", "Agile", "Poly", "Work", "Hardware", "Culinary", "Delicious", "Bio", "Organic", "Colour", "Red", "Swift"};
		String[] secondArg = {"Banana", "Life", "Fishes", "Coop", "Oil", "Mix", "House", "Fun", "Love", "Home"};
		String[] thirdArg = {"Incorporated", "Inc.", "Cooperative", "Coop.", "PLC", "Limited", "Ltd.", "Corporation", "Corp.", "Company", "LLP", "LLC"};
		
		Random rand = new Random();
		String n=firstArg[rand.nextInt(firstArg.length)]+" "+secondArg[rand.nextInt(secondArg.length)]+" "+thirdArg[rand.nextInt(thirdArg.length)];
		this.name=n;
		
		int lol = rand.nextInt(10); //proportions are obviously not actual ones..
		if (lol==0)
			scale=3; // 1/10
		else if (lol<3)
			scale=2; // 2/10
		else if (lol<7)
			scale=1; // 4/10
		else if (lol<10)
			scale=0; // 3/10
	}
	
	
	
	
	/**
	 * An owner can ask a company he/she owns to fraud so his money can get out the country.
	 * 
	 * @param owner The owner should pass himself so not anyone can ask a company to fraud
	 */
	public BankAccount pleaseFraud(LegalEntity owner) throws IllegalArgumentException
	{
		if (owner.checkPossessed(this) == false)
			throw new IllegalArgumentException("You can't ask a company you don't own to fraud.");
		
		//TODO add a chance that the company will signal the transaction to an authority
		
		if (accounts.size() < 2) //account proliferation is fun !
			new BankAccount((Bank)residence.getRandomNational("bank"), this);
		
		frauding = true;
		return(accounts.get(accounts.size()-1));
		
	}
	
	//TODO
	public void doBusiness()
	{
		// A frauding company with a big scale should make subcompagnies
	}
	
	public String toString()
	{
		return "Company -- "+name+" ("+residence+")";
	}
}
