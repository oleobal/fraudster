package fraudster.engine;

import java.lang.IllegalStateException;
import java.util.ArrayList;
import java.util.Random;

/**
 * the ones studying fraud and all
 * there's only one per country
 */
public class Investigator extends Taxpayer
{

	ArrayList<Denunciation> gloryKills;
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
			gloryKills = new ArrayList<Denunciation>();
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
		super(theirName, whereTheyLive, 1); // public employees, rich ? Not the tax evasion service, I'm sure.
		try
		{
			residence.addInvestigator(this);
			gloryKills = new ArrayList<Denunciation>();
		}
		catch (IllegalStateException e)
		{
			throw e;
		}

	}

	public void addDenunciation(Denunciation d)
	{
		gloryKills.add(d); //TODO sanitize
	}
	
	public String toString()
	{
		return "Investigator -- "+name+" ("+residence+")";
	}
}
