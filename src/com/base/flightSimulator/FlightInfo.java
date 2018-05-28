package com.base.flightSimulator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JTextArea;

public class FlightInfo {
	Flight parent;
	String[] data;

	public FlightInfo()
	{
		data = new String[3];
		data[0] = new String("");
		data[1] = new String("");
		data[2] = new String("");
	}
	
	public void setData(String s0 ,String s1  , String s2)
	{
		data[0] = s0;
		data[1] = s1;
		data[2] = s2;
	}
	
	public  String[] getData()
	{
		return data;
	}
	
}
