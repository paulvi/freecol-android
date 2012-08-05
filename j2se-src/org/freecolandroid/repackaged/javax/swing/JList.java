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



import java.util.ArrayList;
import java.util.List;

import org.freecolandroid.repackaged.java.awt.Point;
import org.freecolandroid.repackaged.javax.swing.event.ListSelectionListener;


public class JList extends JComponent {

	private ListCellRenderer cellRenderer;
	private DefaultListModel model;
	private Object selected;
	private int selectedIndex;
	private Object[] data;
	private List<ListSelectionListener> listeners = new ArrayList<ListSelectionListener>();
	
	public JList(DefaultListModel model) {
		// Does nothing
		this.model = model;
	}

	public JList() {
		// Does nothing
	}

	public JList(Object[] array) {
		// Does nothing
	}

	public void setCellRenderer(ListCellRenderer createRenderer) {
		this.cellRenderer = createRenderer;
	}
	
	public void setListData(Object[] listData) {
		this.data = listData;
	}

	public void setFixedCellHeight(int i) {
		// Does nothing
	}

	public void addListSelectionListener(ListSelectionListener endTurnDialog) {
		listeners.add(endTurnDialog);
	}

	public Object getSelectedValue() {
		return selected;
	}

	public void setDragEnabled(boolean b) {
		// Does nothing
	}

	public ListModel getModel() {
		return model;
	}

	public Object[] getSelectedValues() {
		return new Object[] {selected};
	}

	public void setSelectionMode(String singleSelection) {
		// Does nothing
	}

	public int getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(int preferredIndex) {
		this.selectedIndex = preferredIndex;
	}

	public int[] getSelectedIndices() {
		return new int[] {selectedIndex};
	}

	public int locationToIndex(Point point) {
		return 0;
	}

	public int getMaxSelectionIndex() {
		return data.length - 1;
	}

	public void setVisibleRowCount(int i) {
		// Does nothing
	}

	public void setSelectedValue(Object value, boolean b) {
		this.selected = value;
	}

}
