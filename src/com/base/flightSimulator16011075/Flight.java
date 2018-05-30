/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.flightSimulator16011075;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Orchaosis
 */



public class Flight implements Runnable
{
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    
    public static final int RUN_INTERVAL_MS = 50;
    public static final int TAKEOFF_WARN_MINUTES = 10;
    public static final int LANDING_WARN_MINUTES= 10;
    public static final int DEACTIVATE_DELAY_MINUTES = 60;
    
    
    public String FlightID;
    public String name;
    private FlightManager flightManager;
    private AirTrafficControlTower towerBase;
    private AirTrafficControlTower towerDest;


    
    public Date plannedDepartureDate;
    public Date expectedLandingDate;
    int expectedFlightDuration;

    
    public Date takeoffDate;
    public Date arrivalDate;
    int currentFlightDuration;
    
    private Date departureDate;
    private Date landingWarnDate;
 

   
   
    
    // time that flight become to activate
    Date activationDate;
    // time that flight finished and removed from list
    Date deactivationDate;

    
    public FlightStatus status;
    FlightStatus oldStatus;

    public Flight(String name ,FlightManager fm,Date plannedDepartureDate , Date expectedLandingDate , AirTrafficControlTower towerBase , AirTrafficControlTower towerDest) {
        this.flightManager = fm;
    	this.name = name;
    	this.towerBase = towerBase;
        this.towerDest = towerDest;
      
        this.expectedLandingDate =   new Date(expectedLandingDate.getTime()) ;
        this.plannedDepartureDate =  new Date( plannedDepartureDate.getTime());
        
        expectedFlightDuration = (int)(expectedLandingDate.getTime() - plannedDepartureDate.getTime());
        status = FlightStatus.Inactive;
        oldStatus = status;
    }
    
    public Flight(Flight other)
    {
        this.name = other.name;
    	this.towerBase = other.towerBase;
        this.towerDest = other.towerDest;
        this.expectedLandingDate = other.expectedLandingDate;
        this.plannedDepartureDate = other.plannedDepartureDate;
        
        expectedFlightDuration = (int)(expectedLandingDate.getTime() - plannedDepartureDate.getTime());
        status = FlightStatus.Inactive;
        oldStatus = status;
    }
    
 
    public void Start()
    {
        // become active before 'TAKEOFF_WARN_MINUTES' minutes on a flight
    	
    	currentFlightDuration = 1;
        this.activationDate = new Date(plannedDepartureDate.getTime() -TAKEOFF_WARN_MINUTES*60*1000);
        status = FlightStatus.Planned;
 
        executor.scheduleAtFixedRate(this, 0 ,RUN_INTERVAL_MS ,TimeUnit.MILLISECONDS);
        departureDate = null;
		arrivalDate = null;
		takeoffDate = null;
		
		flightManager.addFlight(this);
    }
    
    public int getProgress()
    {
    	int prog = 0;
    	if(status == FlightStatus.Arrived)
    		prog = 100;
    	else
    	if(takeoffDate != null)
    	{
    		if(arrivalDate != null)
    		{
    			prog =	(int)((Simulator.Date().getTime() - takeoffDate.getTime())*100)/currentFlightDuration;
    		}else
    		{
    			prog = (int)((Simulator.Date().getTime() - takeoffDate.getTime() )*100)/expectedFlightDuration;
    		}
    		
    	
    	}
    	return prog;
    }

    
    private void HandleFlight()
    {
       switch(status)
       {
           case Planned:
               if(activationDate.before(Simulator.Date()))
               {
            	   if(towerBase.CheckCancelStatus())
            	   {
            		   System.out.println( "flight:" + name +  " cancelled" );
            		   status = FlightStatus.Cancelled;
            		   deactivationDate = new Date( Simulator.Date().getTime() + 60*1000 * DEACTIVATE_DELAY_MINUTES);
            	   }else
            	   {
                       departureDate = towerBase.RequestTakeOff(TAKEOFF_WARN_MINUTES);
                       status = FlightStatus.WaitingForTakeOff;
            	   }
   
               }
               break;
           case WaitingForTakeOff:
               if(departureDate.before(Simulator.Date()))
               {
            	   takeoffDate = new Date(Simulator.Date().getTime());
                   status = FlightStatus.Aflight;
                   landingWarnDate = new Date(departureDate.getTime() + expectedFlightDuration);
                  // System.out.println("landingWarnDate " + landingWarnDate);

               }
               break;  
           case Aflight:
           {
               if(landingWarnDate.before(Simulator.Date()))
               {
                   status = FlightStatus.WaitingForLanding;
                   arrivalDate = towerDest.RequestLanding(LANDING_WARN_MINUTES);
                    currentFlightDuration = (int) (arrivalDate.getTime() - takeoffDate.getTime());
                    //System.out.println("arrivalDate " + arrivalDate);
               }
           }
               break;
           case WaitingForLanding:
               if(arrivalDate.before(Simulator.Date()))
               {
            	   
                   //System.out.println("some plane is arrived at destionation ");
                   status = FlightStatus.Arrived;
                   deactivationDate = new Date( Simulator.Date().getTime() + 60*1000 * DEACTIVATE_DELAY_MINUTES);
                   
               }
           break;
           case Arrived:
        	   if(deactivationDate.before(Simulator.Date()))
        	   {
        		   status = FlightStatus.Finished;
        		   executor.shutdown();   
        		   flightManager.removeFlight(this);  
        		   
        	   }
               break;
               
           case Cancelled:
        	   if(deactivationDate.before(Simulator.Date()))
        	   {
        		   status = FlightStatus.Finished;
        		   executor.shutdown();   
        		   flightManager.removeFlight(this);  
        		   
        	   }
        	   break;
           case Finished:  
        	    System.out.println("this message supposed to be not shown in here " );
        	    break;
               

       }
       if(status != oldStatus)
       {
    	   //maybe status change event implementation
           oldStatus = status;
       }
    }
    
    
    @Override
    public String toString() {

    	return  name + " Status:" +status + " D: "+ Simulator.DateFormat( plannedDepartureDate )+ " L: " +Simulator.DateFormat( expectedLandingDate); 
    }
    
    
 
    public String getInfo()
    { 
    	return new String("someIdea");
    } 
    
 
   
    @Override
    public void run() {
        HandleFlight();
    }
    
    
}
