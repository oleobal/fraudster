package fraudster.controller;

import fraudster.engine.*;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * wraps around an investigator
 */
public class Player implements Serializable
{
	//private ArrayList<LegalEntity> addressBook;
	private ArrayList<Object> addressBook; //unless you know another way to get both LegalEntities and BankAccounts in there..
	
	public Investigator character;
	
	public Player(String name, Country c)
	{
		character = new Investigator(name, c);
		addressBook = new ArrayList<Object>();
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
	
	public ArrayList<Object> getAddressBook()
	{
		ArrayList<Object> buffer = new ArrayList<Object>();
		for (Object i : addressBook)
		{
			buffer.add(i);
		}
		return buffer;
	}
	
	public void addToAddressBook(Object l)
	{
		if (!addressBook.contains(l)) //should have been a Set..
		{
			addressBook.add(l);
		}
		
	}
	
	public void removeFromAddressBook(Object l)
	{
		addressBook.remove(l);
	}
}