/*
 * Author: Dillon Alvord
 * File: MergeSort.java
 * Date: 4/30/17
 */

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class MergeSort
{
    public static void main(String[] args) throws Exception
    {
        new MergeSort().go();
    }
    
    private void go() throws Exception
    {
    	int items;
        int groups;
        Scanner keys = new Scanner(System.in);
        System.out.println("Please enter number of values to be generated(Power of 2): ");
        items = keys.nextInt();
        groups = items;
        
        Random r = new Random();
        List<Group> list = new ArrayList<Group>();
        List<Integer> tempList;
        Group g;
        
        for(int i = 0; i < groups;i++)
        {
        	tempList =  new ArrayList<Integer>();
        	tempList.add(r.nextInt(100));
        	g = new Group(tempList);
        	list.add(g);
        }
        
        
        groups = list.size();
        
        List<Callable<Group>> tasks = new ArrayList<Callable<Group>>();
        
        for(int i = 0; i < (Math.log(items)/Math.log(2)); i++)
        {
            for(int j = 0; j < groups; j+=2)
            {
                Callable<Group> task = new MyThread(list.get(j), list.get(j+1));
    			tasks.add(task);
            }
            
            ExecutorService executor = Executors.newFixedThreadPool(groups/2);
    		List<Future<Group>> futures = executor.invokeAll(tasks);
    		list.clear();
    		
    		for (Future<Group> f : futures)
    		{
    			g = new Group(f.get().getGroup());
    			list.add(g);
    		}
            groups = groups/2;
            
            for(int j = 0; j < list.size();j++)
            {
            	System.out.println("Group " + (j + 1) + ":");
            	g = list.get(j);
            	g.PrintList();
            	System.out.println();
            }
            futures.clear();
            tasks.clear();
        }
        
        g = list.get(0);
        g.PrintList();
        
    }
    
    public class MyThread implements Callable<Group>
	{
		private Group group1;
		private Group group2;
		private Group mergedGroup;
		
		public MyThread(Group g1, Group g2)
		{
			group1 = g1;
			group2 = g2;
		}
		
		@Override
		public Group call() throws Exception
		{
			mergedGroup = new Group(group1.Merge(group2));
			return mergedGroup;
		}	
		

	}
}