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
		return(accounts.get(accounts.size()-1));
		
	}
	
	//TODO
	public void doBusiness()
	{
		// A frauding company with a big scale should make subcompagnies
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
