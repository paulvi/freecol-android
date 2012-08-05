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

import java.io.IOException;
import java.net.URL;

import org.freecolandroid.repackaged.java.awt.datatransfer.Clipboard;
import org.freecolandroid.repackaged.java.awt.image.BitmapImage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class Toolkit {

	public static Toolkit getDefaultToolkit() {
		return new Toolkit();
	}

	public Image createImage(URL url) throws IOException {
		Bitmap b = BitmapFactory.decodeStream(url.openStream());
		return new BitmapImage(b);
	}
	
	public Cursor createCustomCursor(Image im, Point point, String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public EventQueue getSystemEventQueue() {
		// TODO Auto-generated method stub
		System.out.println("ERROR!");new Exception().printStackTrace();throw new UnsupportedOperationException("Broken!");
	}

	public Clipboard getSystemClipboard() {
		// TODO Auto-generated method stub
		System.out.println("ERROR!");new Exception().printStackTrace();throw new UnsupportedOperationException("Broken!");
	}

	public Dimension getBestCursorSize(int iconWidth, int iconHeight) {
		// TODO Auto-generated method stub
		System.out.println("ERROR!");new Exception().printStackTrace();throw new UnsupportedOperationException("Broken!");
	}

	public int getMenuShortcutKeyMask() {
		// TODO Auto-generated method stub
		System.out.println("ERROR!");new Exception().printStackTrace();throw new UnsupportedOperationException("Broken!");
	}

	public Image getImage(String splashFilename) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Broken");
	}

}
