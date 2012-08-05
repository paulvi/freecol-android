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

package org.freecolandroid.repackaged.javax.imageio;

import java.io.InputStream;
import java.util.jar.JarOutputStream;

import org.freecolandroid.repackaged.java.awt.image.BufferedImage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class ImageIO {

	public static BufferedImage read(InputStream in) {
		Bitmap bitmap = BitmapFactory.decodeStream(in);
		return new BufferedImage(bitmap);
	}

	public static void write(BufferedImage image, String string,
			JarOutputStream fos) {
		// TODO Auto-generated method stub
		System.out.println("ERROR!");new Exception().printStackTrace();throw new UnsupportedOperationException("Broken!");
	}

}
