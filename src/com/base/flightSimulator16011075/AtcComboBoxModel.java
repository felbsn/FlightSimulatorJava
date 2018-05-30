package com.base.flightSimulator16011075;

import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

public class AtcComboBoxModel<E> implements ComboBoxModel<AirTrafficControlTower> {

	public List<AirTrafficControlTower> towerList;
	public AirTrafficControlTower selectedElement = null;
	public AtcComboBoxModel(List<AirTrafficControlTower> towerList) {
		// TODO Auto-generated method stub
		
		this.towerList = towerList;

	}
	
	
	@Override
	public void addListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AirTrafficControlTower getElementAt(int index) {
		// TODO Auto-generated method stub
		return towerList.get(index);
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return towerList.size();
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public Object getSelectedItem() {
		// TODO Auto-generated method stub
		return selectedElement;
	}

	@Override
	public void setSelectedItem(Object anItem) {
		// TODO Auto-generated method stub
		selectedElement =(AirTrafficControlTower) anItem;
	}

}
