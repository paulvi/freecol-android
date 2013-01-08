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

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import net.sf.freecol.client.gui.i18n.Messages;

import org.freecolandroid.R;
import org.freecolandroid.debug.FCLog;

import android.app.Dialog;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmDialogFragment extends FreeColDialogFragment {
	
	private static final String KEY_MESSAGE = "message";
	
	private static final String KEY_POSITIVE = "positive";
	
	private static final String KEY_NEGATIVE = "negative";
	
	private DialogListener mListener;
	
	private boolean mDialogCanceled = true;
	
	public static ConfirmDialogFragment newInstance(String message,
			String positiveText, String negativeText) {
		ConfirmDialogFragment d = new ConfirmDialogFragment();
		Bundle args = new Bundle();
		args.putString(KEY_MESSAGE, message);
		args.putString(KEY_POSITIVE, positiveText);
		args.putString(KEY_NEGATIVE, negativeText);
		d.setArguments(args);
		return d;
	}
	
	public static boolean showDialogAndWaitForResult(
			FragmentManager fragmentManager, String message,
			String positiveText, String negativeText) {
		final CountDownLatch latch = new CountDownLatch(1);
		final AtomicBoolean result = new AtomicBoolean(false);
		ConfirmDialogFragment dialog = newInstance(message, positiveText, negativeText);
		dialog.setListener(new DialogListener() {
			
			@Override
			public void onResult(boolean res) {
				result.set(res);
				latch.countDown();
			}
		});
		dialog.show(fragmentManager, "");
		try {
			FCLog.log("Dialog - starting to wait for result");
			latch.await();
			FCLog.log("Dialog - finshed waiting for result");
		} catch (InterruptedException e) {
			result.set(false);
		}
		return result.get();
	}
	
	public void setListener(DialogListener listener) {
		mListener = listener;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Bundle args = getArguments();
		Dialog d = new Dialog(getActivity(), R.style.GameMenu);
		d.setContentView(R.layout.frag_dialog_alert);
//		d.setOnDismissListener(new OnDismissListener() {
//			
//			@Override
//			public void onDismiss(DialogInterface dialog) {
//				if (mDialogCanceled) {
//					FCLog.log("Dialog - canceled");
//					mListener.onResult(false);
//				}
//			}
//		});
		d.setCancelable(false);
		
		TextView message = (TextView) d.findViewById(R.id.message);
		message.setText(Messages.message(args.getString(KEY_MESSAGE)));
		
		Button btn = (Button) d.findViewById(R.id.positive);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FCLog.log("Dialog - positive selected");
				mDialogCanceled = false;
				mListener.onResult(true);
				dismiss();
			}
		});
		btn.setText(Messages.message(args.getString(KEY_POSITIVE)));
		btn = (Button) d.findViewById(R.id.negative);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FCLog.log("Dialog - negative selected");
				mDialogCanceled = false;
				mListener.onResult(false);
				dismiss();
			}
		});
		btn.setText(Messages.message(args.getString(KEY_NEGATIVE)));
		return d;
	}
	
	private interface DialogListener {
		
		void onResult(boolean result);
		
	}

}
