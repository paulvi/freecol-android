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

package org.freecolandroid.fragments;

import net.sf.freecol.common.model.Colony;

import org.freecolandroid.R;
import org.freecolandroid.ui.ColonyMapCanvas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ColonyFragment extends FreeColFragment {

	private Colony mColony;

	public static ColonyFragment newInstance(Colony colony) {
		ColonyFragment d = new ColonyFragment();
		d.mColony = colony;
		return d;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.frag_colony, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		TextView title = (TextView) getView().findViewById(R.id.title);
		title.setText(mColony.getName());

		ColonyMapCanvas canvas = (ColonyMapCanvas) getView().findViewById(
				R.id.colony_canvas);
		canvas.init(mClient, mColony);
	}

}
