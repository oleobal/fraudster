package fraudster.engine;

import java.util.ArrayList;
import java.lang.IllegalArgumentException;

// compagnies and taxpayers both are
public abstract class LegalEntity
{
	protected String name;
	protected Country residence;
	protected ArrayList<LegalEntity> possessions;
	protected ArrayList<BankAccount> accounts;

	/**
	 * Should not be used
	 * name = "N/A";
	 * residence to new UNREGISTERED country called "N/A"
	 *
	public LegalEntity()
	{
		name="N/A";
		residence=new Country("N/A", new Ledger());

	}*/
	public LegalEntity(String theirName, Country whereTheyLive) throws IllegalArgumentException
	{
		if (theirName.equals(""))
			throw new IllegalArgumentException("Empty string as legal entity name");
		
		this.name=theirName;
		this.residence = whereTheyLive;

		possessions = new ArrayList<LegalEntity>();
		accounts = new ArrayList<BankAccount>();
	}


	/**
	 * Game can be extended as a slavery simulator
	 * That being said, we recommend only passing Company(ies) to this
	 */
	public void addPossession(LegalEntity newProperty)
	{
			
		this.possessions.add(newProperty);
	}
	
	/**
	 * Fraud, yay !
	 *
	 */
	public void addAccount(BankAccount newAccount)
	{
		this.accounts.add(newAccount);
	}
	
	public String toString()
	{
		return name+" ("+residence+")";
	}
}
