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

package org.freecolandroid;

import java.io.File;

import org.freecolandroid.repackaged.java.awt.GraphicsConfiguration;
import org.freecolandroid.repackaged.javax.swing.SwingUtilities;
import org.freecolandroid.ui.game.GameFragment;
import org.freecolandroid.ui.menu.GameMenuFragment;

import net.sf.freecol.FreeCol;
import android.app.Activity;
import android.app.Fragment;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;


public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Launch the game
        new Thread(new Runnable() {
			
			@Override
			public void run() {
				FreeCol.main(new String[] {}, MainActivity.this);
			}
		}).start();
        
        // Setup stub classes
        SwingUtilities.setActivity(this);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        GraphicsConfiguration.setBounds(width, height);
        
        // Setup directories
        FreeCol.setSaveDirectory(new File("/sdcard/freecol/save/"));
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if (keyCode == KeyEvent.KEYCODE_BACK) {
			Fragment currentFrag = getFragmentManager().findFragmentById(
					R.id.content);
			if (currentFrag != null && currentFrag instanceof GameFragment) {
				GameMenuFragment menuFrag = new GameMenuFragment();
				menuFrag.setClient(FreeCol.getFreeColClient());
				menuFrag.show(getFragmentManager(), "");
				return true;
			}
    	}
    	return super.onKeyDown(keyCode, event);
    }
}