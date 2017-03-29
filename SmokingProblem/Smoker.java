import java.util.*;
import java.util.concurrent.*;
public abstract class Smoker implements Callable<Boolean>
{
	protected final Semaphore tobacco;
	protected final Semaphore paper;
	protected final Semaphore matches;
	protected final Semaphore supplier;
	
	public Smoker(Semaphore t, Semaphore p, Semaphore m, Semaphore s)
	{
		tobacco = t;
		paper = p;
		matches = m;
		supplier = s;
	}
	
	public abstract Boolean call() throws Exception;
}
