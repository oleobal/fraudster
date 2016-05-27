package fraudster.engine;

import java.lang.IllegalArgumentException;

// compagnies and taxpayers both are
public abstract class LegalEntity
{
	private String name;
	private Country residence;
	private ArrayList<LegalEntity> possessions;

	public LegalEntity(String theirName, Country whereTheyLive) throws IllegalArgumentException
	{
		if (theirName.equals(""))
			throw new IllegalArgumentException("Empty string as country name");
		
		this.name=theirName;
		this.residence = whereTheyLive;

		possessions = new ArrayList<LegalEntity>;
	}


	/**
	 * Game can be extended as a slavery simulator
	 * That being said, we recommend only passing Company(ies) to this
	 */
	public void addPossession(LegalEntity newProperty)
	{
			
		this.possessions.add(newProperty);
	}
}
