package fraudster.engine;

import java.lang.IllegalStateException;
import java.util.Random;

/**
 * the ones studying fraud and all
 * there's only one per country
 */
public class Investigator extends Taxpayer
{

	/**
	 * throws IllegalStateException if the country already has an investigator
	 *
	 * random name
	 */
	public Investigator(Country employer) throws IllegalStateException
	{
		super(employer);
		try
		{
			residence.addInvestigator(this);
		}
		catch (IllegalStateException e)
		{
			throw e;
		}

	}

	/**
	 * throws IllegalStateException if the country already has an investigator
	 */
	public Investigator(String theirName, Country whereTheyLive) throws IllegalStateException
	{
		super(theirName, whereTheyLive);
		try
		{
			residence.addInvestigator(this);
		}
		catch (IllegalStateException e)
		{
			throw e;
		}

	}

	public String toString()
	{
		return "Investigator -- "+name+" ("+residence+")";
	}
}
