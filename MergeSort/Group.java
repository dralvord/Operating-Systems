/*
 * Author: Dillon Alvord
 * File: Group.java
 * Date: 4/30/17
 */

import java.util.ArrayList;
import java.util.List;

public class Group {
	
	private List<Integer> group;
	
	public Group(List<Integer> g)
	{
		group = g;
	}
	
	public List<Integer> getGroup()
	{
		return group;
	}
	
	public List<Integer> Merge(Group g)
	{
		List<Integer> mergedGroup = new ArrayList<Integer>();
		List<Integer> newGroup = g.getGroup();
		
		while(!group.isEmpty() && !newGroup.isEmpty())
		{
			if(group.get(0) < newGroup.get(0))
			{
				mergedGroup.add(group.get(0));
				group.remove(0);
			}
			else if(group.get(0) > newGroup.get(0))
			{
				mergedGroup.add(newGroup.get(0));
				newGroup.remove(0);
			}
			else
			{
				mergedGroup.add(group.get(0));
				mergedGroup.add(newGroup.get(0));
				group.remove(0);
				newGroup.remove(0);
			}
		}
		
		while(group.isEmpty() && !newGroup.isEmpty())
		{
			mergedGroup.add(newGroup.get(0));
			newGroup.remove(0);
		}
		
		while(!group.isEmpty() && newGroup.isEmpty())
		{
			mergedGroup.add(group.get(0));
			group.remove(0);
		}
		
		
		return mergedGroup;
		
	}
	
	public void PrintList()
	{
		for(int i = 0; i < group.size(); i++)
		{
			System.out.println(group.get(i));
		}
	}
}
