import java.util.concurrent.Semaphore;

public class MatchesSmoker extends Smoker{
	
	public MatchesSmoker(Semaphore t, Semaphore p, Semaphore s) 
	{
		super(t,p,null,s);
	}

	public Boolean call()
	{
		boolean pickUp = false;
		try
		{
			while(true)
			{
				paper.acquire();
				if(tobacco.tryAcquire())
				{
					System.out.println("Matches smoker is smoking....");
					Thread.sleep(1000);
					supplier.release();
					pickUp = true;
				}
				else
				{
					paper.release();
					pickUp = false;
				}
			}
		}
		catch(Exception error)
		{
			error.printStackTrace();
		}
		return pickUp;
	}
}
