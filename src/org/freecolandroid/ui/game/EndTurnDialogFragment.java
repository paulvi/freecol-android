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

package org.freecolandroid.ui.game;

import java.util.List;

import net.sf.freecol.client.gui.i18n.Messages;
import net.sf.freecol.common.model.StringTemplate;
import net.sf.freecol.common.model.Unit;

import org.freecolandroid.R;
import org.freecolandroid.repackaged.javax.swing.ImageIcon;
import org.freecolandroid.ui.FreeColDialogFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class EndTurnDialogFragment extends FreeColDialogFragment {

    private List<Unit> mUnits;

    private DialogListener mListener;

    public static EndTurnDialogFragment newInstance(List<Unit> units, DialogListener listener) {
        EndTurnDialogFragment f = new EndTurnDialogFragment();
        f.mUnits = units;
        f.mListener = listener;
        return f;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog d = new Dialog(getActivity(), R.style.GameMenu);
        d.setContentView(R.layout.frag_dialog_end_turn);

        TextView title = (TextView) d.findViewById(R.id.title);
        title.setText(Messages.message("endTurnDialog.name"));

        StringTemplate t = StringTemplate.template("endTurnDialog.areYouSure").addAmount(
                "%number%", mUnits.size());
        TextView message = (TextView) d.findViewById(R.id.message);
        message.setText(Messages.message(t));

        Button okButton = (Button) d.findViewById(R.id.ok);
        okButton.setText(Messages.message("ok"));
        okButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mListener.onPositiveSelected();
                dismiss();
            }
        });

        Button cancelButton = (Button) d.findViewById(R.id.cancel);
        cancelButton.setText(Messages.message("cancel"));
        cancelButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mListener.onNegativeSelected();
                dismiss();
            }
        });

        ListView unitList = (ListView) d.findViewById(R.id.unit_list);
        unitList.setAdapter(new UnitListAdapter());

        return d;
    }

    private class UnitListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mUnits.size();
        }

        @Override
        public Object getItem(int position) {
            return mUnits.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_unit,
                        parent, false);
            }

            Unit unit = mUnits.get(position);

            // Unit icon
            ImageIcon icon = mClient.getGUI().getImageLibrary().getUnitImageIcon(unit, 1.0);
            ImageView unitIcon = (ImageView) convertView.findViewById(R.id.icon);
            unitIcon.setImageBitmap(icon.getImage().getBitmap());

            // Name
            TextView name = (TextView) convertView.findViewById(R.id.name);
            name.setText(Messages.message(Messages.getLabel(unit)));

            // Location text
            TextView description = (TextView) convertView.findViewById(R.id.description);
            description.setText(Messages.message(unit.getLocation().getLocationName()));

            return convertView;
        }

    }

}
