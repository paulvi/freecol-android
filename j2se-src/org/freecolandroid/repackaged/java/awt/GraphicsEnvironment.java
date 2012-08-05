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

public class GraphicsEnvironment {

	public static GraphicsEnvironment getLocalGraphicsEnvironment() {
		return new GraphicsEnvironment();
	}

	public GraphicsDevice getDefaultScreenDevice() {
		return new GraphicsDevice();
	}

	public static boolean isHeadless() {
		return false;
	}

	public Rectangle getMaximumWindowBounds() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Broken");
	}

	public Point getCenterPoint() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Broken");
	}

}
