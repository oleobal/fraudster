package fraudster.engine;

import java.lang.IllegalStateException;
import java.util.Random;
// taxpayers can only have one bank account
public class Taxpayer extends LegalEntity
{
	private static final int CHANCE_TO_START_FRAUDING = 100; //each day if they're rich enough
	
	/**
	 * random name and wealth
	 */
	public Taxpayer(Country whereTheyLive)
	{
		super("N/A", whereTheyLive, 0);
		String[] firstNames = {"John", "Felicia", "Cecil", "Jesus", "Max", "Vladimir", "Tien", "Harold", "Charles", "Anna", "Noah", "Liam", "Mason", "Jacob", "William", "Richard", "Ethan", "James", "Alexander", "Michael", "Benjamin", "Robert", "David", "Joseph", "Thomas", "Maria", "Emma", "Sophia", "Olivia", "Emily", "Lily", "Elizabeth", "Victoria", "Esther", "Rachel", "Sarah", "Ashley", "Grace", "Rose", "Noemi", "Gabriel", "Mohamed", "Omar", "Ali", "Adam", "Hamza", "Hakim", "Wei", "Jian", "Bin", "Hao", "Ren", "George", "Jing", "Maya", "Fatima", "Aisha", "Chaimaa", "Raihanne", "Marko", "Jovan", "Yohan", "Yann", "Dorian", "Radovan", "Sergei", "Serge", "Jack", "Jacques", "Juan", "Lucas", "Lukas", "Maxim", "Louis", "Tobias", "Samuel", "Ivan", "Nicolas", "Nikolai", "Olivier", "Oliver", "Harry", "Oscar", "Benjamin", "Rasmus", "Raphael", "Rafael", "Arthur", "Jules", "Julian", "Julien", "Yasmine", "Lea", "Lana", "Lucija", "Petra", "Dora", "Amelia", "Louise", "Alice", "Anastasia", "Steve", "Akif", "Mehmet", "Jefferson", "Lewis"};
		String[] lastNames = {"Doe","Hardy", "Khonnagh", "Thompson", "Zola", "Thomson", "ter Meer", "Schmidt", "Anderson", "Zhen", "Meier", "Rodriguez", "Nutarelli", "Pavlov", "Domsky", "McConnaugh", "Peters", "Peeters", "Janssens", "Willems", "Novak", "Dvorak", "Lerdorf", "Pedersen", "Larsen", "Andersen", "Saar", "Koppel", "Korhonen", "Lehtonen", "Martin", "Dubois", "Bernard", "Lefebvre", "Garcia", "Martinez", "Da Silva", "Muller", "Schmidt", "Schneider", "Weber", "Wagner", "Hoffman", "Papadopoulos", "Kiolaidis", "Antoniou", "Nagy", "Szabo", "Murphy", "Kelly", "Sullivan", "Connor", "McCarthy", "Murray", "Ferrari", "Rossi", "Bianchi", "Romano", "Conti", "Van den Berg", "Van Dijk", "Jansen", "De Vries", "De Groot", "Nowak", "Kowalski", "Wozniak", "Jobs", "Kaczmarek", "Ferreira", "Costa", "Rodrigues", "Lopes", "Lopez", "Cruz", "Popa", "Radu", "Ivanov", "Smirnov", "Popov", "Petrov", "Bogdanov", "Lebedev", "Sokolov", "Tchekhov", "Platonov", "Petrovna", "Novak", "Turk", "Romero", "Demir", "Kaya", "Sahin", "Smith", "Jones", "Taylor", "Brown", "Johnson", "Davies", "Clarke"};
		
		Random rand = new Random();
		name=firstNames[rand.nextInt(firstNames.length)]+" "+lastNames[rand.nextInt(lastNames.length)];
		
		int lol = rand.nextInt(10); //proportions are obviously not actual ones..
		if (lol==0)
			scale=3; // 1/10
		else if (lol<3)
			scale=2; // 2/10
		else if (lol<7)
			scale=1; // 4/10
		else if (lol<10)
			scale=0; // 3/10
	}

	/**
	 * wealth from 0 to 3 (poorest to richest)
	 */
	public Taxpayer(String theirName, Country whereTheyLive, Integer howRichTheyAre)
	{
		super(theirName, whereTheyLive, howRichTheyAre);
	}

	/**
	 * Unlike companies, Taxpayers can only have one account.
	 * If our taxpayer already has one, an exception is thrown
	 */
	public void addAccount(BankAccount newAccount) throws IllegalStateException
	{
		if (accounts.isEmpty())
			accounts.add(newAccount);
		else
			throw new IllegalStateException("This taxpayer already has an account");
		
	}

	/**
	 * Every day this method is activated and the Taxpayer will take decisions
	 *
	 */
	//this is where the magic happens
	public void doBusiness()
	{
		// first our pal recieves money based on his scale modifier
		Random rand = new Random();
		if (scale == 0)    //normal people
			cash+=50+rand.nextInt(20);
		else if (scale==1) //successful people
			cash+=400+rand.nextInt(200);
		else if (scale==2) //successful managers
			cash+=3000+rand.nextInt(2000);
		else if (scale==3) //time to buy a new villa
			cash+=20000+rand.nextInt(20000);
		
		
		if (accounts.isEmpty()) //if he doesn't have an account he'll just create one
		{
			//querying his country for a bank
			new BankAccount((Bank)residence.getRandomNational("bank"), this);
			// (reminder : the account's constructor links itself to the client's and the bank's attributes)
		}
		
		// now if he got enough money he'll try to put in an account
		if (!frauding && cash>1000)
		{
			//TODO reflect on whether banks should charge for changes in balance
			(accounts.get(0)).changeBalance(cash); cash=0;
		}
		
		
		// and now our pal might not be our pal anymore, for he'll begin to think about embezzlement in a way normal people shouldn't
		if (frauding == false &&(accounts.get(0)).getBalance() > 50000 && (rand.nextInt(CHANCE_TO_START_FRAUDING)==0))
		{
			// if they're rich enough, every day there's 1/50 chance they'll start frauding 
			// TODO tweak depending on game variables
			// if there's a lot of people in the game, this much is not necessary
			// TODO if I do add country tax rate, add it in the mix
			
			frauding=true; //GOD, NO !
			residence.log(this+" has started frauding");
		}
		
		// If he's frauding he'll keep bis cash on hand to embezzle it instead of putting it on a local account
		if (frauding && cash>5000)
		{
			// he/she establishes a chain between him/herself and a foreign company
			// the depth depends on the scale modifier of our taxpayer
			// actually no
			
			// he/she asks one of her compagnies to fraud through the pleaseFraud() method, which gives him/her back a bank account she can put money on
			Company choice = null; // I know this is stupid
			for (LegalEntity i : possessions)
			{
				// idea here is that we'll take any Company, but favour companies in other countries
				if (i instanceof Company && i.getCountry() != this.residence) // that test should work because there should only be one instance of each country, so it's the same object
					choice = (Company)i;
				else if (i instanceof Company)
					choice = (Company)i;
			}
			
			if (choice == null) //looks like we don't have much options
			{
				Integer newScale = this.scale-2;
				if (newScale<0)
					newScale=0;
				//make a company in another country
				Company lol = new Company(residence.getOtherCountry("low-tax-rate"), newScale);
				possessions.add(lol);
				
				residence.log("(frauding) New company !\n"+lol+"\nFrauder : "+this.toString());
				
				choice = lol;
			}
			
			BankAccount targetAccount = choice.pleaseFraud(this);
			accounts.get(0).changeBalance(cash); cash = 0;
			residence.log("(frauding) "+residence.newTransaction(accounts.get(0), targetAccount, accounts.get(0).getBalance()));
			
		}
	}

	public String toString()
	{
		String result = "Taxpayer -- "+name+" ("+residence+")";
		if (scale == 0)
			result+=" (poor, ";
		else if (scale == 1)
			result+=" (well-off, ";
		else if (scale == 2)
			result+=" (rich, ";
		else if (scale == 3)
			result+=" (very rich, ";
		
		if (accounts.isEmpty())
			result+="no account)";
		else
			result+=accounts.get(0).getBalance()+" $ in main account)";
		
		return result;
	}
}
