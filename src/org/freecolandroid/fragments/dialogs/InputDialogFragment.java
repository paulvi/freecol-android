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

import org.freecolandroid.R;
import org.freecolandroid.debug.FCLog;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InputDialogFragment extends FreeColDialogFragment {

	public interface InputDialogListener {

		void onNegativeSelected();

		void onPositiveSelected(String value);

	}

	private static final String KEY_MESSAGE = "message";

	private static final String KEY_DEFAULT = "default";

	private static final String KEY_POSITIVE = "positive";

	private static final String KEY_NEGATIVE = "negative";

	private static final String KEY_ALLOW_EMPTY = "allow_empty";

	private InputDialogListener mListener;

	public static InputDialogFragment newInstance(String message,
			String defaultValue, String positiveText, String negativeText,
			boolean allowEmpty, InputDialogListener listener) {
		InputDialogFragment f = new InputDialogFragment();
		f.mListener = listener;
		Bundle args = new Bundle();
		args.putString(KEY_MESSAGE, message);
		args.putString(KEY_DEFAULT, defaultValue);
		args.putString(KEY_POSITIVE, positiveText);
		args.putString(KEY_NEGATIVE, negativeText);
		args.putBoolean(KEY_ALLOW_EMPTY, allowEmpty);
		f.setArguments(args);
		return f;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Bundle args = getArguments();
		Dialog d = new Dialog(getActivity(), R.style.GameMenu);
		d.setContentView(R.layout.frag_dialog_text_input);
		d.setCancelable(false);

		TextView message = (TextView) d.findViewById(R.id.message);
		message.setText(args.getString(KEY_MESSAGE));

		final EditText edit = (EditText) d.findViewById(R.id.input);
		edit.setText(args.getString(KEY_DEFAULT));

		Button btn = (Button) d.findViewById(R.id.positive);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				FCLog.log("Dialog - positive selected");
				String value = edit.getText().toString();
				if (!TextUtils.isEmpty(value)
						|| args.getBoolean(KEY_ALLOW_EMPTY)) {
					mListener.onPositiveSelected(value);
					dismiss();
				}
			}
		});
		btn.setText(args.getString(KEY_POSITIVE));
		btn = (Button) d.findViewById(R.id.negative);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				FCLog.log("Dialog - negative selected");
				mListener.onNegativeSelected();
				dismiss();
			}
		});
		btn.setText(args.getString(KEY_NEGATIVE));
		return d;
	}

}
