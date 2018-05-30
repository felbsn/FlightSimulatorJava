package com.base.flightSimulator16011075;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JProgressBar;
import javax.swing.event.TableModelEvent;
import javax.swing.plaf.ProgressBarUI;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class FlightTableModel  extends AbstractTableModel {
	
	   public final static String[] COLUMN_NAMES = { "Status", "Name","TakeOff" , "Arrival" ,"Routine","Progress" };
	   
	   
	  private List<Flight> flightList = new ArrayList<Flight>();
	  SimpleDateFormat routineFormat = new SimpleDateFormat("HH mm");
	  SimpleDateFormat flightDateFormat =  new SimpleDateFormat("HH mm ss");
	  public JProgressBar prog = new JProgressBar(0, 100);
	  
	  public FlightTableModel( List<Flight> flightList) {
		 this.flightList = flightList;
		 System.out.println(routineFormat.format(new Date()));
	}
	  
	  @Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return COLUMN_NAMES[column];
	}
	  
	    @Override
	    public void fireTableDataChanged() {
	    	
	        super.fireTableDataChanged();
	    }
	    
	    @Override
	    public void fireTableRowsInserted(int firstRow, int lastRow) {
	    	// TODO Auto-generated method stub
	    	super.fireTableRowsInserted(firstRow, lastRow);
	    }
	    
	    @Override
	    public void fireTableCellUpdated(int row, int column) {
	    	// TODO Auto-generated method stub
	    	super.fireTableCellUpdated(row, column);
	    }
	    @Override
	    public void fireTableRowsUpdated(int firstRow, int lastRow) {
	    	// TODO Auto-generated method stub
	    	super.fireTableRowsUpdated(firstRow, lastRow);
	    	
	    }
	    @Override
	    public void fireTableRowsDeleted(int firstRow, int lastRow) {
	    	// TODO Auto-generated method stub
	    	super.fireTableRowsDeleted(firstRow, lastRow);
	    	
	    	
	    }
	    
	    public void tableChanged(TableModelEvent e) {
	        fireTableChanged(e);
	        
	    }
	    
	    
	    
	  
	  
	   @Override
	   public int getColumnCount() {
	      return COLUMN_NAMES.length;
	     
	   }

	   @Override
	   public Class<?> getColumnClass(int columnIndex) {
	      if (columnIndex == 0) {
	         return String.class;
	      }
	      return super.getColumnClass(columnIndex);
	   }
	   
	   public void addRow(Flight flight) {
		// TODO Auto-generated method stub
		   flightList.add(flight);
	}

	   @Override
		public int getRowCount() {
		// TODO Auto-generated method stub
		return flightList.size();
	   }

	   @Override
	   public Object getValueAt(int rowIndex, int colIndex) {
		   
		      if (rowIndex < 0 || rowIndex >= getRowCount()) {
		    	  return null;
		    	 
		       }
		       if (colIndex < 0 || colIndex >= getColumnCount()) {
		    	   return null;
		       } 
		       
		      
		      
		       Flight flight = flightList.get(rowIndex);
		       fireTableCellUpdated(rowIndex, colIndex);
		       		switch (colIndex) {
		       		case 0:
		       			return flight.status;

		       		case 1:
		       			return flight.name;

		       		case 2:
		       			if(flight.takeoffDate != null)
		       		    return flightDateFormat.format(flight.takeoffDate) ;
		       			break;
		       		 
		       		case 3:
		       			if(flight.arrivalDate != null)
		       		 	return  flightDateFormat.format(flight.arrivalDate);
		       			break;
		       		 
		       		case 4:
		       		    return  routineFormat.format(flight.plannedDepartureDate) + " - "+  routineFormat.format(flight.expectedLandingDate);
		       		 case 5:
		       			 return  flight.getProgress();
		       		}
		       		
		       return new String("?");
		   	   }
	}
