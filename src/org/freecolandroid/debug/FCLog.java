/**
 *  Copyright (C) 2012   The FreeCol-Android Team
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

package org.freecolandroid.debug;

public final class FCLog {
	
	private FCLog() {}
	
	public static void log(Throwable t) {
		System.out.println("********BEGIN EXCEPTION*********");
		System.out.println("Throwable: " + t);
		System.out.println("Message: " + t.getMessage());
		System.out.println("Thread: " + Thread.currentThread().getName());
		t.printStackTrace(System.out);
		System.out.println("**********END EXCEPTION*********");
	}
	
	public static void log(String msg, Throwable t) {
		System.out.println("********BEGIN EXCEPTION*********");
		System.out.println("Throwable: " + t);
		System.out.println("Message1: " + msg);
		System.out.println("Message2: " + t.getMessage());
		System.out.println("Thread: " + Thread.currentThread().getName());
		t.printStackTrace(System.out);
		System.out.println("**********END EXCEPTION*********");
	}
	
	public static void log(String msg) {
		System.out.println(msg);
	}
	
	public static void warn(String msg) {
		System.err.println(msg);
	}

}
