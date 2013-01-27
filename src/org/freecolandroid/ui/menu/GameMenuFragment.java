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

import net.sf.freecol.FreeCol;
import net.sf.freecol.client.gui.action.SaveAction;
import net.sf.freecol.client.gui.i18n.Messages;

import org.freecolandroid.R;
import org.freecolandroid.integration.ButtonAction;
import org.freecolandroid.ui.FreeColDialogFragment;

import android.app.Dialog;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GameMenuFragment extends FreeColDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog d = new Dialog(getActivity(), R.style.GameMenu);
        d.setContentView(R.layout.frag_dialog_game_menu);
        return d;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button saveButton = (Button) getDialog().findViewById(R.id.save_game);
        saveButton.setText(Messages.message("saveAction.name"));
        saveButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
                new ButtonAction(mClient.getActionManager().getFreeColAction(SaveAction.id))
                        .onClick(v);
            }
        });

        Button openButton = (Button) getDialog().findViewById(R.id.open);
        openButton.setText(Messages.message("openAction.name"));
        openButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.remove(GameMenuFragment.this);
                OpenFileFragment openFragment = OpenFileFragment.newInstance(FreeCol
                        .getSaveDirectory().getAbsolutePath() + "/", ".fsg");
                openFragment.setClient(mClient);
                openFragment.show(ft, OpenFileFragment.TAG);
            }
        });
    }

}
