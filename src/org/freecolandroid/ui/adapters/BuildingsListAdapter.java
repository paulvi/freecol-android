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

package org.freecolandroid.ui.adapters;

import java.util.Collections;
import java.util.List;

import net.sf.freecol.client.FreeColClient;
import net.sf.freecol.common.model.Ability;
import net.sf.freecol.common.model.AbstractGoods;
import net.sf.freecol.common.model.Building;
import net.sf.freecol.common.model.Colony;
import net.sf.freecol.common.model.ProductionInfo;
import net.sf.freecol.common.model.Unit;
import net.sf.freecol.common.resources.ResourceManager;

import org.freecolandroid.R;
import org.freecolandroid.ui.colony.OnUnitLocationUpdatedListener;
import org.freecolandroid.ui.colony.UnitDragHolder;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BuildingsListAdapter extends BaseAdapter implements OnDragListener, OnTouchListener {

    private final List<Building> mBuildings;

    private final Context mContext;

    private final FreeColClient mClient;

    private OnUnitLocationUpdatedListener mListener;

    private ImageView mDragShadowView;

    public BuildingsListAdapter(Context context, FreeColClient client, Colony colony, ImageView dragShadowView) {
        mBuildings = colony.getBuildings();
        mContext = context;
        mClient = client;
        mDragShadowView = dragShadowView;
        Collections.sort(mBuildings);
    }

    @Override
    public int getCount() {
        return mBuildings.size();
    }

    @Override
    public Object getItem(int position) {
        return mBuildings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_building,
                    parent, false);
            convertView.setOnDragListener(this);
        }
        Building building = mBuildings.get(position);
        convertView.setTag(building);
        ImageView buildingImage = (ImageView) convertView.findViewById(R.id.building_image);
        buildingImage.setImageBitmap(ResourceManager
                .getImage(building.getType().getId() + ".image").getBitmap());

        // Set building production info
        TextView prodText = (TextView) convertView.findViewById(R.id.building_production);
        ImageView prodIcon = (ImageView) convertView.findViewById(R.id.production_icon);
        ProductionInfo info = building.getProductionInfo();
        if (info == null || info.getProduction().isEmpty()) {
            prodText.setVisibility(View.GONE);
            prodIcon.setVisibility(View.GONE);
        } else {
            prodText.setVisibility(View.VISIBLE);
            prodIcon.setVisibility(View.VISIBLE);
            AbstractGoods output = info.getProduction().get(0);
            if (output.getAmount() > 0) {
                if (building.hasAbility(Ability.AVOID_EXCESS_PRODUCTION)) {
                    int stored = building.getColony().getGoodsCount(output.getType());
                    int capacity = building.getColony().getWarehouseCapacity();
                    if (output.getAmount() + stored > capacity) {
                        output = new AbstractGoods(output.getType(), capacity - stored);
                    }
                }
                Bitmap icon = mClient.getGUI().getImageLibrary()
                        .getGoodsImageIcon(output.getType()).getImage().getBitmap();
                prodIcon.setImageBitmap(icon);
                prodText.setText("x " + Integer.toString(output.getAmount()));
            }
        }

        // Set list of working units
        LinearLayout unitsInBuilding = (LinearLayout) convertView
                .findViewById(R.id.units_in_building);
        unitsInBuilding.removeAllViews();
        for (Unit unit : building.getUnitList()) {

            Bitmap unitIcon = mClient.getGUI().getImageLibrary().getUnitImageIcon(unit).getImage()
                    .getBitmap();
            ImageView unitView = new ImageView(mContext);
            unitView.setOnTouchListener(this);
            unitView.setTag(unit);
            unitView.setLayoutParams(new LayoutParams(40, 40));
            unitView.setImageBitmap(unitIcon);
            unitsInBuilding.addView(unitView);
        }

        return convertView;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        if (event.getAction() == DragEvent.ACTION_DROP) {
            UnitDragHolder dragHolder = (UnitDragHolder) event.getLocalState();
            Unit unit = dragHolder.unit;
            Building targetBuilding = (Building) v.getTag();
            if (dragHolder.origin != targetBuilding) {
                assignUnitToBuilding(unit, targetBuilding);
                mListener.unitLocationUpdated(unit, targetBuilding);
            }
        }
        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Unit unit = (Unit) v.getTag();
            mDragShadowView.setImageBitmap(mClient.getGUI().getImageLibrary()
                    .getUnitImageIcon(unit).getImage().getBitmap());
            UnitDragHolder dragHolder = new UnitDragHolder(unit, unit.getWorkBuilding());
            v.startDrag(ClipData.newPlainText("Drag", "Drag"), new View.DragShadowBuilder(mDragShadowView),
                    dragHolder, 0);
        }
        return true;
    }

    private void assignUnitToBuilding(Unit unit, Building building) {
        mClient.getInGameController().work(unit, building);
    }

    public void setOnUnitLocationUpdatedListener(OnUnitLocationUpdatedListener listener) {
        mListener = listener;
    }

}
