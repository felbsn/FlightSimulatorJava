package com.base.flightSimulator;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class RoutineCellRenderer  extends DefaultTableCellRenderer{

	
	
    public RoutineCellRenderer() {
		super();
		   setHorizontalAlignment( JLabel.CENTER );
	}
    
   
    
    
  @Override
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

    //Cells are by default rendered as a JLabel.

		   JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
		   
		  
		    //Get the status for the current row.
		   RoutineTableModel tableModel = (RoutineTableModel) table.getModel();
		    
		   boolean s = (boolean)tableModel.getValueAt(row, 0);
		    if ( s  ) {
		  
		      l.setBackground(Color.GREEN);
		    } else {
		      l.setBackground(Color.gray);
		    }
		    
		    if(isSelected)
		    {
		    	l.setForeground(Color.cyan);
		    }else
		    	l.setForeground(Color.black);

		  //Return the JLabel which renders the cell.
		  return l;

		  

  }

}
