/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.flightSimulator16011075;

import java.util.Random;

/**
 *
 * @author Orchaosis
 */
public class Distribution {
    public static double Geometric(Random rnd)
    {
        float val = rnd.nextFloat();
        return Math.pow(val, 3) ;
        
    }
    
    public static double Geometric(Random rnd , int degree)
    {
    	float val = rnd.nextFloat();
    	return Math.pow(val , degree);
    }
    
    public static boolean GetCancelRatio(Random rnd , double ratio)
    {
    	if(rnd.nextInt(1000) < 1000*ratio)
    		return true;
    	else 
    		return false;
 
    }
    
    public static double Expenential(Random rnd)
    {
        return 1.0-Geometric(rnd);
    }
}
