/*
 * Author: Dillon Alvord
 * File: TobaccoSmoker.java
 * Date: 3/29/17
 */
import java.util.concurrent.Semaphore;

public class TobaccoSmoker extends Smoker {
	
	public TobaccoSmoker(Semaphore p, Semaphore m, Semaphore s) 
	{
		super(null,p,m,s);
	}

	public Boolean call()
	{
		boolean pickUp = false;
		try
		{
			while(true)
			{
				matches.acquire();
				if(paper.tryAcquire())
				{
					System.out.println("Tobacco smoker is smoking....");
					Thread.sleep(1000);
					supplier.release();
					pickUp = true;
				}
				else
				{
					matches.release();
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
