/*
 * Author: Dillon Alvord
 * Date: 2/22/17
 * File: Point.java
 */
import java.io.*;
import java.util.*;

public class Point
{
	int xValue;
	int yValue;
    
	public Point(){
		
	}
    public Point(int x, int y)
    {
        xValue = x;
        yValue = y;
    }
    
    public void SetPoint(int x, int y)
    {
    	xValue = x;
        yValue = y;
    }
    
    
    public int GetXValue()
    {
    	return xValue;
    }
    
    public int GetYValue()
    {
    	return yValue;
    }
}