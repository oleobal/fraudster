package fraudster.testing;

//import org.junit.Test;
//import org.junit.Assert;

import fraudster.engine.*;

public class EngineTest
{
	
	public static void main(String[] args)
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
		
		System.out.println("==========");
		System.out.println(someAccount);
		
		Bank notAtAllSuspiciousBank = new Bank("Banca del developmento del Mexico Inc.", germany);
		BankAccount otherAccount = new BankAccount(notAtAllSuspiciousBank, igFarben);
		
		System.out.println("==========");
		Transaction banalTransaction = new Transaction(someAccount, otherAccount, 9876); // funnily enough, it was precisely the amount needed to make a certain french minister worth over 1.3 million
		
		System.out.println(banalTransaction);
		
	}



}
