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

package org.freecolandroid.ui.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.freecol.client.control.PreGameController;
import net.sf.freecol.client.gui.ImageLibrary;
import net.sf.freecol.client.gui.i18n.Messages;
import net.sf.freecol.common.model.Nation;
import net.sf.freecol.common.model.NationOptions;
import net.sf.freecol.common.model.NationOptions.NationState;
import net.sf.freecol.common.model.NationType;
import net.sf.freecol.common.model.Player;

import org.freecolandroid.R;
import org.freecolandroid.repackaged.java.awt.Image;
import org.freecolandroid.ui.FreeColFragment;

import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;



public class StartGameFragment extends FreeColFragment {
	
	private List<Nation> mNations;
	
	private Map<Nation, Player> mPlayers;
	
	private ImageLibrary mLibrary;
	
	private Button mStartButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.frag_start_game, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mLibrary = mClient.getGUI().getImageLibrary();
		mNations = new ArrayList<Nation>();
        mPlayers = new HashMap<Nation, Player>();
		Player player = mClient.getMyPlayer();
		List<Nation> allNations = player.getSpecification().getNations();
		NationOptions nationOptions = mClient.getGame().getNationOptions();
		
		for (Nation n : allNations) {
			if (n.isSelectable()) {
				NationState state = nationOptions.getNations().get(n);
	            if (state != null) {
	                mNations.add(n);
	                mPlayers.put(n, null);
	            }
			}
		}
		
		ListView nationList = (ListView) getView().findViewById(R.id.nation_list);
		nationList.setAdapter(new NationListAdapter());
		nationList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mStartButton.setEnabled(true);
				Nation nation = mNations.get(position);
				PreGameController pgc = mClient.getPreGameController();
				pgc.setNation(nation);
				pgc.setNationType(nation.getType());
			}
		});
		
		mStartButton = (Button) getView().findViewById(R.id.ok);
		mStartButton.setText(Messages.message("startGame"));
		mStartButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startGame();
			}
		});
	}
	
	private void startGame() {
		// Hide the fragment
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.remove(this);
		ft.commit();
		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				mClient.getMyPlayer().setReady(true);
				mClient.getPreGameController().requestLaunch();
				return null;
			}
		}.execute();
	}
	
	private class NationListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mNations.size();
		}

		@Override
		public Object getItem(int position) {
			return mNations.get(position);
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(getActivity()).inflate(
						R.layout.list_item_nation, parent, false);
			}
			
			Nation nation = mNations.get(position);
			
			// Flag
			Image image = mLibrary.getCoatOfArmsImage(nation, 1);
			ImageView flagView = (ImageView) convertView.findViewById(R.id.flag);
			flagView.setImageBitmap(image.getBitmap());
			
			// Name
			TextView name = (TextView) convertView.findViewById(R.id.name);
			name.setText(Messages.message(nation.getNameKey()));
			
			// Advantage
			NationType type = null;
			if (mPlayers.get(nation) == null) {
				type = nation.getType();
            } else {
            	type = mPlayers.get(nation).getNationType();
            }
			TextView description = (TextView) convertView.findViewById(R.id.description);
			description.setText(Messages.message(type.getId() + ".shortDescription" ));
			
			
			return convertView;
		}
		
	}

}
