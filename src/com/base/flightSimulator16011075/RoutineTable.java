package com.base.flightSimulator16011075;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

public class RoutineTable  extends  JTable {
	public RoutineTable(List<FlightRoutine> routineList) {
		
		super();
		
		setRowSelectionAllowed(true);

	if(routineList == null)
	{
		this.routineList = new ArrayList<FlightRoutine>();
	}else
		this.routineList = routineList;
		
	 	model = new RoutineTableModel(this.routineList );
		setModel(model);
 
		
		getColumnModel().getColumn(0).setCellRenderer(new RoutineRadioBoxCellRenderer());
		for (int i = 1; i < model.getColumnCount(); i++) {
			getColumnModel().getColumn(i).setCellRenderer(new RoutineCellRenderer());
		}
		getColumnModel().getColumn(0).setPreferredWidth(20);
		setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

	}
	
	public void setList(List<FlightRoutine> routineList)
	{
		this.routineList = routineList;
	}
	
	public List<FlightRoutine> routineList;
	public RoutineTableModel model;
	public void addRoutine(FlightRoutine f)
	{
		routineList.add(f);
		model.fireTableRowsInserted(0, routineList.size());
	}
	public void removeRoutine(FlightRoutine f)
	{
		routineList.remove(f);
		model.fireTableRowsDeleted(0, routineList.size());
	}
	
	public void removeAt(int index)
	{
		routineList.remove(index);
		model.fireTableRowsDeleted(0, routineList.size());
	}
}
