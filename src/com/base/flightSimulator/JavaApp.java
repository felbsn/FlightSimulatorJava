/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.flightSimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListDataListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.JTextComponent;



/**
 *
 * @author Orchaosis
 */
public class JavaApp {

 
  public static void main(String[] args) throws InterruptedException {

	  
      JFrame frame = new JFrame();
      frame.setLayout(null);
      frame.setSize(1280 ,410);
      
        
      Simulator simulator = Simulator.getInstance();

      
    
      Calendar cal = Calendar.getInstance();
      cal.set(Calendar.HOUR_OF_DAY, 8);
      cal.set(Calendar .MINUTE , 0); 
      
      Simulator.setDate(cal.getTime());
      

 
     FlightTable flightTable = new FlightTable(null) ;
     RoutineTable  routineTable= new  RoutineTable(null);
     FlightManager flightManager =new FlightManager(flightTable , routineTable);
     
     
     
    
    AirTrafficControlTower atc0 = new AirTrafficControlTower("merkez" , flightManager, new WorldCoordinates(0.0, 0.0));
    AirTrafficControlTower atc1 = new AirTrafficControlTower("sagorta" , flightManager, new WorldCoordinates(-1, 0.0));
    AirTrafficControlTower atc2 = new AirTrafficControlTower("almanya" , flightManager, new WorldCoordinates(0.1, -0.4));
    AirTrafficControlTower atc3 = new AirTrafficControlTower("türkiye" , flightManager, new WorldCoordinates(0.2, -0.3));
    AirTrafficControlTower atc4 = new AirTrafficControlTower("amerika" , flightManager, new WorldCoordinates(-0.8, -0.1));
    AirTrafficControlTower atc5 = new AirTrafficControlTower("çin" , flightManager, new WorldCoordinates(0.8, 0.3));
    AirTrafficControlTower atc6 = new AirTrafficControlTower("kanada" , flightManager, new WorldCoordinates(-0.6, -0.8));
    
     FlightRoutine ro0 = new FlightRoutine("firma0" , flightManager , 9 , 20 , atc0 , atc1);
     FlightRoutine ro1 = new FlightRoutine("firma1" , flightManager , 12 , 45 , atc0 , atc2);
     FlightRoutine ro2 = new FlightRoutine("firma2" , flightManager , 16 , 00 , atc4 , atc5);
     FlightRoutine ro3 = new FlightRoutine("firma3" , flightManager , 3 , 00 , atc2 , atc1);
     FlightRoutine ro4 = new FlightRoutine("firma4" , flightManager , 00 , 00 , atc2 , atc3);
     FlightRoutine ro5 = new FlightRoutine("firma5" , flightManager , 22 , 45 , atc5 , atc1);
    		 
     
     
     flightManager.addTower(atc0);
     flightManager.addTower(atc1);
     flightManager.addTower(atc2);
     flightManager.addTower(atc3);
     flightManager.addTower(atc4);
     flightManager.addTower(atc5);
     flightManager.addTower(atc6);

     flightManager.addRoutine(ro0);
     flightManager.addRoutine(ro1);
     flightManager.addRoutine(ro2);
     flightManager.addRoutine(ro3);
     flightManager.addRoutine(ro4);
     flightManager.addRoutine(ro5);
     
    // flightManager
     
     flightManager.StartCheckingService();
     

      // define main fight panel
     JPanel panelRoutines = new JPanel();
     
     
     
     
     panelRoutines.setBounds(620 , 90 , 360 ,240);
     panelRoutines.setLayout(new BorderLayout());
     panelRoutines.setBackground(Color.LIGHT_GRAY);
     
     
     
     JScrollPane scrollPaneRoutines = new JScrollPane(routineTable);
     scrollPaneRoutines.setBackground(Color.LIGHT_GRAY);
     panelRoutines.add(scrollPaneRoutines);
     
     
     JLabel bannerLabel = new JLabel("Java Flight Simulator");
     bannerLabel.setFont(new Font("Serif", Font.BOLD, 23));
     bannerLabel.setBounds(30 , 15 , 300 ,30);
     frame.add(bannerLabel);
     
     
      JPanel panelFlights = new JPanel();  
      panelFlights.setBounds(10 , 60 , 600 ,300);
      panelFlights.setLayout(new BorderLayout());
      panelFlights.setBackground(Color.white);
      
      
      // define fight table
      JScrollPane scrollPaneFlights = new JScrollPane(flightTable);
      panelFlights.add(scrollPaneFlights);
      scrollPaneFlights.setBackground(Color.WHITE);
      
      JComboBox<AirTrafficControlTower> jtower = new JComboBox<AirTrafficControlTower>();

      jtower.setModel(new AtcComboBoxModel<>(flightManager.getTowerList()) ); 


      

      jtower.setBounds(990 , 90 , 100 ,30);
      frame.add(jtower);
      
      JButton worldPane = new JButton();
      int mapX = 990, mapY = 130;
      int mapWidth = 260 , mapHeight = 230;
      
      worldPane.setBounds(mapX,mapY ,mapWidth,mapHeight);
      worldPane.setBackground(Color.cyan);
      worldPane.setOpaque(false);
      frame.add(worldPane);
      
      
      JLabel xbox = new JLabel( "X");
      frame.add(xbox);
      xbox.setBounds(0 , 0, 10 , 10);
      
      
      JButton delTower = new JButton("Del");
      delTower.setBounds(1090 , 90 , 60 ,30);
      frame.add(delTower);
     
      
      JButton newTower = new JButton("Add");
      newTower.setBounds(1150 , 90 , 60 ,30);
      frame.add(newTower);
      newTower.setEnabled(false);

      delTower.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		flightManager.removeTower(jtower.getSelectedIndex());
		if(jtower.getItemCount() >= 1)
		{
			
			 jtower.setSelectedIndex(0);
			
		}else
		{
			delTower.setEnabled(false);
		}

		}
	});
      
      
      jtower.addItemListener(new ItemListener() {
  		
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
		
			
			 AirTrafficControlTower obj = (AirTrafficControlTower)jtower.getSelectedItem();
			 xbox.setBounds(mapX + (int)(mapWidth/2 * obj.getX() +mapWidth/2 ),
					 mapY +(int)(mapHeight/2 * obj.getY() +mapHeight/2),
					 80 ,10);
			 
			 xbox.setText( "* "+ obj.toString());
			 
 
			 xbox.updateUI();
			 newTower.setEnabled(false);
			 delTower.setEnabled(true);
		}
	});
      
      WorldCoordinates tempCoord = new WorldCoordinates(0, 0);

      worldPane.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			 xbox.setText("X");
			 xbox.grabFocus();
			 
			 tempCoord.x = (worldPane.getMousePosition().getX()-mapWidth/2)/(mapWidth/2) ;
			 tempCoord.y = ( worldPane.getMousePosition().getY() -mapHeight/2)/(mapHeight/2);
			
			
			

			  xbox.setBounds((int)(mapX  + worldPane.getMousePosition().getX() ),(int)(mapY  + worldPane.getMousePosition().getY() ),10 , 10);

			  xbox.updateUI();
			  newTower.setEnabled(true);
			  delTower.setEnabled(false);
			
		}
	});
      
    newTower.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			newTower.setEnabled(false);
			TowerInputFrame inp =new TowerInputFrame(flightManager,tempCoord);
			jtower.updateUI();
			
			
		}
	});
    
    JButton helpBtn = new JButton("?");
    helpBtn.setBounds(1210 , 90 , 42 ,30);
    helpBtn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			new HelpWindow();
			
		}
	});
    frame.add(helpBtn);
     

      frame.add(panelFlights);
      frame.add(panelRoutines);
     
     
      JLabel timeLabel = new JLabel("00:00");
      timeLabel.setBounds(730,15, 200, 60);
      frame.add(timeLabel);
      
      JLabel multipleerLabel = new JLabel("x1");
      multipleerLabel.setBounds(655, 58, 60, 30);
      frame.add(multipleerLabel);
      
      JSlider slider = new JSlider();
      slider.setBounds(700, 60, 200, 30);
      frame.add(slider);
      slider.setValue(1);
      slider.addChangeListener(new ChangeListener() {
          @Override
          public void stateChanged(ChangeEvent e) {
            
        	  			 double ml = slider.getValue()*slider.getValue()/1.0f;
                       Simulator.setMultipleer(ml);
                       multipleerLabel.setText("x"+(int)ml);
          }
          
      });
      
      
      JToggleButton stopButton = new JToggleButton("Stop");
      stopButton.setBounds(910 , 60 , 70 , 30);
      stopButton.addChangeListener(new ChangeListener() {
		
		@Override
		public void stateChanged(ChangeEvent e) {

				Simulator.setTimerEnable(!stopButton.isSelected());
				if(!stopButton.isSelected())
				{
					stopButton.setText("Stop");
				}else
				{
					stopButton.setText("Start");
				}
			
		}
	});
      frame.add(stopButton);
      
      
      JButton addRoutineButton = new JButton("Add Rotine");
      addRoutineButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			new RoutineAdderWindow(routineTable,flightManager);
			
		}
	});
      addRoutineButton.setBounds(801, 330 ,180, 30);
      
      frame.add(addRoutineButton);
      
      JButton removeRoutineButton = new JButton("Remove Rotine");
      removeRoutineButton.setBounds(619, 330 ,180, 30);
      removeRoutineButton.addActionListener(new ActionListener() {
		
		@Override
	  public void actionPerformed(ActionEvent e) {
			if(routineTable.getSelectedRowCount() >= 1)
			{
				routineTable.removeAt(routineTable.getSelectedRow());
			}
			
			
			}});
      
    
      frame.add(removeRoutineButton);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);

    
      DateFormat dateFormat = new SimpleDateFormat("HH mm ss");


      
      while(frame.isEnabled())
      {
    	 
          simulator.Simulate();
          timeLabel.setText("Simulator Time : "+dateFormat.format(Simulator.Date()));

      }
      

	
   
      

    }
	  
    
}
