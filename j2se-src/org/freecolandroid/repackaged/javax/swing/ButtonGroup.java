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
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import org.freecolandroid.repackaged.java.awt.event.ActionEvent;


public class ButtonGroup {
	
	List<AbstractButton> buttons = new ArrayList<AbstractButton>();

	public void add(AbstractButton btn) {
		buttons.add(btn);
	}

	public Enumeration<AbstractButton> getElements() {
		final Iterator<AbstractButton> iter = buttons.iterator();
		return new Enumeration<AbstractButton>() {

			@Override
			public boolean hasMoreElements() {
				return iter.hasNext();
			}

			@Override
			public AbstractButton nextElement() {
				return iter.next();
			}
		};
	}

	public void setSelected(ButtonModel model, boolean b) {
		// Does nothing
	}

	public ActionEvent getSelection() {
		return new ActionEvent(this, 0, null, 0);
	}

}
