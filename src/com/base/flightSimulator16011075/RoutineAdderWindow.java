package com.base.flightSimulator16011075;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RoutineAdderWindow extends JFrame {
	
	
	JSpinner jhours = new JSpinner();
	JSpinner jminutes= new JSpinner();
	JButton okButton = new JButton("OK");
	
	JTextArea routineText = new JTextArea("Routine");
	
	JComboBox<AirTrafficControlTower> cb0 = new JComboBox<>();
	JComboBox<AirTrafficControlTower> cb1 = new JComboBox<>();
	
	
	
	JLabel hourLbl = new JLabel("hours");
	JLabel minuteLbl = new JLabel("minutes");
	RoutineTable table;
	FlightManager fm;
	public RoutineAdderWindow(RoutineTable table  , FlightManager flin)
	{
		super();
		fm = flin;
		this.table = table;
		
		setBounds(100, 100, 300, 300);
		setLayout(null);;
		
		hourLbl.setBounds(20, 10, 60, 20);
		jhours.setBounds(20, 30, 60, 20);
		jhours.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if((int)jhours.getValue() >= 24 )
				{
					jhours.setValue((int)jhours.getValue() % 24);
				}else
					if((int)jhours.getValue() < 0)
					{
						jhours.setValue(23);
					}
				
			}
		});
		add(jhours);
		add(hourLbl);
		
		minuteLbl.setBounds(120, 10, 60, 20);
		jminutes.setBounds(120, 30, 60, 20);
		jminutes.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if((int)jminutes.getValue() >= 60 )
				{
					jminutes.setValue((int)jminutes.getValue() % 60);
				}else
				if((int)jminutes.getValue() < 0)
				{
					jminutes.setValue(59);
				}
				
			}
		});
		add(minuteLbl);
		add(jminutes);
		
		
		routineText.setBounds(20, 60, 180, 30);
		add(routineText);
		
		okButton.setBounds(200, 220, 80, 30);
		add(okButton);
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 addRoutine();
					setVisible(false); //you can't see me!
					dispose();
			}
		});
		okButton.setEnabled(false);
		
		cb0.setModel(new AtcComboBoxModel<>(fm.getTowerList()));
		cb1.setModel(new AtcComboBoxModel<>(fm.getTowerList()));
		
		cb0.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(cb1.getSelectedIndex() == cb0.getSelectedIndex() && (cb0.getSelectedIndex() != -1 && cb1.getSelectedIndex() != -1))
				{

					okButton.setEnabled(false);
					
				}else
					okButton.setEnabled(true);
				
			}
		});
		
		cb0.setBounds(20, 120, 80, 30);
		cb1.setBounds(120, 120, 80, 30);
		add(cb0);
		add(cb1);
		setVisible(true);
		
		
	}
	
	public void addRoutine()
	{
		

    table.addRoutine(new FlightRoutine(routineText.getText(), 
		fm, (int)jhours.getValue(), 
		(int)jminutes.getValue(),
		(AirTrafficControlTower) cb0.getSelectedItem(), 
		(AirTrafficControlTower) cb1.getSelectedItem()));
	}
	
	
	
	
	

}
