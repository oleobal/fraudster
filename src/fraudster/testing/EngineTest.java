package fraudster.testing;

//import org.junit.Test;
//import org.junit.Assert;

import fraudster.engine.*;

public class EngineTest
{
	
	public static void main(String[] args)
	{
		Ledger theLedger = new Ledger();

		Country france = new Country("French Republic", theLedger);
		Country germany = new Country("Germany", theLedger);
		Country russia = new Country("Russian Federation", theLedger);
		
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
		Transaction banalTransaction = theLedger.newTransaction(someAccount, otherAccount, 9876); // funnily enough, it was precisely the amount needed to make a certain french minister worth over 1.3 million
		
		System.out.println(banalTransaction);
		
	
		System.out.println("==========");
		Investigator greatGuy = new Investigator(france);
		System.out.println(greatGuy);
	}



}
