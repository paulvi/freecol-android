/**
 *  Copyright (C) 2013   The FreeCol-Android Team
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

package org.freecolandroid.ui.colony;

import java.util.List;

import net.sf.freecol.client.FreeColClient;
import net.sf.freecol.common.model.Unit;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class UnitListAdapter extends BaseAdapter {

    private final List<Unit> mUnits;
    private final FreeColClient mClient;

    public UnitListAdapter(List<Unit> units, FreeColClient client) {
        mUnits = units;
        mClient = client;
    }

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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new ImageView(parent.getContext());
        }
        Unit unit = mUnits.get(position);

        Bitmap icon = mClient.getGUI().getImageLibrary().getUnitImageIcon(unit).getImage()
                .getBitmap();
        ((ImageView) convertView).setImageBitmap(icon);
        return convertView;
    }

}
