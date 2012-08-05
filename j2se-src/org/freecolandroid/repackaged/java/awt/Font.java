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

import java.io.InputStream;


public class Font {

	public static final String TRUETYPE_FONT = null;
	public static final int BOLD = 0;
	public static final int ITALIC = 0;
	public static final int PLAIN = 0;

	public Font deriveFont(int style) {
		return this;
	}

	public Font deriveFont(float size) {
		return this;
	}

	public Font deriveFont(int style, float size) {
		return this;
	}

	public String getFontName() {
		return "dummy-android-font";
	}

	public static Font createFont(String truetypeFont, InputStream openStream) {
		return new Font();
	}

	public static Font decode(String substring) {
		return new Font();
	}

	public int getSize() {
		return 10;
	}

}
