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



import org.freecolandroid.repackaged.java.awt.Color;
import org.freecolandroid.repackaged.java.awt.Component;
import org.freecolandroid.repackaged.java.awt.Graphics;
import org.freecolandroid.repackaged.java.awt.Insets;
import org.freecolandroid.repackaged.javax.swing.border.Border;
import org.freecolandroid.repackaged.javax.swing.border.TitledBorder;


public class BorderFactory {

	public static Border createEmptyBorder() {
		return new DummyBorder();
	}

	public static TitledBorder createTitledBorder(Object createEmptyBorder,
			String message) {
		return new TitledDummyBorder();
	}

	public static Border createEmptyBorder(int i, int j, int k, int l) {
		return new DummyBorder();
	}

	public static Border createLineBorder(Color primary1) {
		return new DummyBorder();
	}

	public static Border createBevelBorder(String lowered) {
		return new DummyBorder();
	}

	public static Border createEtchedBorder() {
		return new DummyBorder();
	}

	public static Border createMatteBorder(int i, int j, int k, int l,
			ImageIcon background) {
		return new DummyBorder();
	}

	public static Border createCompoundBorder(Border createMatteBorder,
			Border createLineBorder) {
		return new DummyBorder();
	}

	public static Border createTitledBorder(String title) {
		return new DummyBorder();
	}

	public static Border createMatteBorder(int i, int j, int k, int l,
			Color borderColor) {
		return new DummyBorder();
	}

	public static Border createLineBorder(Color gray, int i) {
		return new DummyBorder();
	}
	
	private static class DummyBorder implements Border {

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y,
				int width, int height) {
			// Does nothing
		}

		@Override
		public Insets getBorderInsets(Component c) {
			return null;
		}
		
	}
	
	private static class TitledDummyBorder extends TitledBorder implements Border {

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y,
				int width, int height) {
			// Does nothing
		}

		@Override
		public Insets getBorderInsets(Component c) {
			return null;
		}
		
	}

}
