/**
 *	Copyright (C) 2012   The FreeCol-Android Team
 *
 *  This file is part of FreeCol-Android.
 *
 *  FreeCol-Android is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  FreeCol is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with FreeCol-Android.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.freecolandroid.repackaged.javax.swing;

import org.freecolandroid.repackaged.javax.swing.table.DefaultTableColumnModel;
import org.freecolandroid.repackaged.javax.swing.table.JTableHeader;
import org.freecolandroid.repackaged.javax.swing.table.TableCellEditor;
import org.freecolandroid.repackaged.javax.swing.table.TableColumnModel;
import org.freecolandroid.repackaged.javax.swing.table.TableModel;

public class JTable extends JComponent {
	
	private TableColumnModel columnModel = new DefaultTableColumnModel();
	
	private TableModel tableModel;

	private int selectedCol;

	private int selectedRow;

	public static Object AUTO_RESIZE_ALL_COLUMNS;

	public JTable() {
	}

	public JTable(TableModel model) {
		tableModel = model;
	}
	
	public void addNotify() {
		// Does nothing
	}
	
	public TableCellEditor getCellEditor() {
		System.out.println("ERROR!");new Exception().printStackTrace();throw new UnsupportedOperationException("Broken!");
	}

	public TableCellEditor getCellEditor(int i, int j) {
		System.out.println("ERROR!");new Exception().printStackTrace();throw new UnsupportedOperationException("Broken!");
	}

	public TableColumnModel getColumnModel() {
		return columnModel;
	}

	public TableModel getModel() {
		return tableModel;
	}
	
	public int getSelectedColumn() {
		return this.selectedCol;
	}
	
	public int getSelectedRow() {
		return this.selectedRow;
	}
	
	public JTableHeader getTableHeader() {
		System.out.println("ERROR!");new Exception().printStackTrace();throw new UnsupportedOperationException("Broken!");
	}

	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		System.out.println("ERROR!");new Exception().printStackTrace();throw new UnsupportedOperationException("Broken!");
	}
	
	public void setAutoCreateColumnsFromModel(boolean b) {
		// Does nothing
	}
	
	public void setAutoResizeMode(Object aUTO_RESIZE_ALL_COLUMNS2) {
		// Does nothing
	}
	
	public void setCellSelectionEnabled(boolean b) {
		// Does nothing
	}
	
	public void setColumnSelectionAllowed(boolean b) {
		// Does nothing
	}

	public void setModel(TableModel model) {
		this.tableModel = model;
	}

	public void setRowHeight(int h) {
		// Does nothing
	}

	public void setRowSelectionAllowed(boolean b) {
		// Does nothing
	}

	public void setRowSelectionInterval(int i, int j) {
		// Does nothing
	}

	public void setSelectionMode(String singleSelection) {
		// Does nothing
	}

}
