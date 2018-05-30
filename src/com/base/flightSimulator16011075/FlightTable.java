package com.base.flightSimulator16011075;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;

public class FlightTable  extends JTable{

	
	public FlightTable(List<Flight> flightList) {
		super();
	if(flightList == null)
	{
		this.flightList = new ArrayList<Flight>();
	}else
		this.flightList = flightList;
		
		model = new FlightTableModel(this.flightList );
		setModel(model);
		
		for (int i = 0; i < model.getColumnCount()-1; i++) {
			getColumnModel().getColumn(i).setCellRenderer(new FlightTableCellRenderer());
		}
		getColumnModel().getColumn(model.getColumnCount()-1).setCellRenderer(new FlightTableProgressCellRenderer());

	}
	public List<Flight> flightList;
	public FlightTableModel model;
	public void addFlight(Flight f)
	{
		flightList.add(f);
		model.fireTableRowsInserted(0, flightList.size());

	}
	public void removeFlight(Flight f)
	{
		flightList.remove(f);
		model.fireTableRowsDeleted(0, flightList.size());

	}
	
	public void updateTable()
	{
		model.tableChanged(null);
	}
	
	public void refresh()
	{
		model.tableChanged(null);
	}
}

