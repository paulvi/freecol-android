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

package org.freecolandroid.ui.colony;

import java.util.ArrayList;
import java.util.List;

import net.sf.freecol.client.gui.i18n.Messages;
import net.sf.freecol.common.model.AbstractGoods;
import net.sf.freecol.common.model.BuildableType;
import net.sf.freecol.common.model.Colony;
import net.sf.freecol.common.model.ColonyTile;
import net.sf.freecol.common.model.Goods;
import net.sf.freecol.common.model.GoodsContainer;
import net.sf.freecol.common.model.GoodsType;
import net.sf.freecol.common.model.StringTemplate;
import net.sf.freecol.common.model.Unit;
import net.sf.freecol.common.model.WorkLocation;
import net.sf.freecol.common.resources.ResourceManager;

import org.freecolandroid.R;
import org.freecolandroid.debug.FCLog;
import org.freecolandroid.repackaged.java.awt.Image;
import org.freecolandroid.ui.FreeColFragment;
import org.freecolandroid.ui.RefreshRequestListener;
import org.freecolandroid.ui.adapters.BuildingsListAdapter;
import org.freecolandroid.ui.adapters.ConstructionProgressListAdapter;
import org.freecolandroid.ui.adapters.ConstructionProgressListAdapter.ConstructionProgress;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ColonyFragment extends FreeColFragment implements OnUnitLocationUpdatedListener, RefreshRequestListener {

    private Colony mColony;

    public static ColonyFragment newInstance(Colony colony) {
        ColonyFragment d = new ColonyFragment();
        d.mColony = colony;
        return d;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_colony, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().getActionBar().setTitle(mColony.getName());

        ColonyMapCanvas canvas = (ColonyMapCanvas) getView().findViewById(R.id.colony_canvas);
        canvas.init(mClient, mColony, (ImageView) getView().findViewById(R.id.dragShadow));
        canvas.setOnUnitLocationUpdatedListener(this);
    }
    
    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    private void refresh() {
        // Update the population info views
        updatePopulationInfo();

        // Update current production info
        updateProductionInfo();

        // Update the building grid
        updateBuildingInfo();

        // Update the warehouse display
        updateWarehouse();
    }

    private void updateWarehouse() {
        LinearLayout warehouseContainer = (LinearLayout) getView().findViewById(
                R.id.warehouse_container);
        warehouseContainer.removeAllViews();
        GoodsContainer container = mColony.getGoodsContainer();
        for (GoodsType goodsType : mColony.getSpecification().getGoodsTypeList()) {
            if (goodsType.isStorable()) {
                Goods goods = container.getGoods(goodsType);
                Bitmap icon = mClient.getGUI().getImageLibrary().getGoodsImage(goods.getType(), 1f)
                        .getBitmap();
                String amount = Integer.toString(goods.getAmount());
                View goodsView = getActivity().getLayoutInflater().inflate(
                        R.layout.list_item_goods, warehouseContainer, false);
                ImageView iconView = (ImageView) goodsView.findViewById(R.id.icon);
                iconView.setImageBitmap(icon);
                // Amount
                TextView amountView = (TextView) goodsView.findViewById(R.id.amount);
                amountView.setText(amount);
                // Production (change)
                TextView changeView = (TextView) goodsView.findViewById(R.id.change);
                int change = mColony.getAdjustedNetProductionOf(goodsType);
                if (change > 0) {
                    changeView.setTextColor(Color.GREEN);
                } else if (change < 0) {
                    changeView.setTextColor(Color.RED);
                } else {
                    // No production surplus/shortage
                    changeView.setVisibility(View.GONE);
                }
                changeView.setText(change > 0 ? "+" + change : Integer.toString(change));
                warehouseContainer.addView(goodsView, new LayoutParams(LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT));
            }
        }
    }

    private void updateBuildingInfo() {
        GridView buildingGrid = (GridView) getView().findViewById(R.id.buildig_grid);
        BuildingsListAdapter adapter = new BuildingsListAdapter(getActivity(), mClient, mColony,
                (ImageView) getView().findViewById(R.id.dragShadow));
        adapter.setOnUnitLocationUpdatedListener(this);
        buildingGrid.setAdapter(adapter);
    }

    private void updatePopulationInfo() {
        int population = mColony.getUnitCount();
        int members = mColony.getMembers();
        int rebels = mColony.getSoL();

        // Update the national shields
        ImageView rebelShield = (ImageView) getView().findViewById(R.id.rebel_shield);
        Image rebelImage = getImageLibrary().getCoatOfArmsImage(mColony.getOwner().getNation());
        rebelShield.setImageBitmap(rebelImage.getBitmap());

        ImageView royalShield = (ImageView) getView().findViewById(R.id.royal_shield);
        Image royalImage = getImageLibrary().getCoatOfArmsImage(
                mColony.getOwner().getNation().getRefNation());
        royalShield.setImageBitmap(royalImage.getBitmap());

        // Update Rebel & Royalist counts and percentages
        String rebelNumber = Messages.message(StringTemplate.template("colonyPanel.rebelLabel")
                .addAmount("%number%", members));
        String royalistNumber = Messages.message(StringTemplate.template(
                "colonyPanel.royalistLabel").addAmount("%number%", population - members));

        TextView rebelCount = (TextView) getView().findViewById(R.id.rebel_count);
        rebelCount.setText(rebelNumber);
        TextView rebelPercentage = (TextView) getView().findViewById(R.id.rebel_percentage);
        rebelPercentage.setText(Integer.toString(rebels) + "%");

        TextView royalistCount = (TextView) getView().findViewById(R.id.royal_count);
        royalistCount.setText(royalistNumber);
        TextView royalistPercentage = (TextView) getView().findViewById(R.id.royal_percentage);
        royalistPercentage.setText(Integer.toString(mColony.getTory()) + "%");

        // Update population and bonus counts
        TextView populationCount = (TextView) getView().findViewById(R.id.population_count);
        populationCount.setText(Messages.message(StringTemplate.template(
                "colonyPanel.populationLabel").addAmount("%number%", population)));
        TextView bonusCount = (TextView) getView().findViewById(R.id.bonus);
        bonusCount.setText(Messages.message(StringTemplate.template("colonyPanel.bonusLabel")
                .addAmount("%number%", mColony.getProductionBonus())));
    }

    private void updateProductionInfo() {
        BuildableType buildable = mColony.getCurrentlyBuilding();
        ImageView currentProductionImage = (ImageView) getView().findViewById(
                R.id.current_production_image);
        currentProductionImage.setImageBitmap(ResourceManager.getImage(
                buildable.getId() + ".image", 0.75).getBitmap());

        TextView currentProductionTitle = (TextView) getView().findViewById(
                R.id.current_production_title);
        currentProductionTitle.setText(Messages.message(StringTemplate.template(
                "colonyPanel.currentlyBuilding").addName("%buildable%", buildable)));
        TextView currentProductionTurns = (TextView) getView().findViewById(
                R.id.current_production_turns);
        int turnsToComplete = mColony.getTurnsToComplete(buildable);
        String turnsStr = Messages.getTurnsText(turnsToComplete);
        currentProductionTurns.setText(Messages.message(StringTemplate.template(
                "turnsToComplete.long").addName("%number%", turnsStr)));
        ListView currentProductionProgress = (ListView) getView().findViewById(
                R.id.current_production_progress_list);
        List<ConstructionProgress> list = new ArrayList<ConstructionProgress>();
        for (AbstractGoods requiredGoods : buildable.getGoodsRequired()) {
            int amountNeeded = requiredGoods.getAmount();
            int amountAvailable = mColony.getGoodsCount(requiredGoods.getType());
            int amountProduced = mColony.getAdjustedNetProductionOf(requiredGoods.getType());
            Bitmap icon = mClient.getGUI().getImageLibrary()
                    .getGoodsImageIcon(requiredGoods.getType()).getImage().getBitmap();
            list.add(new ConstructionProgress(icon, 0, amountNeeded, amountAvailable,
                    amountProduced));
        }
        currentProductionProgress.setAdapter(new ConstructionProgressListAdapter(list));
    }

    @Override
    public void unitLocationUpdated(Unit unit, WorkLocation location) {
        refresh();
        if (location instanceof ColonyTile) {
            // Show the work picker dialog
            WorkPickerDialogFragment dialog = WorkPickerDialogFragment.newInstance(mClient, mColony, unit, this);
            dialog.show(getFragmentManager(), null);
        }
    }

    @Override
    public void onRefreshRequested() {
        refresh();
    }
}
