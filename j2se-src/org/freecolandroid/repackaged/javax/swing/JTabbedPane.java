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

import java.util.HashMap;
import java.util.Map;

import org.freecolandroid.repackaged.java.awt.Component;


public class JTabbedPane extends JComponent {

	Map<String, Component> tabs = new HashMap<String, Component>();
	
	private int selected;
	
	public JTabbedPane(String top2) {
		// Does nothing
	}

	public static final String TOP = null;

	public void addTab(String title, Component c) {
		tabs.put(title, c);
	}

	public void setSelectedIndex(int i) {
		this.selected = i;
	}

	public int getSelectedIndex() {
		return selected;
	}

	public void addTab(String name, Icon icon, Component c,
			String shortDescription) {
		tabs.put(name, c);
	}

	public int getTabCount() {
		return tabs.size();
	}

}
