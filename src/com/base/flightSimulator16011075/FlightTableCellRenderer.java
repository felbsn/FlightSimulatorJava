package com.base.flightSimulator16011075;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class FlightTableCellRenderer extends DefaultTableCellRenderer  {
	
	    public FlightTableCellRenderer() {
			super();
			   setHorizontalAlignment( JLabel.CENTER );
		}
	    
	    
	    @Override
	    public boolean isDisplayable() { 
	        // This does the trick. It makes sure animation is always performed 
	        return true; 
	    }
	    
	    
	    
	  @Override
	  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

	    //Cells are by default rendered as a JLabel.
		  
		  	//return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
		  	
		  
		  if(col != 5)
		  {
			   JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
			    //Get the status for the current row.
			    FlightTableModel tableModel = (FlightTableModel) table.getModel();
			    
			    FlightStatus s = (FlightStatus)tableModel.getValueAt(row, 0);
			    if(s != null)
			    {
			    switch (s) {
			    case Aflight:
			    	l.setBackground(Color.YELLOW);
			    	l.setForeground(Color.black);
			    	break;
			    case Arrived:
			    	l.setBackground(Color.green);
			    	l.setForeground(Color.black);
			    	break;
			    	
			    case Planned:
			    	l.setBackground(Color.gray);
			    	l.setForeground(Color.black);
			    	break;
			    	
			    case WaitingForLanding:
			    	l.setBackground(Color.cyan);
			    	l.setForeground(Color.black);
			    	break;
			    case WaitingForTakeOff:
			    	l.setBackground(Color.lightGray);
			    	l.setForeground(Color.black);
			    	break;
			    case Cancelled:
			    	l.setBackground(Color.RED);
			    	l.setForeground(Color.black);
			    	break;
				default:
					break;
				}
			    }

			  //Return the JLabel which renders the cell.
			  return l;
		  }else
		  {
			  JProgressBar l = (JProgressBar) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
			  return l;
		  }


			  

	  }
}
