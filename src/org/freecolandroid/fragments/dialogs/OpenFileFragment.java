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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.sf.freecol.client.gui.i18n.Messages;

import org.freecolandroid.R;
import org.freecolandroid.debug.FCLog;

import android.app.Dialog;
import android.content.Context;
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
import android.widget.ListView;
import android.widget.TextView;


public class OpenFileFragment extends FreeColDialogFragment {
	
	public static final String TAG = "OpenFileFragment";
	
	private static final String ARG_DIR = "directory";
	
	private static final String ARG_FILE_EXT = "file_extension";
	
	private File mSelectedFile;
	
	public static OpenFileFragment newInstance(String directory, String fileExt) {
		FCLog.log("Starting Open File dialog, dir=" + directory + ", ext="
				+ fileExt);
		OpenFileFragment f = new OpenFileFragment();
		Bundle args = new Bundle();
		args.putString(ARG_DIR, directory);
		args.putString(ARG_FILE_EXT, fileExt);
		f.setArguments(args);
		return f;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Dialog d = new Dialog(getActivity(), R.style.GameMenu);
		d.setContentView(R.layout.frag_dialog_open_file);
		return d;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		File dir = new File(getArguments().getString(ARG_DIR));
		String fileExt = getArguments().getString(ARG_FILE_EXT);
		
		final Button openButton = (Button) getDialog().findViewById(R.id.open);
		
		final ListView list = (ListView) getDialog().findViewById(R.id.file_list);
		final DirectoryListAdapter adapter = new DirectoryListAdapter(
				getActivity(), dir, fileExt);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mSelectedFile = (File) adapter.getItem(position);
				if (mSelectedFile.isDirectory()) {
					adapter.setDirectory(mSelectedFile);
					adapter.notifyDataSetInvalidated();
					openButton.setEnabled(false);
				} else {
					openButton.setEnabled(true);
				}
				FCLog.log("Selected " + mSelectedFile.getName());
			}
		});
		
		TextView title = (TextView) getDialog().findViewById(R.id.open_title);
		title.setText(Messages.message("openAction.name"));

		openButton.setText(Messages.message("openAction.name"));
		openButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dismiss();
				AsyncTask.THREAD_POOL_EXECUTOR.execute(new Runnable() {

					@Override
					public void run() {
						mClient.getInGameController().loadGame(mSelectedFile);
					}
				});
			}
		});
	}
	
	private static class DirectoryListAdapter extends BaseAdapter {
		
		private final List<File> mFiles = new ArrayList<File>();
		
		private File mParentDirectory;
		
		private final Context mContext;
		
		private final String mFileExt;
		
		public DirectoryListAdapter(Context context, File directory, String fileExt) {
			mContext = context;
			mFileExt = fileExt;
			setDirectory(directory);
		}
		
		public void setDirectory(File directory) {
			mFiles.clear();
			mParentDirectory = directory.getParentFile();
			if (mParentDirectory != null) {
				mFiles.add(mParentDirectory);
			}
			File[] files = directory.listFiles();
			if (files != null) {
				for (File f : files) {
					FCLog.log("Checking " + f.getName());
					if (f.getName().endsWith(mFileExt) || f.isDirectory()) {
						FCLog.log("Match");
						mFiles.add(f);
					} else {
						FCLog.log("Not a match");
					}
				}
			} else {
				FCLog.log("Not a directory");
			}
		}

		@Override
		public int getCount() {
			return mFiles.size();
		}

		@Override
		public Object getItem(int position) {
			return mFiles.get(position);
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.list_item_file, parent, false);
			}
			File file = mFiles.get(position);
			TextView text = (TextView) convertView.findViewById(R.id.name);
			if (file == mParentDirectory) {
				text.setText("..");
			} else {
				text.setText(file.getName());
			}
			return convertView;
		}
		
	}
}
