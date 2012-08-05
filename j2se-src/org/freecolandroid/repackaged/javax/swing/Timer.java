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

import java.util.TimerTask;

import org.freecolandroid.repackaged.java.awt.event.ActionListener;

import net.sf.freecol.client.FreeColClient;
import android.app.Activity;


public class Timer {
	
	private FreeColClient client;
	
	private java.util.Timer timer = new java.util.Timer();
	
	private ActionListener listener;
	
	private long delay;
	
	private boolean running = false;
	
	private boolean repeats = false;
	
	public Timer(long delay, ActionListener listener, FreeColClient client) {
		this.client = client;
		this.listener = listener;
		this.delay = delay;
	}

	public boolean isRunning() {
		return running;
	}

	public void start() {
		if (repeats) {
			timer.scheduleAtFixedRate(new Task(), delay, delay);
		} else {
			timer.schedule(new Task(), delay);
		}
		running = true;
	}

	public void stop() {
		timer.cancel();
	}

	public void setRepeats(boolean repeats) {
		this.repeats = repeats;
	}
	
	private class Task extends TimerTask {

		@Override
		public void run() {
			Activity activity = client.getActivity();
			if (activity != null && listener != null) {
				activity.runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						listener.actionPerformed(null);
					}
				});
			}
		}
		
	}

}
