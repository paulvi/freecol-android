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
import java.util.Vector;

import org.freecolandroid.repackaged.java.awt.event.ActionListener;
import org.freecolandroid.repackaged.java.awt.event.ItemListener;



public class JComboBox extends JComponent {
	
	private List<ActionListener> actionListeners = new ArrayList<ActionListener>();
	
	private List<ItemListener> itemListeners = new ArrayList<ItemListener>();
	
	private List<Object> items = new ArrayList<Object>();
	
	private DefaultComboBoxModel model;
	
	private ListCellRenderer renderer;

	public JComboBox() {
		// Does nothing
	}

	public JComboBox(DefaultComboBoxModel model) {
		this.model = model;
	}

	public JComboBox(Object[] allstates) {
		items.add(allstates);
	}

	public JComboBox(Vector vector) {
		items.add(vector);

	}

	public void addActionListener(ActionListener actionListener) {
		actionListeners.add(actionListener);
	}

	public void addItem(Object item) {
		items.add(item);
	}

	public void addItemListener(ItemListener itemListener) {
		itemListeners.add(itemListener);
	}

	public ActionListener[] getActionListeners() {
		return (ActionListener[]) actionListeners.toArray();
	}

	public Object getItemAt(int i) {
		return items.get(i);
	}

	public int getItemCount() {
		return items.size();
	}

	public int getSelectedIndex() {
		// Does nothing
		return 0;
	}

	public Object getSelectedItem() {
		return null;
	}

	public void removeActionListener(ActionListener al) {
		actionListeners.remove(al);
	}

	public void removeAllItems() {
		items.clear();
	}

	public void setEditable(boolean b) {
		// Does nothing
	}

	public void setModel(DefaultComboBoxModel model) {
		this.model = model;
	}

	public void setRenderer(ListCellRenderer renderer) {
		this.renderer = renderer;
	}

	public void setSelectedIndex(Integer value) {
		// Does nothing
	}

	public void setSelectedItem(Object colony) {
		// Does nothing
	}

}
