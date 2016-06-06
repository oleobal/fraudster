package fraudster.engine;

import java.util.ArrayList;
import java.lang.IllegalArgumentException;

import java.io.Serializable;

// compagnies and taxpayers both are
public abstract class LegalEntity implements Serializable
{
	protected String name;
	protected Country residence;
	/**
	 * From 0 to 3, poorest to richest
	 * for someone, that's the number of additional zeroes on their paycheques
	 * for a company, 0 is the garage next door and 3 is Samsung
	 */
	protected Integer scale;
	/**
	 * Money not in an account
	 */
	protected Integer cash;
	
	/**
	 * whether this entity is frauding or not.
	 * makes the game more interesting, I heard
	 */
	protected Boolean frauding; 
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
	
	/**
	 * howRichTheyAre 0 to 3 (poorest to richest)
	 * values outside this range are scaled down
	 */
	public LegalEntity(String theirName, Country whereTheyLive, Integer howRichTheyAre) throws IllegalArgumentException
	{
		if (theirName.equals(""))
			throw new IllegalArgumentException("Empty string as legal entity name");
		
		this.name=theirName;
		this.residence = whereTheyLive;
		residence.addNational(this);

		possessions = new ArrayList<LegalEntity>();
		accounts = new ArrayList<BankAccount>();
		
		frauding = false; //everyone starts good, I hear. From Genevan fugitives, at least.
		
		if (howRichTheyAre<0)
			scale=0;
		else if (howRichTheyAre>3)
			scale=3;
		else
			scale=howRichTheyAre;
		
		cash=0;
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
	 * returns true if the parameter is a possession of this object
	 */
	 public Boolean checkPossessed(LegalEntity property)
	 {
		 if (this.possessions.contains(property))
			 return true;
		 else
			 return false;
	 }
	
	/**
	 * Fraud, yay !
	 *
	 */
	public void addAccount(BankAccount newAccount)
	{
		this.accounts.add(newAccount);
	}
	
	public void doBusiness()
	{
		//plz overwrite me
	}
	
	public Country getCountry()
	{
		return residence;
	}
	
	public String toString()
	{
		return name+" ("+residence+")";
	}
	
	public String getName()
	{
		return name;
	}
}
