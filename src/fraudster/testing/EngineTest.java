package fraudster.testing;

//import org.junit.Test;
//import org.junit.Assert;

import fraudster.engine.*;

public class EngineTest
{
	
	public void entireSetup()
	{
		Country france = new Country("French Republic");
		Country germany = new Country("Germany");
		Country russia = new Country("Russian Federation");
		
		france.changeRelations(germany, 1);
		france.changeRelations(russia, 8);
		
		LegalEntity basf = new Company("Baden Anilin und Something Fabrik", germany);
		Company igFarben = new Company("I.G. Farbenindustrie", germany);
		igFarben.addPossession(basf);
	
		Bank evilBank = new Bank("Злой банк", russia);
		
		BankAccount someAccount = new BankAccount(evilBank, basf);
		someAccount.changeBalance(9999);
		
		
		
	}



}
