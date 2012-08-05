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

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import org.freecolandroid.repackaged.java.awt.Component;
import org.freecolandroid.repackaged.java.awt.Point;

import android.app.Activity;


public class SwingUtilities {
	
	private static Activity activity;
	
	private static Executor mExecutor = Executors.newCachedThreadPool();
	
	public static void setActivity(Activity activity) {
		SwingUtilities.activity = activity;
	}

	public static void invokeLater(Runnable runnable) {
		mExecutor.execute(runnable);
	}
	
	public static void invokeLaterOnUiThread(Runnable runnable) {
		activity.runOnUiThread(runnable);
	}

	public static boolean isEventDispatchThread() {
		return false;
	}

	public static void invokeAndWait(final Runnable uiTask) throws InterruptedException, InvocationTargetException {
		final Semaphore flag = new Semaphore(0);
		invokeLater(new Runnable() {
			
			@Override
			public void run() {
				uiTask.run();
				flag.release();
			}
		});
		flag.acquire();
	}

	public static void convertPointFromScreen(Point p, JComponent c) {
		// TODO Auto-generated method stub
		System.out.println("ERROR!");new Exception().printStackTrace();throw new UnsupportedOperationException("Broken!");
	}

	public static void replaceUIInputMap(Component okButton,
			int condition, InputMap closeInputMap) {
		// Does nothing
	}

	public static Component getAncestorOfClass(Class<JInternalFrame> class1,
			Component freeColPanel) {
		// TODO Auto-generated method stub
		System.out.println("ERROR!");new Exception().printStackTrace();throw new UnsupportedOperationException("Broken!");
	}

	public static Point convertPoint(Component source, int x, int y,
			Object object) {
		// TODO Auto-generated method stub
		System.out.println("ERROR!");new Exception().printStackTrace();throw new UnsupportedOperationException("Broken!");
	}


}
