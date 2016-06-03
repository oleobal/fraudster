package fraudster.engine;


/**
 * Empty shell companies require only 5 lines of code
 */
public class Company extends LegalEntity
{
	public Company(String n, Country c)
	{
	 super(n, c);
	}

	public String toString()
	{
		return "Company -- "+name+" ("+residence+")";
	}
}
