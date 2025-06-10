package com.sinse.exceltable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

public class DataModel extends AbstractTableModel{
	
	Map<Integer,List> data = new HashMap<>(); // Integer는 행의 번호
	List<String> title = new ArrayList<>();// 컬럽내용

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.size();
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return title.size();
	}
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return title.get(column);
	}

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		List row = data.get(rowIndex);
		return row.get(colIndex);
	}
	
	// ID와 비밀번호는 수정불가
	@Override
	public boolean isCellEditable(int row, int col) {
		// TODO Auto-generated method stub
		return col>1;
	}
	
	// 값 수정하고 저장하기
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {	
	 data.get(rowIndex).set(columnIndex, aValue);
	}
	

}
