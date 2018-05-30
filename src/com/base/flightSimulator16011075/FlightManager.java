/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.flightSimulator16011075;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.management.timer.TimerMBean;

/**
 *
 * @author Orchaosis
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
public class FlightManager {
   // private static FlightManager instance = null;
    
    
    private final int departureOffset = 60*60*1000; // 1 hour
    private List<Flight> flightList = new ArrayList<Flight>();
    private List<FlightRoutine> routineList = new ArrayList<FlightRoutine>();
    private List<AirTrafficControlTower> towerList = new ArrayList<AirTrafficControlTower>();
    
    
    RoutineChecker checker;
    FlightTable ftable;
    RoutineTable rtable;
    
    
    Timer timer = new Timer();
   // ScheduledExecutorService executor = Executors.newScheduledThreadPool(0);
    
  
    private class RoutineChecker extends TimerTask
    {
    	public RoutineChecker(List<FlightRoutine> routineList)
    	{
    		this.routineList = routineList;
    		rtable.model.fireTableRowsInserted(0, routineList.size());
    	}
    	
    	private List<FlightRoutine> routineList;
    	
    	public void run()
    	{
        	for (int i = 0; i < routineList.size(); i++) {
        		
        		
        		if(!routineList.get(i).isActive())
        		{
        			
        			//System.out.println(Simulator.Date()); 
        			
            		if(routineList.get(i).CheckForFlight(Simulator.Date()))
            		{
            			
            		 Flight flight = routineList.get(i).BeginFlight();
            		 flight.Start();
            		 rtable.model.fireTableRowsInserted(0, routineList.size());
 
            		 
            		}
        		}

    		}
    	}
    }
    
    public void StopCheck()
    {
    //	executor.shutdownNow();
    }
    public void StartCheckingService()
    {
    	timer.schedule(checker,  0 ,200);
    //	executor.schedule(checker , 200, TimeUnit.MILLISECONDS);
    }
    

    public FlightManager(FlightTable ftable , RoutineTable rtable)
    {
    	this.ftable = ftable;
    	this.rtable = rtable;
    	routineList = rtable.routineList;
    	//rtable.setList(routineList);
    	checker = new RoutineChecker(routineList); 
    }
    
    public void FinishedFlight()
    {
    	rtable.model.fireTableDataChanged();
    }
    
    public int getDepartureOffsetMs()
    {
        return departureOffset;
    }
    public int getDepartureOffsetMinutes()
    {
        return departureOffset/1000/60;
    }
    public int getDepartureOffsetHours()
    {
        return departureOffset/1000/60/60;
    }
    
    public boolean addTower(AirTrafficControlTower atc)
    {
    	return towerList.add(atc);
    }
    public void removeTower(int index)
    {
    	if(index < towerList.size() &&   towerList.size() > 0)
    	  towerList.remove(index);
    }
    
    public List<AirTrafficControlTower> getTowerList()
    {
    	return towerList;
    }
    
    public List<Flight> getFlightList()
    {
    	return flightList;
    }
    public List<FlightRoutine> getRoutines()
    {
    	return routineList;
    }
    
    public void updateFlightTable()
    {
    	ftable.updateUI();
    }
    
    public void addFlight(Flight fl)
    {
    	//flightList.add(fl);
    	ftable.addFlight(fl);
    }
    public void removeFlight(Flight fl)
    {
    	FinishedFlight();
    	ftable.removeFlight(fl);
    	//flightList.remove(fl);
    	
    }
   
    public void addRoutine(FlightRoutine ro)
    {
    	// routineList.add(ro);
    	 rtable.addRoutine(ro);
    //	 rtable.model.fireTableRowsInserted(0, routineList.size());
    	
    }
    
    public void removeRoutine(FlightRoutine ro)
    {
    	rtable.removeRoutine(ro);
    	// routineList.remove(ro);
    	// rtable.model.fireTableRowsInserted(0, routineList.size());
    }
    
    public void SayHello()
    {
        System.out.println(" here this is it !! ");
    }
    
    static Date getDate()
    {
        return Simulator.Date();
    }
    
    
    
}
