package com.base.flightSimulator16011075;

public class WorldCoordinates {
	public static final int MINUTE_PER_LENGTH_UNIT = 160;
	
	
	
	
	public WorldCoordinates(double x , double y )
	{
		this.x = x;
		this.y = y;
	}
	
	public WorldCoordinates(WorldCoordinates other )
	{
		this.x = other.x;
		this.y = other.y;
	}
	
	
	public int GetFlightDurationMinutes( WorldCoordinates b)
	{
		double diffX =  x - b.x;
		double diffY =  y - b.y;
		return  (int)(MINUTE_PER_LENGTH_UNIT*Math.sqrt(diffX*diffX +diffY*diffY));
	}
	
	public static int GetFlightDurationBetweenMinutes(WorldCoordinates a , WorldCoordinates b)
	{
		double diffX = a.x - b.x;
		double diffY = a.y - b.y;
		return  (int)(MINUTE_PER_LENGTH_UNIT*Math.sqrt(diffX*diffX +diffY*diffY));
	}
	
	
	
	public double x;
	public double y;

}
