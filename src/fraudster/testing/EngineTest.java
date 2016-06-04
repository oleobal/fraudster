package fraudster.testing;

//import org.junit.Test;
//import org.junit.Assert;

import fraudster.engine.*;

import java.util.Scanner;

import java.util.NoSuchElementException;

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
		
		LegalEntity basf = new Company("Baden Anilin und Something Fabrik", germany, 3);
		Company igFarben = new Company("I.G. Farbenindustrie", germany, 4);
		igFarben.addPossession(basf);
	
		Bank someBank = new Bank("Société Paribas", france, 3); //ambitious merger
		Bank evilBank = new Bank("Злой банк", russia, 2);
		
		BankAccount someAccount = new BankAccount(evilBank, basf);
		someAccount.changeBalance(9999);
		
		System.out.println("==========");
		System.out.println(someAccount);
		
		Bank notAtAllSuspiciousBank = new Bank("Banca del developmento del Mexico Inc.", germany, 2);
		BankAccount otherAccount = new BankAccount(notAtAllSuspiciousBank, igFarben);
		
		System.out.println("==========");
		Transaction banalTransaction = theLedger.newTransaction(someAccount, otherAccount, 9876); // funnily enough, it was precisely the amount needed to make a certain french minister worth over 1.3 million
		
		System.out.println(banalTransaction);
		
	
		System.out.println("==========");
		Investigator greatGuy = new Investigator(france);
		System.out.println(greatGuy);
		
		System.out.println("==========");
		Taxpayer richDude = new Taxpayer("Richie McRichface", france, 4);
		Taxpayer otherRichDude = new Taxpayer("Sigmund Krupp", germany, 4);
		
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			theLedger.nextDay();
			System.out.println("Day "+theLedger.getDay());
			/*
			System.out.println("Transactions of the day :");
			System.out.println("-------------------------");
			for (Transaction i : theLedger.getTransactions(theLedger.getDay()))
			{
				if (i != null)
					System.out.println(i);
			}
			*/
			System.out.println("Log messages of the day :");
			System.out.println("-------------------------");
			try
			{
				for (String i : theLedger.getLog(theLedger.getDay()))
				{
					System.out.println(i);
					System.out.println("");
				}
					
			}
			catch (NoSuchElementException e)
			{
				System.out.println("No entries");
			}
			sc.nextLine();
		}
	}



}
