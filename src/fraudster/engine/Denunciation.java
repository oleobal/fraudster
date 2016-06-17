package fraudster.engine;

/**
 * links a bank account to a Taxpayer
 *
 * when a denunciation is made, the game will check who's bank account it is, then so on and so forth until it gets to the end of the chain (the actual owner)
 * if he is frauding, then congrats !
 */
public class Denunciation
{
	private Taxpayer supposedFrauder;
	private BankAccount suspectedAccount;
	
	public Denunciation(BankAccount a, Taxpayer t)
	{
		supposedFrauder = t;
		suspectedAccount = a;
		
	}
	
	// well well well
	public boolean checkValidity()
	{
		if (supposedFrauder.isFrauding())
		{
			//TODO check validity of denounciation
			if (suspectedAccount.getOwner().isFrauding())
			{
				// now that doesn't mean it's valid, because they might be unrelated. We'll check who's the account owner's owner, and the owner's owner's owner, and so on
				return chainOfFrauders(supposedFrauder);
			}
		}
		return false;
	}
	
	/**
	 * recursive
	 * starts at supposedFrauder (well, you're supposed to pass it that) and gets down in the chain to try and get the bank account's owner
	 *
	 * returns true if the bank account's owner is in the possession of the supposed frauder
	 */
	private boolean chainOfFrauders(LegalEntity l)
	{
		boolean returnedValue = false;
		if (l.getPossessions().isEmpty())
			return false;
		
		for (LegalEntity i : l.getPossessions())
		{
			if (i == suspectedAccount.getOwner())
			{
				return true;
			}
			else
			{
				returnedValue = returnedValue || chainOfFrauders(i);
			}
		}
		
		return returnedValue;
	}
}