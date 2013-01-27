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

import java.util.ArrayList;
import java.util.List;

import net.sf.freecol.client.FreeColClient;
import net.sf.freecol.client.control.InGameController;
import net.sf.freecol.client.gui.ImageLibrary;
import net.sf.freecol.client.gui.i18n.Messages;
import net.sf.freecol.common.model.Building;
import net.sf.freecol.common.model.BuildingType;
import net.sf.freecol.common.model.Colony;
import net.sf.freecol.common.model.ColonyTile;
import net.sf.freecol.common.model.GoodsType;
import net.sf.freecol.common.model.StringTemplate;
import net.sf.freecol.common.model.Unit;

import org.freecolandroid.R;
import org.freecolandroid.ui.FreeColDialogFragment;
import org.freecolandroid.ui.RefreshRequestListener;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class WorkPickerDialogFragment extends FreeColDialogFragment implements OnItemClickListener {

    private Unit mUnit;

    private Colony mColony;

    private final List<WorkItem> mItems = new ArrayList<WorkItem>();

    private RefreshRequestListener mListener;

    public static WorkPickerDialogFragment newInstance(FreeColClient client, Colony colony,
            Unit unit, RefreshRequestListener listener) {
        WorkPickerDialogFragment dialog = new WorkPickerDialogFragment();
        dialog.init(client, colony, unit, listener);
        return dialog;
    }

    private void init(FreeColClient client, Colony colony, Unit unit,
            RefreshRequestListener listener) {
        setClient(client);
        mUnit = unit;
        mColony = colony;
        mListener = listener;
        populateWorkItems();
    }

    private void populateWorkItems() {

        ColonyTile also = (mUnit.getWorkLocation2() instanceof ColonyTile) ? (ColonyTile) mUnit
                .getWorkLocation2() : null;
        ImageLibrary imageLibrary = getImageLibrary();

        // Work in Field - automatically find the best location
        List<GoodsType> farmedGoods = mClient.getGame().getSpecification().getFarmedGoodsTypeList();
        for (GoodsType goodsType : farmedGoods) {
            ColonyTile bestTile = mColony.getVacantColonyTileFor(mUnit, false, goodsType);
            if (also != null) {
                int alsoProd = also.getProductionOf(mUnit, goodsType);
                if (bestTile == null) {
                    if (alsoProd > 0)
                        bestTile = also;
                } else {
                    if (alsoProd > bestTile.getProductionOf(mUnit, goodsType))
                        bestTile = also;
                }
            }
            if (bestTile != null) {
                int maxpotential = bestTile.getProductionOf(mUnit, goodsType);
                String text = Messages.message(StringTemplate.template(
                        goodsType.getId() + ".workAs").addAmount("%amount%", maxpotential));
                Bitmap icon = imageLibrary.getScaledGoodsImageIcon(goodsType, 0.66f).getImage()
                        .getBitmap();
                mItems.add(new WorkItem(icon, text, Type.TILE, goodsType.getId()));
            }
        }

        // Work at Building - show both max potential and realistic projection
        for (Building building : mColony.getBuildings()) {
            switch (building.getNoAddReason(mUnit)) {
            case NONE:
            case ALREADY_PRESENT:
                GoodsType goodsType = building.getGoodsOutputType();
                String locName = Messages.message(building.getNameKey());

                String text;
                Bitmap icon;
                if (goodsType != null) {
                    StringTemplate t = StringTemplate.template("model.goods.goodsAmount")
                            .addAmount("%amount%", building.getAdditionalProductionNextTurn(mUnit))
                            .addName("%goods%", goodsType);
                    text = locName + " (" + Messages.message(t) + ")";
                    icon = imageLibrary.getScaledGoodsImageIcon(goodsType, 0.66f).getImage()
                            .getBitmap();
                } else {
                    // No goods produced, use the name of the location
                    text = locName;
                    icon = null;
                }
                mItems.add(new WorkItem(icon, text, Type.BUILDING, building.getType().getId()));
                break;
            default:
                break;
            }
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog d = new Dialog(getActivity(), R.style.PopupDialog);
        d.setContentView(R.layout.frag_dialog_pick_work);

        ListView unitList = (ListView) d.findViewById(R.id.list);
        unitList.setAdapter(new WorkListAdapter());
        unitList.setOnItemClickListener(this);

        return d;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        WorkItem item = mItems.get(position);
        InGameController inGameController = mClient.getInGameController();
        if (item.type == Type.BUILDING) {
            BuildingType buildingType = mClient.getGame().getSpecification()
                    .getBuildingType(item.workId);
            Building building = mUnit.getColony().getBuilding(buildingType);
            if (building != mUnit.getLocation()) {
                inGameController.work(mUnit, building);
            }
        } else {
            // Work a Tile
            GoodsType goodsType = mClient.getGame().getSpecification().getGoodsType(item.workId);
            // Change workType first for the benefit of change listeners
            inGameController.changeWorkType(mUnit, goodsType);
            // Move unit to best producing ColonyTile
            ColonyTile bestTile = mColony.getVacantColonyTileFor(mUnit, false, goodsType);
            if (bestTile != null) {
                inGameController.work(mUnit, bestTile);
            }
        }
        dismiss();
        mListener.onRefreshRequested();
    }

    private static class WorkItem {
        final Bitmap icon;
        final String text;
        final Type type;
        final String workId;

        WorkItem(Bitmap icon, String text, Type type, String workId) {
            this.icon = icon;
            this.text = text;
            this.type = type;
            this.workId = workId;
        }
    }

    private enum Type {
        TILE, BUILDING
    }

    private class WorkListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(
                        R.layout.list_item_work_location, parent, false);
            }
            ImageView iconView = (ImageView) convertView.findViewById(R.id.icon);
            TextView textView = (TextView) convertView.findViewById(R.id.text);

            WorkItem item = mItems.get(position);
            iconView.setImageBitmap(item.icon);
            textView.setText(item.text);
            return convertView;
        }

    }

}
