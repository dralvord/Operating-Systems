/*
 * Author: Dillon Alvord
 * File: Main.java
 * Date: 3/29/17
 */
import java.util.concurrent.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception
	{
		Semaphore tobacco = new Semaphore(0, true);
		Semaphore paper = new Semaphore(0,true);
		Semaphore matches = new Semaphore(0,true);
		Semaphore supplier = new Semaphore(0,true);
		List<Callable<Boolean>> threads = new ArrayList<Callable<Boolean>>();
		
		threads.add(new MatchesSmoker(tobacco, paper, supplier));
		threads.add(new PaperSmoker(tobacco, matches, supplier));
		threads.add(new TobaccoSmoker(paper, matches, supplier));
		threads.add(new Supplier(tobacco, paper,matches, supplier));
	
		ExecutorService executor = Executors.newFixedThreadPool(4);
		
		executor.invokeAll(threads);
		executor.shutdown();
	}
}
