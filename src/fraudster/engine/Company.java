package fraudster.engine;

import java.util.Random;
import java.lang.IllegalArgumentException;

/**
 * Empty shell companies require only 5 lines of code
 */
public class Company extends LegalEntity
{
	public Company(String n, Country c, Integer w)
	{
	 super(n, c, w);
	}

	/**
	 * random name
	 */
	public Company(Country c, Integer w)
	{
		super("N/A", c, w);
		
		this.name=getRandomName();
		
		
	}
	
	/**
	 * random name and wealth
	 */
	public Company(Country c)
	{
		super("N/A", c, 0);
		this.name=getRandomName();
		
		int lol = (new Random()).nextInt(10); //proportions are obviously not actual ones..
		if (lol==0)
			scale=3; // 1/10
		else if (lol<3)
			scale=2; // 2/10
		else if (lol<7)
			scale=1; // 4/10
		else if (lol<10)
			scale=0; // 3/10
	}
	
	
	public String getRandomName()
	{
		// TODO expand to other languages
		// apparently people use chinese names for embezzlements to complicate things
		String[][] firstArg = {
			{"Pecha", "Little", "Development", "Air", "Victory", "Standard", "Ware", "Software", "Agile", "Poly", "Work", "Hardware", "Culinary", "Delicious", "Bio", "Organic", "Colour", "Red", "Swift"},
			{"Societe", "Groupe", "Groupement", "Compagnie", "Amis", "Amateurs", "Espoir", "Joseph", "Magasins", "Restaurant", "Bleu"},
			{"I.G.", "Badische", "Maschinen", "Nurnberg", "Mercedes", "Volks", "Stadt", "Ulm", "Essen", "Schwarz", "Weiss"},
			{"Companía", "Fabrica", "Comercio", "Industria", "Sociedad", "Espanol", "Barcelona", "Madeira", "Mexico", "Carioca"}
		};
			
		String[][] secondArg = {
			{"Banana", "Life", "Group", "Fishes", "Coop", "Oil", "Mix", "House", "Fun", "Love", "Home", "Steel"},
			{"Associes", "Chimie", "Developpement", "Investissement", "Meubles", "Tresors", "Antiquités", "Poisson", "Fibre", "Acier"},
			{"Fabrik", "Soda", "Gruppe", "Anilin", "Farbenindustrie", "Wagen", "Silber", "Jagdwaffen", "Chemie", "Stahl"},
			{"Logistica", "de Hidrocarburos", "Acos", "Aviación", "Infraestructuras", "Bocadillo", "Sandwiches", "Inversiones", "Automoviles"}
		};
		String[][] thirdArg = {
			{"Incorporated", "Inc.", "Cooperative", "Coop.", "PLC", "Limited", "Ltd.", "Corporation", "Corp.", "Company", "LLP", "LLC"},
			{"S.A.", "Societe Anonyme", "SARL", "SPRL", "EURL", "S.a.r.l.", "SAS", "SASU", "SCOP", "SCS", "S.C.A."},
			{"A.G.", "SE", "Gmbh", "Aktiengesellschaft", "GesmbH", "e.V.", "KGaA", "engetragenen Verein"},
			{"S.A.", "Sociedad Anonima", "S. en C.", "S.C.", "Sociedad Limitada S.R.L.", "Sociedad Colectiva"}
		};
		
		
		Random rand = new Random();
		int language = rand.nextInt(4);

		String result = firstArg[language][rand.nextInt(firstArg[language].length)];
		result+= " "+secondArg[language][rand.nextInt(secondArg[language].length)];
		result+= " "+thirdArg[language][rand.nextInt(thirdArg[language].length)];
	
		return result;
	}
	
	/**
	 * An owner can ask a company he/she owns to fraud so his money can get out the country.
	 * 1/200 chance the company will signal the authorities the fraud, enabling this entire game
	 * 
	 * @param owner The owner should pass himself so not anyone can ask a company to fraud
	 */
	public BankAccount pleaseFraud(LegalEntity owner) throws IllegalArgumentException
	{
		
		if (owner.checkPossessed(this) == false)
			throw new IllegalArgumentException("You can't ask a company you don't own to fraud.");
		
		//TODO add a chance that the company will signal the transaction to an authority
		
		if (accounts.size() < 2) //account proliferation is fun !
			new BankAccount((Bank)residence.getRandomNational("bank"), this);
		
		frauding = true;
		
		// signal it to the authorities !
		// this is a more-honest-than-the-rest employee kollaborating
		if ((new Random()).nextInt(200) == 0)
		{
			residence.signalFraud(accounts.get(accounts.size()-1));
			residence.log("(frauding) Fraud signaled !\n"+this+"\n"+owner);
		}
		
		return(accounts.get(accounts.size()-1));
	}
	
	/**
	 * day-to-day operation for businesses
	 * - chance to level up in scale based on wealth
	 * - chance to open branches to other countries
	 * - chance to distribute money to branches
	 * - and the good frauding stuff, transmitting money to subcompagnies
	 * 
	 * it should be noted companies separated normal and frauding operations, they use two different accounts
	 * they also won't start frauding by themselves
	 */
	public void doBusiness()
	{
		//for inner workings see Taxpayer.doBusiness(), it's pretty much the same.
		//although companies never start frauding on their own, it's always a Taxpayer telling them to
		//the subtetly here is that the account 0 is the main one and the (max-1) is the frauding one
		//thus frauding and normal operations are separated
		//there could be more accounts, but, uh..
		
		Random rand = new Random();
		if (scale == 0)    //normal people
			cash+=500+rand.nextInt(200);
		else if (scale==1) //successful people
			cash+=4000+rand.nextInt(2000);
		else if (scale==2) //successful managers
			cash+=30000+rand.nextInt(20000);
		else if (scale==3) //time to buy a new villa
			cash+=200000+rand.nextInt(200000);
		
		if (accounts.isEmpty())
		{
			new BankAccount((Bank)residence.getRandomNational("bank"), this);
		}
		accounts.get(0).changeBalance(cash); cash=0;
		
		
		// Level up !
		if (scale==0 && accounts.get(0).getBalance()>1000000 && rand.nextInt(50)==0) // 1 million
		{
			scale++;
			accounts.get(0).changeBalance(-750000); //investissement de capacit�, comme on dit
		}
		else if (scale==1 && accounts.get(0).getBalance()>5000000 &&rand.nextInt(100)==0) // 5 millions
		{
			scale++;
			accounts.get(0).changeBalance(-4800000);
		}
		else if (scale==2 && accounts.get(0).getBalance()>200000000 && rand.nextInt(100)==0) //200 millions
		{
			scale++;
			accounts.get(0).changeBalance(-180000000);
		}
		
		
		//new branches
		if (scale>1 && accounts.get(0).getBalance()>5000000 && rand.nextInt(100)==0) //5 millions
		{
			//extend operations
			Company lol = new Company(residence.getOtherCountry(""), 1);
			possessions.add(lol);
		}
		
		//sending money down to sub-companies
		if (accounts.get(0).getBalance()>1000000 && !possessions.isEmpty() && rand.nextInt(5)==0 )
		{
			LegalEntity winner = possessions.get(rand.nextInt(possessions.size()));
			
			residence.newTransaction(accounts.get(0), winner.accounts.get(0), 1000000); //1 million
			//FIXME: this can result in problems if the target doesn't have an account
			//I think that's the source at least
		}
		
		
		if (frauding && scale>2 && accounts.get(accounts.size()-1).getBalance()>50000)
		{
			// let's give some of that fraud money to another company
			Company choice = null;
			for (LegalEntity i : possessions)
			{
				//TODO: the money can come back to the original pal's country of residence
				//possessions knowing their owner wouldn't be ideal
				//implementing the tax rate idea would be
				if (i instanceof Company && i.getCountry() != this.residence)
					choice = (Company)i;
			}
			// no suitable company found
			if (choice == null)
			{
				//create a new one !
				int newScale = this.scale-2;
				if (newScale<0)
					newScale=0;
				//make a company in another country
				Company lol = new Company(residence.getOtherCountry("low-tax-rate"), newScale);
				possessions.add(lol);
				
				residence.log("(frauding) New company !\n"+lol+"\nFrauder : "+this.toString());
				
				choice = lol;
			}
			
			BankAccount targetAccount = choice.pleaseFraud(this); //company sends back an account to depose money in
			
			
			int amountToBeSent = 0, accountBuffer = accounts.get(accounts.size()-1).getBalance();
			while (accountBuffer>1000)
			{ amountToBeSent+=1000;accountBuffer-=1000; } // increments of 1000 being cleaner and all
			residence.log("(frauding) "+residence.newTransaction(accounts.get(accounts.size()-1), targetAccount, amountToBeSent));
		}
	}
	
	public String toString()
	{
		if (scale == 0)
			return "Company -- "+name+" ("+residence+") (small)";
		else if (scale == 1)
			return "Company -- "+name+" ("+residence+") (medium)";
		else if (scale == 2)
			return "Company -- "+name+" ("+residence+") (large)";
		else if (scale == 3)
			return "Company -- "+name+" ("+residence+") (trust)";
		else
			return "Company -- "+name+" ("+residence+")";
	}
}
