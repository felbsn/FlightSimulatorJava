package com.base.flightSimulator16011075;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class RoutineTableModel extends AbstractTableModel{
	
	public List<FlightRoutine> routineList;
	public String[] COLUMN_NAMES = {
			"inAir"  , "Name" , "Routine"
	};
	
	
	public RoutineTableModel(List<FlightRoutine> list)
	{
		routineList = list;
	}
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return COLUMN_NAMES[column];
	}
	
	public Class<?> getColumnClass(int columnIndex) {

			if(columnIndex == 0)
			return boolean.class;

		

			return String.class;
		
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
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return COLUMN_NAMES.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return routineList.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		FlightRoutine routine = routineList.get(arg0);
		
		switch (arg1) {
		case 0:
			
			return routine.isActive();
			
		case 1:
			return routine.getName();
				
		case 2:
			return routine.getRoutineString();

		}
		
		return null;
	}

}
