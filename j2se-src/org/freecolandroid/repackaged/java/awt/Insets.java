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

/**
 * An Insets object is a representation of the borders of a container. It
 * specifies the space that a container must leave at each of its edges. The
 * space can be a border, a blank space, or a title.
 */
public class Insets {
	
	public int left;
	public int top;
	public int right;
	public int bottom;

	public Insets(int top, int left, int bottom, int right) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}


}
