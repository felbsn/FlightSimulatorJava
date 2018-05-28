package com.base.flightSimulator;

import java.awt.Component;

import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


	public class RoutineRadioBoxCellRenderer extends JRadioButton implements TableCellRenderer {
		
		RoutineRadioBoxCellRenderer() {
	        super();
	        setSelected(false);
	        setHorizontalAlignment(JRadioButton.CENTER);
	    }
		
	    @Override
	    public boolean isDisplayable() { 
	        // This does the trick. It makes sure animation is always performed 
	        return true; 
	    }

		@Override
		public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4,
				int arg5) {

	        this.setSelected((boolean)arg1);
	        return this;
		}

	}

