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

public class BasicStroke implements Stroke {

	public static final int CAP_ROUND = 1;
	public static final int JOIN_ROUND = 2;
	
	private float width;
	
	private int cap = CAP_ROUND;
	
	private int join = JOIN_ROUND;

	public BasicStroke(float width) {
		this.width = width;
	}

	public BasicStroke(float width, int cap, int join) {
		this.width = width;
		this.cap = cap;
		this.join = join;
	}

}
