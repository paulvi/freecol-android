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

public class KeyStroke {
	
	private char keyChar;
	
	private int keyCode;
	
	private int modifiers;
	
	private String accelerator;
	
	private boolean onRelease;

	public static KeyStroke getKeyStroke(String accelerator) {
		KeyStroke key = new KeyStroke();
		key.accelerator = accelerator;
		return key;
	}

	public static KeyStroke getKeyStroke(int keyCode, int modifiers) {
		KeyStroke key = new KeyStroke();
		key.keyCode = keyCode;
		key.modifiers = modifiers;
		return key;
	}

	public static KeyStroke getKeyStroke(int keyCode, int modifiers, boolean onRelease) {
		KeyStroke key = new KeyStroke();
		key.keyCode = keyCode;
		key.modifiers = modifiers;
		key.onRelease = onRelease;
		return key;
	}

	public int getModifiers() {
		return modifiers;
	}

	public int getKeyCode() {
		return keyCode;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof KeyStroke) {
			KeyStroke key = (KeyStroke) o;
			return key.keyChar == keyCode;
		} else {
			return false;
		}
	}
}
