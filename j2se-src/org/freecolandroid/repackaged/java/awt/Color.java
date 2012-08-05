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

public class Color {

	public static final Color BLACK = new Color(android.graphics.Color.BLACK);
	public static final Color WHITE = new Color(android.graphics.Color.WHITE);
	public static final Color GRAY = new Color(android.graphics.Color.GRAY);
	public static final Color GREEN = new Color(android.graphics.Color.GREEN);
	public static final Color RED = new Color(android.graphics.Color.RED);
	public static final Color DARK_GRAY = new Color(android.graphics.Color.DKGRAY);
	public static final Color MAGENTA = new Color(android.graphics.Color.MAGENTA);
	public static final Color BLUE = new Color(android.graphics.Color.BLUE);
	public static final Color YELLOW = new Color(android.graphics.Color.YELLOW);
	
	public static final Color black = BLACK;
	public static final Color white = WHITE;
	public static final Color red = RED;
	public static final Color green = GREEN;
	public static final Color blue = BLUE;
	public static final Color gray = GRAY;
	public static final Color darkGray = DARK_GRAY;
	public static final Color yellow = YELLOW;
	public static final Color magenta = MAGENTA;
	
	public static final Color orange = new Color("#ff9933");
	
	
	
	
	
	private int colorValue;
	
	public Color(Color c) {
		this.colorValue = c.colorValue;
	}

	public Color(int color) {
		colorValue = color;
	}
	
	public Color(String color) {
		// The Android Color class does not support 0x?????? notation
		if (color.startsWith("0x")) {
			color = color.replace("0x", "#");
		}
		colorValue = android.graphics.Color.parseColor(color);
	}
	
	public Color(float r, float g, float b, float a) {
		colorValue = android.graphics.Color.argb((int) (a * 255),
				(int) (r * 255), (int) (g * 255), (int) (b * 255));
	}

	public Color(int r, int g, int b) {
		colorValue = android.graphics.Color.rgb((int) (r * 255),
				(int) (g * 255), (int) (b * 255));
	}

	public int getRGB() {
		return colorValue;
	}

	public float getRed() {
		return android.graphics.Color.red(colorValue);
	}

	public float getGreen() {
		return android.graphics.Color.green(colorValue);
	}

	public float getBlue() {
		return android.graphics.Color.blue(colorValue);
	}

}
