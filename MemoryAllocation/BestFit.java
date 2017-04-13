/*
*Author: Dillon Alvord
*Date: 4/12/17
*File: BestFit.java
*Function: Runs best fit algorithm for memory allocation
*/
import java.util.LinkedList;
public class BestFit implements ISwapper {

	private int max;
	private LinkedList<Node> list = new LinkedList<Node>(); 
	public void init(int memSize)
	{
		Node node = new Node(true, -1, memSize, 0, memSize-1);
		list.addFirst(node);
		max = memSize;
	}
	public boolean load(IProcess p, IMemory mem) throws BlueScreenException
	{
		int bestIndex = -1;
		int bestSize = max;
		Node temp;
		for(int i = 0; i < list.size();i++)
		{
			temp = list.get(i);
			if(temp.hole && temp.size >= p.getSize() && temp.size <= bestSize)
			{
				bestSize = temp.size;
				bestIndex = i;
			}
		}

		if(bestIndex >= 0)
		{
			temp = list.get(bestIndex);
			mem.load(p, temp.start , temp.start + p.getSize() - 1);
			Node node = new Node(false, p.getId(), p.getSize(), temp.start, temp.start + p.getSize() - 1);
			list.remove(bestIndex);
			list.add(bestIndex, node);
			if(node.end + 1 != max)
			{
				Node hole = new Node(true, -1, temp.end - p.getSize(), temp.start+ p.getSize(), temp.end);
				list.add(bestIndex + 1, hole);
			}
			return true;
		}
		else
		{
			return false;
		}
	}
	public void unload(IProcess p, IMemory mem) throws BlueScreenException
	{
		Node temp;
		Node temp2;
		for(int i = 0; i < list.size(); i++)
		{
			temp = list.get(i);
			if(temp.id == p.getId())
			{
				mem.unload(p);
				list.remove(i);
				Node hole = new Node(true, -1,p.getSize(), temp.start, temp.end);
				list.add(i, hole);
				break;
			}
		}
		for(int i = 0; i < list.size(); i++)
		{
			if(i < list.size() - 1)
			{
				temp = list.get(i);
				temp2 = list.get(i + 1);
				if(temp.hole && temp2.hole)
				{
					list.remove(i);
					Node hole = new Node(true, -1, temp.size + temp2.size, temp.start, temp2.end);
					list.add(i,hole);
				}
			}
		}
	}
}

class Node{
	boolean hole;
	int id;
	int size;
	int start;
	int end;
	
	public Node(boolean newHole, int newId, int newSize, int newStart, int newEnd)
	{
		hole = newHole;
		id = newId;
		size = newSize;
		start = newStart;
		end = newEnd;
	}
}