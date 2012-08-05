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

package org.freecolandroid.repackaged.java.awt.geom;

import org.freecolandroid.repackaged.java.awt.Rectangle;
import org.freecolandroid.repackaged.java.awt.Shape;

public class Rectangle2D implements Shape {
	
	private Rectangle rect = new Rectangle();
	
	public Rectangle2D() {
	}

	public Rectangle2D(int x, int y, int width, int height) {
		rect.setBounds(x, y, width, height);
	}

	public Rectangle getBounds() {
		return rect;
	}

	public float getHeight() {
		return rect.height;
	}

	public float getWidth() {
		return rect.width;
	}

	public float getX() {
		return rect.x;
	}
	
	public void setHeight(int height) {
		rect.height = height;
	}
	
	public void setWidth(int width) {
		rect.width = width;
	}
}
