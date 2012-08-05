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

package org.freecolandroid.repackaged.java.awt.font;

import org.freecolandroid.repackaged.java.awt.Font;
import org.freecolandroid.repackaged.java.awt.Graphics2D;
import org.freecolandroid.repackaged.java.awt.geom.Rectangle2D;

public class TextLayout {
	
	private String text;

	public TextLayout(String text, Font font, Object fontRenderContext) {
		//FIXME Implement with Android measured text
		this.text = text;
	}

	public Rectangle2D getBounds() {
		return new Rectangle2D(0, 0, 20 * text.length(), 20);
	}

	public int getAscent() {
		return 0;
	}

	public int getDescent() {
		return 0;
	}

	public void draw(Graphics2D g2, float x, float y) {
		g2.drawString(text, (int)x, (int)y);
	}

	public boolean isLeftToRight() {
		return true;
	}

	public int getAdvance() {
		return 0;
	}

	public int getLeading() {
		return 0;
	}

	public float getVisibleAdvance() {
		return 0;
	}

}
