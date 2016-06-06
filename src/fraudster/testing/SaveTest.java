package fraudster.testing;

import fraudster.engine.*;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SaveTest
{
	public static void main(String[] args)
	{
		Ledger theLedger = new Ledger();
		theLedger.populate();
		theLedger.nextDay();
		theLedger.nextDay();
		theLedger.nextDay();
		theLedger.nextDay();
		
		
		try
		{
			FileOutputStream fout = new FileOutputStream("../savegames/autosave");
			ObjectOutputStream oos = new ObjectOutputStream(fout);   
			oos.writeObject(theLedger);
			oos.close();
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
}