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
		double closestDistance = -1;
		double distance = 0;
		int xValue;
		int yValue;
		xValue = scan.nextInt();
		yValue = scan.nextInt();
		Point referencePoint = new Point(xValue,yValue);
		
		for(int i = 0; i < Master.NUM_POINTS/Master.NUM_GROUPS;i++)
		{
			points[i] = new Point(xValue, yValue);
			xValue = scan.nextInt();
			yValue = scan.nextInt();
		}
		
		for(int i = 0; i <Master.NUM_POINTS/Master.NUM_GROUPS; i++)
		{
			distance = Math.hypot(points[i].GetXValue()-referencePoint.GetXValue(), 
								  points[i].GetYValue()-referencePoint.GetYValue());
			if(closestDistance != -1 && distance < closestDistance)
			{
				closestPoint.SetPoint(points[i].GetXValue(), points[i].GetYValue());
				closestDistance = distance;
			}
		}
		System.out.println(closestPoint.GetXValue());
		System.out.println(closestPoint.GetYValue());
		
	}
}