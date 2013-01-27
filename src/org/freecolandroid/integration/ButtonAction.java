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

package org.freecolandroid.integration;

import net.sf.freecol.client.gui.action.FreeColAction;
import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;

public class ButtonAction implements OnClickListener {

    private final FreeColAction action;

    public ButtonAction(FreeColAction action) {
        this.action = action;
    }

    @Override
    public void onClick(View view) {
        // Some Actions will perform operations that are blocking
        AsyncTask.THREAD_POOL_EXECUTOR.execute(new Runnable() {

            @Override
            public void run() {
                action.actionPerformed(null);
            }
        });
    }

}
