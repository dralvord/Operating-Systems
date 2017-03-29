import java.util.Random;
import java.util.concurrent.*;

public class Supplier extends Smoker {
	
	public Supplier(Semaphore t, Semaphore p, Semaphore m, Semaphore s)
	{
		super(t,p,m,s);
	}

	public Boolean call() throws Exception
	{
		Random rand = new Random();
		int flip;
		int sleep;
		while(true)
		{
			sleep = rand.nextInt(500) + 500;
			Thread.sleep((long)sleep);
			flip = rand.nextInt(3);
			if(flip==0)
			{
				System.out.println("Supplier is putting paper on the table...");
				paper.release();
				System.out.println("Supplier is putting matches on the table...");
				matches.release();
			}
			else if(flip==1)
			{
				System.out.println("Supplier is putting paper on the table...");
				paper.release();
				System.out.println("Supplier is putting tobacco on the table...");
				tobacco.release();
			}
			else if(flip==2)
			{
				System.out.println("Supplier is putting tobacco on the table...");
				tobacco.release();
				System.out.println("Supplier is putting matches on the table...");
				matches.release();
			}
		}
	}
		
}
