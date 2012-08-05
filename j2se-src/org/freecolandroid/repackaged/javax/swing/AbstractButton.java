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

import org.freecolandroid.repackaged.java.awt.Insets;
import org.freecolandroid.repackaged.java.awt.event.ActionListener;
import org.freecolandroid.repackaged.java.awt.event.ItemListener;
import org.freecolandroid.repackaged.javax.swing.event.ChangeListener;


public class AbstractButton extends JComponent {

	private List<ActionListener> actionListeners = new ArrayList<ActionListener>();
	
	private List<ChangeListener> changeListeners = new ArrayList<ChangeListener>();
	
	private List<ItemListener> itemListeners = new ArrayList<ItemListener>();
	
	protected String actionCommand;
	
	private Icon icon;

	protected String text;
	
	protected Action action;

	private boolean selected;
	
	private ButtonModel model;

	public void addActionListener(ActionListener l) {
		actionListeners.add(l);
	}

	public void addChangeListener(ChangeListener l) {
		changeListeners.add(l);
	}

	public void addItemListener(ItemListener l) {
		itemListeners.add(l);
	}

	protected void configurePropertiesFromAction(Action a) {
		// Does nothing
	}

	public Action getAction() {
		return action;
	}

	public Icon getIcon() {
		return icon;
	}

	public ItemListener[] getItemListeners() {
		return (ItemListener[]) itemListeners.toArray();
	}

	public ButtonModel getModel() {
		return model;
	}

	public boolean isContentAreaFilled() {
		return true;
	}

	public boolean isRolloverEnabled() {
		return false;
	}

	public boolean isSelected() {
		return selected;
	}

	public void removeActionListener(ActionListener l) {
		actionListeners.remove(l);
	}

	public void removeItemListener(ItemListener l) {
		itemListeners.remove(l);
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public void setActionCommand(String command) {
		this.actionCommand = command;
	}

	public void setBorderPainted(boolean b) {
		// Does nothing
	}

	public void setContentAreaFilled(boolean b) {
		// Does nothing
	}

	public void setDisabledIcon(Object icon) {
		// Does nothing
	}

	public void setFocusPainted(boolean e) {
		// Does nothing
	}

	public void setHorizontalAlignment(int leading) {
		// Does nothing
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public void setMargin(Insets insets) {
		// Does nothing
	}

	public void setMnemonic(char c) {
		// Does nothing
	}

	public void setMnemonic(int intValue) {
		// Does nothing
	}

	public void setPressedIcon(Icon i) {
		// Does nothing
	}

	public void setRolloverEnabled(boolean e) {
		// Does nothing
	}

	public void setRolloverIcon(Icon i) {
		// Does nothing
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public void setText(String text) {
		this.text = text;
	}
}
