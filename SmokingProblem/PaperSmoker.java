/*
 * Author: Dillon Alvord
 * File: PaperSmoker.java
 * Date: 3/29/17
 */
import java.util.concurrent.*;
public class PaperSmoker extends Smoker{
	
	public PaperSmoker(Semaphore t, Semaphore m, Semaphore s) 
	{
		super(t,null,m,s);
	}

	public Boolean call()
	{
		boolean pickUp = false;
		try
		{
			while(true)
			{
				matches.acquire();
				if(tobacco.tryAcquire())
				{
					System.out.println("Paper smoker is smoking....");
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
