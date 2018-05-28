package com.base.flightSimulator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class TowerInputFrame  extends JFrame{
	
	public String name;
	public String info;
	
	JButton btnOk =new JButton("OK");;
	JButton btnCancel = new JButton("Cancel");
	
	JTextArea nameText = new JTextArea("tower name") ;
	JTextArea infoText = new JTextArea("info") ;
	
	
	FlightManager fm;
	WorldCoordinates temp;
	
	public TowerInputFrame( FlightManager fmMngr ,WorldCoordinates tempCoord)
	{
		temp = new WorldCoordinates(tempCoord);
		this.fm = fmMngr;
		setBounds(200 ,200 ,300,300);
		setLayout(null);
		
		btnOk.setBounds(20, 150, 60, 50);
		btnCancel.setBounds(220, 150, 60, 50);
		
		nameText.setBounds(20, 20, 100, 50);
		infoText.setBounds(20, 80, 100, 60);
		
		
		
		
		
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fm.addTower(new AirTrafficControlTower( nameText.getText() , fm ,temp));
				setVisible(false); //you can't see me!
				dispose();
				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); //you can't see me!
				dispose();
				
			}
		});
		add(btnOk);
		add(btnCancel);
		add(nameText);
		add(infoText);
		setVisible(true);
	}
	

}
