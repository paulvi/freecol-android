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

package org.freecolandroid.repackaged.java.awt.geom;


public abstract class Point2D {
	
	public abstract double getX();
	
	public abstract double getY();

	public static class Float extends Point2D {

		public Float(float x, float y) {
			this.x = x;
			this.y = y;
		}

		public float x;
		
		public float y;

		@Override
		public double getX() {
			return x;
		}

		@Override
		public double getY() {
			return y;
		}
		
	}
	
	public static class Double extends Point2D {

		public double x;
		
		public double y;
		
		public @Override
		double getX() {
			return x;
		}

		@Override
		public double getY() {
			return y;
		}
		
	}
	
	public double distance(double PX,
            double PY) {
		double dx = Math.abs(getX() - PX);
		double dy = Math.abs(getY() - PY);
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	public double distance(Point2D pt) {
		return distance(pt.getX(), pt.getY());
	}

}
