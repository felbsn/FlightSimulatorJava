/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.flightSimulator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Orchaosis
 */
public class Simulator {
    
    private static Simulator instance = null;
    Random rnd;
    Date date = new Date();
    private boolean timerEnabled = true;
    
    private int msDelay = 100;
    private Runnable mainTask;
    
    Date timeOld = new Date();
    double timeMultipleer = 1;
    
    DateFormat dateFormat = new SimpleDateFormat("MM dd mm ss");
 
    
    
    private long intervalMs ;

    

    private Simulator()
    {
        rnd = new Random();
        
    }
    public static Simulator getInstance()
    {
        if(instance == null)
        {
            instance = new Simulator();
        }
        return instance;
    }
    
    public void Simulate() throws InterruptedException
    {
       Date timeNow = new Date();
       long millis =   timeNow.getTime() - timeOld.getTime();
       if(instance.timerEnabled)
       {
    	   
      
       intervalMs = (long)(millis * timeMultipleer);
       date.setTime(date.getTime() + intervalMs);
       
       if(mainTask != null)
       {
           mainTask.run();
       }
       
       }
       TimeUnit.MILLISECONDS.sleep(msDelay);
       timeOld = timeNow;
    }
    
    public static void setDate(Date date)
    {
        instance.date = date;
    }

    public static void setMultipleer(double ratio)
    {
        instance.timeMultipleer = ratio;
    }
    
    public static void setTimerEnable(boolean val)
    {
    	instance.timerEnabled = val;
    }
    
    public static double Rand()
    {
        return RandGeometric();
    }
    
    
    public static double RandGeometric()
    {
        return Distribution.Geometric(instance.rnd);
    }
    
    public static double RandExponential()
    {
    	return Distribution.Expenential(instance.rnd);
    }
    
    
    public static Date Date()
    {
        return instance.date;
    }
    
    
    public static String DateFormat(Date date)
    {
    	return instance.dateFormat.format(date);
    }


    public int getMsDelay() {
        return msDelay;
    }

    public void setMsDelay(int msDelay) {
        this.msDelay = msDelay;
    }

    public Runnable getMainTask() {
        return mainTask;
    }

    public void setMainTask(Runnable mainTask) {
        this.mainTask = mainTask;
    }

    public long getIntervalMs() {
        return intervalMs;
    }

    public void setIntervalMs(long intervalMs) {
        this.intervalMs = intervalMs;
    }
    
    
    
    
}
