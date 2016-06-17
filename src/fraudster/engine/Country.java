package fraudster.engine;

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Random;

import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;
import java.util.NoSuchElementException;
import java.io.Serializable;

public class Country implements Serializable
{
	private String name;

	/**
	 * this country's fraud investigator
	 */
	private Investigator investigator;
	
	/**
	 * List of people and companies in this country
	 */
	private CopyOnWriteArrayList<LegalEntity> nationals;

	/**
	 * let's say we have relations as a number of days added to how much time they take to respond
	 */
	private Hashtable<Country, Integer> relations;

	/**
	 * a reference to the Ledger
	 */
	private Ledger theLedger;

	/**
	 * Just some values initializations.
	 */
	public Country(String countryName, Ledger mainLedger) throws IllegalArgumentException
	{
		if (countryName.equals(""))
			throw new IllegalArgumentException("Empty Country name");
		this.name = countryName;
		relations = new Hashtable<Country, Integer>();
		nationals = new CopyOnWriteArrayList<LegalEntity>();

		theLedger = mainLedger;
		theLedger.addCountry(this);
	}

	/**
	 * add an investigator to this country
	 */
	public void addInvestigator(Investigator i) throws IllegalStateException
	{
		if (this.investigator == null)
			this.investigator = i;
		else
			throw new IllegalStateException("Country already has an investigator");
	}
	
	public void addNational(LegalEntity e)
	{
		nationals.add(e);
	}

	/**
	 * Adds or changes relation level
	 * c country
	 * i relation level (positive integer, lower is better relations)
	 */
	public void changeRelations(Country c, Integer i)
	{
		relations.put(c, i);

	}

	/**
	 * returns a random national of the country
	 * parameter : what type of national
	 * amongst "company", "rich-company", "bank", "taxpayer", "rich-taxpayer", "company-not-bank", "rich-company-not-bank";
	 *
	 * why not generic types or whatnot, I hear you ask
	 * answer is a mixture of fine-grained control and laziness
	 */
	
	// not sure there's a use for this actually
	public LegalEntity getRandomNational(String type) throws NoSuchElementException
	{
		ArrayList<LegalEntity> theOnesWeWant = new ArrayList<LegalEntity>();

		if (type.equals("company"))
		{
			for (LegalEntity i:nationals)
			{
				if (i instanceof Company)
				{
					theOnesWeWant.add(i);
				}
			}
		}
		if (type.equals("rich-company"))
		{
			for (LegalEntity i:nationals)
			{
				if (i instanceof Company)
				{
					if (i.getScale() == 3)
						theOnesWeWant.add(i);
				}
			}
		}
		if (type.equals("bank"))
		{
			for (LegalEntity i:nationals)
			{
				if (i instanceof Bank)
				{
					theOnesWeWant.add(i);
				}
			}
		}
		if (type.equals("taxpayer"))
		{
			for (LegalEntity i:nationals)
			{
				if (i instanceof Taxpayer)
				{
					theOnesWeWant.add(i);
				}
			}
		}
		if (type.equals("rich-taxpayer"))
		{
			for (LegalEntity i:nationals)
			{
				if (i instanceof Taxpayer)
				{
					if (i.getScale() == 3)
						theOnesWeWant.add(i);
				}
			}
		}

		if (type.equals("company-not-bank"))
		{
			for (LegalEntity i:nationals)
			{
				if (i instanceof Company && !(i instanceof Bank))
				{
					theOnesWeWant.add(i);
				}
			}
		}

		if (type.equals("rich-company-not-bank"))
		{
			for (LegalEntity i:nationals)
			{
				if (i instanceof Company && !(i instanceof Bank))
				{
					if (i.getScale() == 3)
						theOnesWeWant.add(i);
				}
			}
		}
		
		if (theOnesWeWant.isEmpty())
			throw new NoSuchElementException("No "+type+" in this country.");
		
		return theOnesWeWant.get((new Random()).nextInt(theOnesWeWant.size()));
	
	}
	
	/**
	 * queries the ledger and returns a country that is not this one
	 * this is to model a citizen asking the administration
	 * no, really, this avoids me having to pass the ledger to every single LegalEntity, by using their country as proxy
	 *
	 * @param type : currently ignored
	 */
	public Country getOtherCountry(String arg) throws NoSuchElementException//TODO have this argument filter countries, like low tax rate, bad relations, etc.
	{
		ArrayList<Country> lol = theLedger.getCountries(); Country i;
		if (lol.size() == 1)
			throw new NoSuchElementException("There's only one country : us.");
		while ((i = lol.get((new Random()).nextInt(lol.size())))  !=  this) //trust me on that one
			return i; //FIXME: seems like I shouldn't have trusted myself. Looks like it can return itself, which it shouldn't
		
		return this; //we'll never get to this, if all goes well :x
	}
	
	/**
	 * same than with getOtherCountry, passing the ledger to every LegalEntity would be annoying
	 * but this has an added bonus : this means the state can look at a passing transaction !
	 *
	 * it just relays the query to the ledger, and relays back the object and exceptions
	 */
	public Transaction newTransaction(BankAccount from, BankAccount to, Integer howMuch) throws IllegalStateException, IllegalArgumentException
	{
		try
		{
			return theLedger.newTransaction(from, to, howMuch);
		}
		catch (RuntimeException e)
		{
			throw e;
		}
		
	}
	
	/**
	 * a Company signaling there's something fishy going on, as a fraudulent transaction takes place (Company.pleaseFraud())
	 * @param suspectAccount the account that will receive the money
	 */
	public void signalFraud(BankAccount suspectAccount)
	{
		//FIXME IT'S NOT WORKING
		//TODO signal to ledger and all
		
	}
	
	/**
	 * similar to the LegalEntity doBusiness() method, this activates the doBusiness() of every national, and maybe do a little State stuff too
	 */
	public void doBusiness()
	{
		int k=0;
		for (LegalEntity i : nationals)
		{
			i.doBusiness();
			k++;
		}
		theLedger.log("(country) "+this.toString()+" activated "+k+" nationals");
	}
	
	
	public Hashtable<Country, Integer> getRelations()
	{
		@SuppressWarnings("unchecked")
		Hashtable<Country, Integer> lol = (Hashtable<Country, Integer>)relations.clone();
		return lol;
	}
	
	/**
	 * ArrayList with all compagnies in the realm
	 */
	public ArrayList<Company> getCompanies()
	{
		ArrayList<Company> comps = new ArrayList<Company>();
		Company lol;
		for (LegalEntity i:nationals)
		{
			if (i instanceof Company)
			{
				//@SuppressWarnings("unchecked")
				lol = (Company)i;
				comps.add(lol);
			}
		}

		return comps;
	}

	/**
	 * relays the message to the ledger's log
	 */
	public void log(String message)
	{
		theLedger.log(message);
	}
	
	public String toString()
	{
		return name;
	}
}
