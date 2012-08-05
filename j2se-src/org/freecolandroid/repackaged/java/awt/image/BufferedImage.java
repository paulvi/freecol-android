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

package org.freecolandroid.repackaged.java.awt.image;

import org.freecolandroid.repackaged.java.awt.Graphics2D;
import org.freecolandroid.repackaged.java.awt.Image;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;


public class BufferedImage extends Image {

	public static final String TYPE_INT_ARGB = null;
	
	private Bitmap bitmap;
	
	public BufferedImage(Bitmap bitmap) {
		this.bitmap = bitmap.copy(Config.ARGB_8888, true);
	}
	
	public BufferedImage(int width, int height, String typeIntArgb) {
		bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
	}

	public Bitmap getBitmap() {
		return bitmap;
	}
	
	public Graphics2D getGraphics() {
		return new Graphics2D(this);
	}

	public Graphics2D createGraphics() {
		return new Graphics2D(this);
	}

	public BufferedImage getSubimage(int x, int y, int width, int height) {
		return new BufferedImage(Bitmap.createBitmap(bitmap, x, y, width, height, null, false));
	}

	public int getRGB(int biX, int biY) {
		return bitmap.getPixel(biX, biY);
	}

	public void setRGB(int biX, int biY, int color) {
		bitmap.setPixel(biX, biY, color);
	}
	
	@Override
	public int getWidth() {
		return bitmap.getWidth();
	}
	
	@Override
	public int getHeight() {
		return bitmap.getHeight();
	}

}
