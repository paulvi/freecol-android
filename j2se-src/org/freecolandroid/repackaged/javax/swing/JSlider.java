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

import java.util.Hashtable;

public class JSlider extends JComponent {

	public static final String HORIZONTAL = null;
	private DefaultBoundedRangeModel model;
	
	private int value;

	public void setModel(DefaultBoundedRangeModel model) {
		this.model = model;
	}

	public void setLabelTable(Hashtable<Integer, JComponent> labels) {
		// Does nothing
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void setMajorTickSpacing(int i) {
		// Does nothing
	}

	public void setSnapToTicks(boolean b) {
		// Does nothing
	}

	public int getValue() {
		return value;
	}

	public void setOrientation(String horizontal2) {
		// Does nothing
	}

	public void setPaintLabels(boolean b) {
		// Does nothing
	}

	public void setPaintTicks(boolean b) {
		// Does nothing
	}

	public void setExtent(int i) {
		// Does nothing
	}

}
