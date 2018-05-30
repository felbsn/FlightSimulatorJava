/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.flightSimulator16011075;

import java.util.Date;

/**
 *
 * @author Orchaosis
 */
public class AirTrafficControlTower {
	private String name;
	private String info;
    private FlightManager fm;
    private WorldCoordinates coordinates;
    private boolean active = true;
    
    public AirTrafficControlTower(String name , FlightManager fm ,WorldCoordinates coordinates )
    {
    	this.fm = fm;
    	this.coordinates = coordinates;
    	this.name = name;
    	info = "empty";
    }
    
    public AirTrafficControlTower(String name , String info, FlightManager fm ,WorldCoordinates coordinates )
    {
    	this.fm = fm;
    	this.coordinates = coordinates;
    	this.name = name;
    	this.info =  info;
    }
    
    
    public AirTrafficControlTower(String name , FlightManager fm ,int x , int y)
    {
    	this.fm = fm;
    	this.coordinates = new WorldCoordinates(x, y);
    	this.name = name;
    }
    
    public void setActive(boolean val)
    {
    	active = val;
    }
    
    public boolean isOnline()
    {
    	return active;
    }

	public AirTrafficControlTower(FlightManager fm ,WorldCoordinates coordinates )
    {
		this.name = "tower";
    	this.fm = fm;
    	this.coordinates = coordinates;
    }
    
    public int GetFlightDurationMinutes(AirTrafficControlTower destination)
    {
    	return coordinates.GetFlightDurationMinutes(destination.coordinates);
    }


     synchronized public Date RequestLanding(int nextMinutes)
    {
    	System.out.println("landing req time:  " + Simulator.Date());
    	int nextMillis =(int) (nextMinutes*1000*60*  Simulator.Rand());
        //System.out.println("javaapp.AirTrafficControlTower.RequestLanding() " + nextMinutes);
        return new Date(Simulator.Date().getTime() +nextMillis) ;
    }
     
     synchronized public boolean CheckCancelStatus()
     {
    	 return Simulator.RandCancel();
     }
    
    
     synchronized public Date RequestTakeOff(int nextMinutes)
    {
    
    	System.out.println("takeoff req time:  " + Simulator.Date());
        int nextMillis =(int)(nextMinutes*1000*60 * Simulator.RandExponential());
        //System.out.println("javaapp.AirTrafficControlTower.RequestTakeOff() " + nextMinutes);
        return new Date(Simulator.Date().getTime() + nextMillis) ;
    }
     
     @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return this.name;
    }
     
     public double getX()
     {
    	 return coordinates.x;
     }
     public double getY()
     {
    	 return coordinates.y;
     }
    
}
