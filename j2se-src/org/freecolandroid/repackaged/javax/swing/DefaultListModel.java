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
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;



public class DefaultListModel implements ListModel {
	
	List<Object> items = new ArrayList<Object>();

	public void addElement(Object unitWrapper) {
		items.add(unitWrapper);
	}

	public void removeElement(Object type) {
		items.remove(type);
	}

	public void clear() {
		items.clear();
	}

	public boolean contains(Object buildingType) {
		return items.contains(buildingType);
	}

	public Object[] toArray() {
		return items.toArray();
	}

	public int getSize() {
		return items.size();
	}

	@Override
	public Object getElementAt(int index) {
		return items.get(index);
	}

	public int size() {
		return items.size();
	}

	public void add(int minimumIndex, Object buildableType) {
		items.add(minimumIndex, buildableType);
	}

	public void remove(int i) {
		items.remove(i);
	}

	public Object get(int i) {
		return items.get(i);
	}

	public Object firstElement() {
		return items.get(0);
	}

	public void removeElementAt(int selectedIndex) {
		items.remove(selectedIndex);
	}

	public void setElementAt(Object elementAt, int index) {
		items.remove(index);
		items.add(index, elementAt);
	}

	public Enumeration elements() {
		final Iterator<Object> iter = items.iterator();
		return new Enumeration<Object>() {

			@Override
			public boolean hasMoreElements() {
				return iter.hasNext();
			}

			@Override
			public Object nextElement() {
				return iter.next();
			}
		};
	}

}
