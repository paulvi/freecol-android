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

import org.freecolandroid.repackaged.java.awt.geom.Rectangle2D;

import android.graphics.Paint;


public class FontMetrics {

	private Paint paint;

	public FontMetrics(Paint paint) {
		this.paint = paint;
	}

	public Rectangle2D getStringBounds(String text, Graphics g) {
		Rectangle2D bounds = new Rectangle2D();
		if (text != null) {
			bounds.setHeight(20);
			if (paint != null) {
				bounds.setWidth(stringWidth(text));
			}
		}
		return bounds;
	}

	public int stringWidth(String text) {
		int height = 0;
		if (text != null) {
			if (paint != null) {
				return (int) paint.measureText(text);
			} else {
				return 15 * text.length();
			}
		}
		return height;
	}
	
	public int getMaxAscent() {
		if (paint != null) {
			return Math.abs(paint.getFontMetricsInt().top);
		} else {
			return 10;
		}
	}

	public int getMaxDescent() {
		if (paint != null) {
			return Math.abs(paint.getFontMetricsInt().bottom);
		} else {
			return 10;
		}
	}

	public int getAscent() {
		if (paint != null) {
			return Math.abs(paint.getFontMetricsInt().ascent);
		} else {
			return 0;
		}
	}

	public int getDescent() {
		if (paint != null) {
			return Math.abs(paint.getFontMetricsInt().descent);
		} else {
			return 0;
		}
	}

}
