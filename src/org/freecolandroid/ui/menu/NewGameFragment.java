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

package org.freecolandroid.ui.menu;

import java.io.IOException;

import net.sf.freecol.client.control.ConnectController;
import net.sf.freecol.common.io.FreeColTcFile;
import net.sf.freecol.common.model.NationOptions.Advantages;
import net.sf.freecol.common.model.Specification;

import org.freecolandroid.R;
import org.freecolandroid.ui.FreeColFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

public class NewGameFragment extends FreeColFragment {

    private Specification specification;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_new_game, container, false);
    }

    public void setSpecification(Specification specification) {
        if (specification == null) {
            FreeColTcFile tcData;
            try {
                tcData = new FreeColTcFile("classic");
                specification = tcData.getSpecification();
            } catch (IOException e) {
                Log.w(getTag(), e);
            }
        }
        this.specification = specification;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final Spinner spinner = (Spinner) getView().findViewById(R.id.difficulty);

        Button btn = (Button) getView().findViewById(R.id.ok);
        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                int level = spinner.getSelectedItemPosition();
                startGame(level);
            }
        });
    }

    private void startGame(final int difficulityLevel) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                ConnectController connectController = mClient.getConnectController();
                specification.applyDifficultyLevel(difficulityLevel);
                connectController.startSingleplayerGame(specification, "Player Name",
                        Advantages.FIXED);
            }

        }).start();
    }

}
