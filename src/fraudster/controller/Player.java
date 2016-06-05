package fraudster.controller;

import fraudster.engine.*;

/**
 * wraps around an investigator
 */
public class Player
{
	String name;
	
	private Investigator character;
	
	public Player(String name, Country c)
	{
		character = new Investigator(name, c);
	}
	
	public String toString()
	{
		return "Player "+character.toString();
	}
	
	public String getName()
	{
		return character.getName();
	}
	
	public Country getCountry()
	{
		return character.getCountry();
	}
}