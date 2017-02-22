
import java.io.*;
import java.util.*;

public class Master
{
	public final static int NUM_POINTS = 10;
	public final static int NUM_GROUPS = 5;
	final int MAX_VALUE = 10;
	Point[] points = new Point[NUM_POINTS];
	Point[] closestPoints = new Point[NUM_GROUPS];
	
	public static void main(String args[]) throws Exception
	{
		new Master().go();
	}

	private void go() throws Exception
	{
		Scanner keys = new Scanner(System.in);
		System.out.println("Please input a point to find the closest value.(Range -" + MAX_VALUE + " to " + MAX_VALUE + ")");
		System.out.print("X value: ");
		int x = keys.nextInt();
		System.out.print("Y value: ");
		int y = keys.nextInt();
		double distance;
		double closestDistance = -1;
		Point closestPoint = new Point();
		
		while((x < -MAX_VALUE || x > MAX_VALUE) && (y < -MAX_VALUE || y > MAX_VALUE))
		{
			System.out.print("X value: ");
			x = keys.nextInt();
			System.out.print("Y value: ");
			y = keys.nextInt();
		}
		Point referencePoint = new Point(x,y);
		
		createPoints();
		System.out.println();
		int count = 0;
		List<Process> list = new ArrayList<Process>();
		for (int i = 0; i < NUM_GROUPS; i++)
		{
			System.out.printf("Starting process %d ...%n",i);
			Process p = new ProcessBuilder("java", "Worker").start();
			sendReference(p, referencePoint);
			for(int j = 0; j < (NUM_POINTS/NUM_GROUPS); j++)//Assumes Num of points will evenly distribute into groups.
			{
				sendPoints(p, points[count]);
				count++;
			}
			list.add(p);
		}

		for(int i = 0; i < NUM_GROUPS; i++)
		{
			closestPoints[i] = new Point();
		}
		count = 0;
		for (Process p : list)
		{
			closestPoints[count] = getPoints(p);
			count++;
		}
		
		for(int i = 0; i <NUM_GROUPS; i++)
		{
			distance = Math.hypot(points[i].GetXValue()-referencePoint.GetXValue(), 
								  points[i].GetYValue()-referencePoint.GetYValue());
			if(closestDistance != -1 && distance < closestDistance)
			{
				closestPoint.SetPoint(points[i].GetXValue(), points[i].GetYValue());
				closestDistance = distance;
			}
		}
		
		System.out.println("The closest point is (" + closestPoint.GetXValue() + ", "
							+ closestPoint.GetYValue() + ")");
	}

	private void sendPoints(Process p, Point point) throws Exception
	{
		System.out.printf("Sending points to process ...%n");
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(p.getOutputStream(), "UTF-8"));
		pw.println(point.GetXValue());
		pw.println(point.GetYValue());
		pw.flush();
	}
	
	private void sendReference(Process p, Point reference) throws Exception
	{
		System.out.printf("Sending reference point to compare ...%n");
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(p.getOutputStream(), "UTF-8"));
		pw.println(reference.GetXValue());
		pw.println(reference.GetYValue());
		pw.flush();
	}

	private Point getPoints(Process p) throws Exception
	{
		p.waitFor();
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
		String line = br.readLine();
		int xValue = Integer.parseInt(line);
		line = br.readLine();
		int yValue = Integer.parseInt(line);
		
		Point closest = new Point(xValue, yValue);
		return closest;
		
	}	
	
	private void createPoints() throws Exception
	{
	    	
		Random rand = new Random();
		int xValue;
		int yValue;
	        
	    for(int i = 0; i < NUM_POINTS; i++)
	    {
	    	xValue = rand.nextInt(MAX_VALUE*2) - MAX_VALUE;
	    	yValue = rand.nextInt(MAX_VALUE*2)- MAX_VALUE;
	    	points[i] = new Point(xValue, yValue);
	    }
	}
	
}