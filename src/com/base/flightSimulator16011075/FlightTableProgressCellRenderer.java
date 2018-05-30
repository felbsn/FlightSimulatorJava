package com.base.flightSimulator16011075;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class FlightTableProgressCellRenderer extends JProgressBar implements TableCellRenderer {
	
	FlightTableProgressCellRenderer() {
        super(0, 100);
        setValue(0);
        //setString("initializing...");
        //setStringPainted(true);
    }
	
    @Override
    public boolean isDisplayable() { 
        // This does the trick. It makes sure animation is always performed 
        return true; 
    }
    
   

	@Override
	public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4,
			int arg5) {
         String text = "";
     //   Color foregroundColor = Color.BLACK;

    //    this.setForeground(foregroundColor);
		if(arg1 != null)
        this.setValue((Integer)arg1);
	
        this.setString(text);

        return this;
	}

}
