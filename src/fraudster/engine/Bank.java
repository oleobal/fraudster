package fraudster.engine;

public class Bank extends Company
{
	private ArrayList<BankAccount> hostedAccounts;

	public Bank(String banksName)
	{
		this.name = banksName; //field inherited from LegalEntity
		hostedAccounts = new ArrayList<BankAccount>;
	}
	
	/**
	 * yay, we got richer
	 * please give us your ID so we can link it to the account
	 */
	public BankAccount newAccount(LegalEntity l)
	{
		BankAccount a = new BankAccount(this, l);
		hostedAccounts.add(a);
		return a;
	}

}
