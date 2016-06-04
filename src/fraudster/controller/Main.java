package fraudster.controller;

import fraudster.engine.*;

import java.util.Random;

import java.util.Scanner;
import java.util.NoSuchElementException;

public class Main
{
	
	public static void main(String[] args)
	{
		Ledger theLedger = new Ledger();
		theLedger.populate();
		
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