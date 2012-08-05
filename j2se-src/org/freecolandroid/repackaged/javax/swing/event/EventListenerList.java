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

package org.freecolandroid.repackaged.javax.swing.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.freecolandroid.repackaged.java.awt.event.ActionListener;


public class EventListenerList {
	
	private Map<Class<ActionListener>, List<ActionListener>> map = new HashMap<Class<ActionListener>, List<ActionListener>>();

	public void add(Class<ActionListener> class1, ActionListener listener) {
		List<ActionListener> list = map.get(class1);
		if (list == null) {
			list = new ArrayList<ActionListener>();
			map.put(class1, list);
		}
		list.add(listener);
	}

	public void remove(Class<ActionListener> class1, ActionListener listener) {
		List<ActionListener> list = map.get(class1);
		if (list != null) {
			list.remove(listener);
		}
	}

	public ActionListener[] getListeners(Class<ActionListener> class1) {
		List<ActionListener> list = map.get(class1);
		if (list != null) {
			ActionListener[] result = new ActionListener[list.size()];
			for (int i = 0; i < list.size(); i++) {
				result[i] = list.get(i);
			}
			return result;
		} else {
			return new ActionListener[] {};
		}
	}

	public int getListenerCount() {
		int count = 0;
		for (List<ActionListener> l : map.values()) {
			count += l.size();
		}
		return count;
	}

}
