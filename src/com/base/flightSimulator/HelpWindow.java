package com.base.flightSimulator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class HelpWindow extends JFrame {
	
	JButton okButton = new JButton("OK");
	JLabel  infoLabel = new JLabel("<html> <b> Java uçuþ simulatörü <br>  Kullanýcý kontrol kulesi eklemek "
			+ "için harita üzerinden bir nokta seçerek yeni bir güzergah ekleyebilir.<br>"
			+ "Ekli güzergahlar üzerinde günlük rutin tanýmlayýp silme iþlemleri gerçekleþtirilebilir.<br><br>  fatih elbasan 2018</b></html>\n");
			
	public HelpWindow() {
		
		// TODO Auto-generated constructor stub
	setBounds(0, 0, 300,300);
	
	infoLabel.setBounds(20,20,220 ,200);
	okButton.setBounds(110, 200, 60, 20);
	
	 setLayout(null);
	add(infoLabel);
	
	
	add(okButton);
	
	
	setVisible(true);
	}

}
