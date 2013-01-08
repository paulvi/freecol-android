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

package org.freecolandroid.ui;

import net.sf.freecol.client.gui.i18n.Messages;

import org.freecolandroid.R;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoDialogFragment extends FreeColDialogFragment {
	
	private static final String KEY_MESSAGE = "message";
	
	private static final String KEY_ICON = "icon";
	
	public static InfoDialogFragment newInstance(String message, Bitmap icon) {
		InfoDialogFragment f = new InfoDialogFragment();
		Bundle args = new Bundle();
		args.putString(KEY_MESSAGE, message);
		args.putParcelable(KEY_ICON, icon);
		f.setArguments(args);
		return f;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Bundle args = getArguments();
		Dialog d = new Dialog(getActivity(), R.style.GameMenu);
		d.setContentView(R.layout.frag_dialog_info);
		
		TextView message = (TextView) d.findViewById(R.id.message);
		message.setText(args.getString(KEY_MESSAGE));
		
		Bitmap iconData = args.getParcelable(KEY_ICON);
		ImageView icon = (ImageView) d.findViewById(R.id.icon);
		if (iconData != null) {
			icon.setImageBitmap(iconData);
		} else {
			icon.setVisibility(View.GONE);
		}
		Button okButton = (Button) d.findViewById(R.id.ok);
		okButton.setText(Messages.message("ok"));
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		
		return d;
	}
}
