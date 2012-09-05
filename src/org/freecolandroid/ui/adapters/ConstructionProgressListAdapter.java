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

package org.freecolandroid.ui.adapters;

import java.util.List;

import net.sf.freecol.client.gui.i18n.Messages;
import net.sf.freecol.common.model.StringTemplate;

import org.freecolandroid.R;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ConstructionProgressListAdapter extends BaseAdapter {

	public static class ConstructionProgress {

		public final Bitmap icon;

		public final int min;

		public final int max;

		public final int value;

		public final int step;

		public ConstructionProgress(Bitmap bitmap, int min, int max, int value,
				int step) {
			this.icon = bitmap;
			this.min = min;
			this.max = max;
			this.value = value;
			this.step = step;
		}

	}

	private final List<ConstructionProgress> mProgress;

	public ConstructionProgressListAdapter(List<ConstructionProgress> progress) {
		mProgress = progress;
	}

	@Override
	public int getCount() {
		return mProgress.size();
	}

	@Override
	public Object getItem(int position) {
		return mProgress.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(parent.getContext()).inflate(
					R.layout.list_item_construction_progress, parent, false);
		}
		ConstructionProgress progress = mProgress.get(position);
		ImageView imageView = (ImageView) convertView.findViewById(R.id.icon);
		imageView.setImageBitmap(progress.icon);
		TextView text = (TextView) convertView.findViewById(R.id.progress);
		String stepSignal = (progress.step < 0) ? "-" : "+";
		String progressString = String.valueOf(progress.value) + stepSignal
				+ Math.abs(progress.step) + "/" + progress.max;
		String turnsString = Messages.message("notApplicable.short");
		if (progress.max <= progress.value) {
			turnsString = "0";
		} else if (progress.step > 0) {
			// There is progress, find how many turns necessary with current
			// production
			int turns = (progress.max - progress.value) / progress.step;
			if ((progress.max - progress.value) % progress.step > 0) {
				turns++;
			}
			turnsString = Integer.toString(turns);
		}
		progressString += " "
				+ Messages.message(StringTemplate.template(
						"turnsToComplete.short").addName("%number%",
						turnsString));
		text.setText(progressString);
		return convertView;
	}

}
