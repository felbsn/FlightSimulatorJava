/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.flightSimulator;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Orchaosis
 */
public class FlightRoutine {
	public static final int BEGIN_OFFSET_HOURS = 1;
	
	
	
	private String name;
	
	private int hours;
	private int minutes;
	
	private int hoursBegin;
	
	private int estimatedFlightDurationMinutes;
	private FlightManager flightManager;
	
	private AirTrafficControlTower towerBase;
	private AirTrafficControlTower towerDest;
	
	public FlightRoutine(String name , FlightManager fl, int hours , int minutes , int estimatedFlightDurationMinutes ,AirTrafficControlTower towerBase , AirTrafficControlTower towerDest) {
		this.name = name;
		this.hours = hours;
		this.minutes = minutes;
		this.towerBase = towerBase;
		this.towerDest = towerDest;
		this.estimatedFlightDurationMinutes = estimatedFlightDurationMinutes;
		this.flightManager  = fl;
		Calendar cal = Calendar.getInstance();
		cal.setTime(Simulator.Date());
		cal.set(Calendar.HOUR_OF_DAY, hours);
		cal.set(Calendar.MINUTE, minutes);
		
		hoursBegin = hours - BEGIN_OFFSET_HOURS;
		hoursBegin %= 24;
		
		m_flight = new Flight(name+ "_fl" , fl , cal.getTime()  , new Date( cal.getTime().getTime() +estimatedFlightDurationMinutes*60*1000 ) , towerBase , towerDest);
		
	}
	
	public FlightRoutine(String name , FlightManager fl, int hours , int minutes ,AirTrafficControlTower towerBase , AirTrafficControlTower towerDest) {
		this.name = name;
		this.hours = hours;
		this.minutes = minutes;
		this.towerBase = towerBase;
		this.towerDest = towerDest;
		this.estimatedFlightDurationMinutes =  towerBase.GetFlightDurationMinutes(towerDest);
		this.flightManager  = fl;
		Calendar cal = Calendar.getInstance();
		cal.setTime(Simulator.Date());
		cal.set(Calendar.HOUR_OF_DAY, hours);
		cal.set(Calendar.MINUTE, minutes);
		
		hoursBegin = hours- BEGIN_OFFSET_HOURS;
		hoursBegin = (hoursBegin+24) % 24;
		
		m_flight = new Flight(name+ "_fl" , fl , cal.getTime()  , new Date( cal.getTime().getTime() +estimatedFlightDurationMinutes*60*1000 ) , towerBase , towerDest);
		
	}
	
	public String getRoutineString()
	{
		
		int landMinutes =  minutes + estimatedFlightDurationMinutes;
		int landHours =  (hours + landMinutes/60)%24;
		landMinutes %= 60;
 
		return String.format("%02d%n:%02d%n - %02d%n:%02d%n ",hours , minutes ,landHours ,landMinutes);//+ ":" +String.format("%02d%n",minutes)+ " / " + String.format("%02d%n",landHours) + ":"+ landMinutes;
	}
	
	public String getName()
	{
		return name;
	}
	
	boolean isActive()
	{
		
		if(m_flight.status == FlightStatus.Inactive || m_flight.status == FlightStatus.Finished) 
		{
			//theese status considered inactive
			return false;
		}else
		{
			return true;
		}
	 
	}
	
	boolean CheckForFlight(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if(cal.get(Calendar.HOUR_OF_DAY) == hoursBegin )
		{

			if(cal.get(Calendar.MINUTE) >= minutes)
			{	
				return true;
			}
		}
		
		return false;
	}
	
	public Flight BeginFlight()
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(Simulator.Date());
		cal.set(Calendar.HOUR_OF_DAY, hours);
		cal.set(Calendar.MINUTE, minutes);
		m_flight = new Flight(name+ "_fl" , flightManager , cal.getTime()  , new Date( cal.getTime().getTime() +estimatedFlightDurationMinutes*60*1000 ) , towerBase , towerDest);
		return this.m_flight;
	}

	private Flight m_flight;

}
