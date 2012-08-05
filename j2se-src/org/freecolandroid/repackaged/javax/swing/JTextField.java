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

import org.freecolandroid.repackaged.java.awt.event.ActionListener;


public class JTextField extends JComponent {
	
	private String actionCommand;
	
	private String text;
	
	private List<ActionListener> actionListeners = new ArrayList<ActionListener>();

	public JTextField(String string, int cOLUMNS) {
		// Does nothing
	}

	public JTextField(String message) {
		// Does nothing
	}

	public JTextField() {
		// Does nothing
	}

	public String getText() {
		return text;
	}

	public void addActionListener(ActionListener al) {
		actionListeners.add(al);
	}

	public void setActionCommand(String actionCommand) {
		this.actionCommand = actionCommand;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void selectAll() {
		// Does nothing
	}

	public void setColumns(int i) {
		// Does nothing
	}

}
