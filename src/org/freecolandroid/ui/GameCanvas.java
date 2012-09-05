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
 *  FreeCol-Android is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with FreeCol-Android.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.freecolandroid.ui;

import static org.freecolandroid.Constants.LOG_TAG;
import net.sf.freecol.client.FreeColClient;

import org.freecolandroid.repackaged.java.awt.Graphics2D;

import android.content.Context;
import android.graphics.Canvas;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;


public class GameCanvas extends SurfaceView implements Callback {
	
	private PaintThread mPaintThread = new PaintThread();
	
	private SurfaceHolder mHolder;
	
	private FreeColClient mClient;

	public GameCanvas(Context context) {
		super(context);
		
		mHolder = getHolder();
		mHolder.addCallback(this);
	}
	
	public GameCanvas(Context context, AttributeSet attrs) {
		super(context, attrs);

		mHolder = getHolder();
		mHolder.addCallback(this);
	}
	
	public void init(FreeColClient client) {
		mClient = client;
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		super.onWindowFocusChanged(hasWindowFocus);
		if (hasWindowFocus) {
			mPaintThread.setPaused(false);
		} else {
			mPaintThread.setPaused(true);
		}
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		if (mClient != null && canvas != null) {
			Graphics2D g2d = new Graphics2D(canvas);
			mClient.getGUI().getCanvas().paintComponent(g2d);
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mPaintThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		mPaintThread.stopPainting();
		while (retry) {
			try {
				mPaintThread.join();
				retry = false;
			} catch (InterruptedException e) {}
		}
	}

	private class PaintThread extends Thread {
		
		private boolean mRunning = true;
		
		private boolean mPaused = false;
		
		public void stopPainting() {
			mRunning = false;
		}
		
		public void setPaused(boolean paused) {
			mPaused = paused;
		}
		
		@Override
		public void run() {
			Log.d(LOG_TAG, "GameCanvas.PaintThread.run() - start");
			System.out.println();
			Canvas canvas = null;
			while (mRunning) {
				if (!mPaused) {
					try {
						canvas = mHolder.lockCanvas();
						onDraw(canvas);
					} catch (Exception e) {
	//					mRunning = false;
						Log.w(LOG_TAG, "Exception while drawing", e);
					} finally {
						if (canvas != null) {
							mHolder.unlockCanvasAndPost(canvas);
						}
					}
				}
			}
			SystemClock.sleep(50);
			Log.d(LOG_TAG, "GameCanvas.PaintThread.run() - stop");
		}
		
	}

}
