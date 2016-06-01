package fraudster.engine;

import java.util.Hashtable;
import java.util.ArrayList;
import java.lang.IllegalArgumentException;

public class Country
{
	private String name;
	
	/**
	 * List of people and companies in this country
	 */
	private ArrayList<LegalEntity> nationals;

	/**
	 * let's say we have relations as a number of days added to how much time they take to respond
	 */
	private Hashtable<Country, Integer> relations;

	/**
	 * Are you seriously wondering what this does ?
	 * Just some values initializations.
	 */
	public Country(String countryName) throws IllegalArgumentException
	{
		if (countryName.equals(""))
			throw new IllegalArgumentException("Empty Country name");
		this.name = countryName;
		relations = new Hashtable<Country, Integer>();
		nationals = new ArrayList<LegalEntity>();
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
	
	public String toString()
	{
		return name;
	}
}
