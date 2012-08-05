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

import org.freecolandroid.repackaged.java.awt.geom.AffineTransform;
import org.freecolandroid.repackaged.java.awt.geom.GeneralPath;
import org.freecolandroid.repackaged.java.awt.image.BufferedImage;
import org.freecolandroid.repackaged.java.awt.image.RescaleOp;

import android.graphics.Canvas;


public class Graphics2D extends Graphics {
	
	public Graphics2D(BufferedImage image) {
		canvas.setBitmap(image.getBitmap());
		clipBounds = new Rectangle(0, 0, image.getWidth(), image.getHeight());
	}
	
	public Graphics2D(Canvas canvas) {
		this.canvas = canvas;
		clipBounds = new Rectangle(0, 0, canvas.getWidth(), canvas.getHeight());
	}

	public Graphics2D() {
		clipBounds = new Rectangle();
	}
	
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
		clipBounds.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
	}

	public void draw(GeneralPath path) {
		//FIXME Implement  
	}

	public void drawImage(BufferedImage bi, RescaleOp rop, int x, int y) {
		drawImage(bi, x, y, null);
	}

	public void fill(GeneralPath cross) {
		//FIXME Implement 
	}

	public Composite getComposite() {
		return new Composite();
	}

	public Object getFontRenderContext() {
		return new Object();
	}

	public Stroke getStroke() {
		return new Stroke() {
		};
	}

	public AffineTransform getTransform() {
		return new AffineTransform(canvas.getMatrix());
	}

	public void scale(float sx, float sy) {
		canvas.scale(sx, sy);
	}

	public void setComposite(Composite instance) {
		//FIXME Implement!
	}

	public void setPaint(Paint paint) {
		// TODO Auto-generated method stub
		System.out.println("ERROR!");
		new Exception().printStackTrace();
		throw new UnsupportedOperationException("Broken!");
	}

	public void setRenderingHint(String keyTextAntialiasing, Object textAA) {
		// Does nothing
	}

	public void setStroke(Stroke basicStroke) {
		// Does nothing
	}

	public void setTransform(AffineTransform transform) {
		canvas.setMatrix(transform.getMatrix());
	}
	
}
