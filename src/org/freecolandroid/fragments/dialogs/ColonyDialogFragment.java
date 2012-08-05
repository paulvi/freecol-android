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

package org.freecolandroid.fragments.dialogs;

import net.sf.freecol.common.model.Colony;

import org.freecolandroid.R;
import org.freecolandroid.ui.ColonyMapCanvas;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.TextView;

public class ColonyDialogFragment extends FreeColDialogFragment {

	private Colony mColony;

	public static ColonyDialogFragment newInstance(Colony colony) {
		ColonyDialogFragment d = new ColonyDialogFragment();
		d.mColony = colony;
		return d;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Dialog d = new Dialog(getActivity(), R.style.MessageDialog);
		d.setContentView(R.layout.frag_dialog_colony);

		TextView title = (TextView) d.findViewById(R.id.title);
		title.setText(mColony.getName());

		ColonyMapCanvas canvas = (ColonyMapCanvas) d
				.findViewById(R.id.colony_canvas);
		canvas.init(mClient, mColony);

		return d;
	}

}
