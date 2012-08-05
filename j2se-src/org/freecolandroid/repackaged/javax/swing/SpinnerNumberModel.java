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

public class SpinnerNumberModel {
	
	private int value;
	
	private int min;
	
	private int max;
	
	private int stepSize;
	
	public SpinnerNumberModel(int value, int min, int max, int stepSize) {
		this.value = value;
		this.max = max;
		this.min = min;
		this.stepSize = stepSize;
	}

	public void setMaximum(int value) {
		this.max = value;
	}

	public Integer getNumber() {
		return value;
	}

}
