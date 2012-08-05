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

import org.freecolandroid.debug.FCLog;

import android.graphics.Bitmap;

public abstract class Image {
	
	public static final String SCALE_REPLICATE = null;
	public static final String SCALE_SMOOTH = null;
	public static final String SCALE_DEFAULT = null;

	public abstract Bitmap getBitmap();
	
	public int getWidth() {
		return getBitmap().getWidth();
	}
	
	public int getHeight() {
		return getBitmap().getHeight();
	}

	public int getWidth(Object object) {
		return getWidth();
	}

	public Image getScaledInstance(int width, int height, String scaleReplicate) {
		FCLog.log("Image scaling not supported yet");
		return this;
	}

	public int getHeight(Object object) {
		return getHeight();
	}

}
