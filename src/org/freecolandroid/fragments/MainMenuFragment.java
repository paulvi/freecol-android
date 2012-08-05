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

import net.sf.freecol.FreeCol;
import net.sf.freecol.client.gui.action.ActionManager;
import net.sf.freecol.client.gui.action.ContinueAction;
import net.sf.freecol.client.gui.action.MapEditorAction;
import net.sf.freecol.client.gui.action.NewAction;
import net.sf.freecol.client.gui.action.PreferencesAction;
import net.sf.freecol.client.gui.action.QuitAction;

import org.freecolandroid.R;
import org.freecolandroid.fragments.dialogs.OpenFileFragment;
import org.freecolandroid.integration.ButtonAction;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class MainMenuFragment extends FreeColFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.frag_main_menu, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		ActionManager am = mClient.getActionManager();
		
		// Continue
		Button btn = (Button) getView().findViewById(R.id.continue_playing);
		btn.setOnClickListener(new ButtonAction(am.getFreeColAction(ContinueAction.id)));
		
		// New Game
		btn = (Button) getView().findViewById(R.id.new_game);
		btn.setOnClickListener(new ButtonAction(am.getFreeColAction(NewAction.id)));
		
		// Open
		btn = (Button) getView().findViewById(R.id.open);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.addToBackStack(null);
				ft.hide(MainMenuFragment.this);
				OpenFileFragment openFragment = OpenFileFragment.newInstance(
						FreeCol.getSaveDirectory().getAbsolutePath() + "/", ".fsg");
				openFragment.setClient(mClient);
				openFragment.show(ft, OpenFileFragment.TAG);
			}
		});
		
		// Map Editor
		btn = (Button) getView().findViewById(R.id.map_editor);
		btn.setOnClickListener(new ButtonAction(am.getFreeColAction(MapEditorAction.id)));
		
		// Preferences
		btn = (Button) getView().findViewById(R.id.preferences);
		btn.setOnClickListener(new ButtonAction(am.getFreeColAction(PreferencesAction.id)));
		
		// Quit
		btn = (Button) getView().findViewById(R.id.quit);
		btn.setOnClickListener(new ButtonAction(am.getFreeColAction(QuitAction.id)));
	}
}
