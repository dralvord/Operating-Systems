/*
 * Author: Dillon Alvord
 * Date: 2/22/17
 * File: Worker.java
 * 
 */
import java.util.*;


public class Worker
{
  // can easily be run 'stand alone'...  
	public static void main(String args[]) throws Exception
	{
		new Worker().go();
	}

	private void go() throws Exception
	{
		Scanner scan = new Scanner(System.in);
		Point[] points = new Point[Master.NUM_POINTS/Master.NUM_GROUPS];
		Point closestPoint = new Point();
		double closestDistance = 10000000;
		double distance = 0;	
		int xValue = scan.nextInt();
		int yValue = scan.nextInt();
		
		Point referencePoint = new Point(xValue,yValue);
		
		for(int i = 0; i < Master.NUM_POINTS/Master.NUM_GROUPS;i++)
		{	//Receive the points from the master class
			//points[i] = new Point(xValue, yValue);
			xValue = scan.nextInt();
			yValue = scan.nextInt();
			//xValue =i;
			//yValue = i;
			points[i] = new Point(xValue, yValue);
		}
		
		for(int i = 0; i <Master.NUM_POINTS/Master.NUM_GROUPS; i++)
		{	//Find the closest point in the set
			distance = Math.hypot(points[i].GetXValue()-referencePoint.GetXValue(), 
								  points[i].GetYValue()-referencePoint.GetYValue());
			if(distance < closestDistance)
			{
				closestPoint.SetPoint(points[i].GetXValue(), points[i].GetYValue());
				closestDistance = distance;
			}
		}
		System.out.printf("%d%n%d%n",closestPoint.GetXValue(),closestPoint.GetYValue());
		
	}
}