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

package org.freecolandroid.repackaged.java.awt;

import java.util.ArrayList;

public class Container extends Component {
	
	private ArrayList<Component> components = new ArrayList<Component>();
	
	public void doLayout() {
		// Does nothing
	}
	
	public boolean isAncestorOf(Component c) {
		System.out.println("ERROR!");new Exception().printStackTrace();throw new UnsupportedOperationException("Broken!");
	}
 
	
	public void remove(Component comp) {
		components.remove(comp);
	}

	public Component add(Component comp) {
		components.add(comp);
		comp.setParent(this);
		return this;
	}
	
	public Component add(String name,
            Component comp) {
		components.add(comp);
		comp.setParent(this);
		return this;
	}
	
	public void add(Component comp,
            Object constraints) {
		components.add(comp);
		comp.setParent(this);
	}
	
	public int getComponentCount() {
		return components.size();
	}

	public Component getComponent(int n) {
		return components.get(n);
	}
	
	public void remove(int index) {
		components.remove(index);
	}
	
	public void setFocusCycleRoot(boolean focusCycleRoot) {
		// Does nothing
	}
	
	public Component[] getComponents() {
		Component[] result = new Component[components.size()];
		for (int i = 0; i < components.size(); i++) {
			result[i] = components.get(i);
		}
		return result;
	}

}
