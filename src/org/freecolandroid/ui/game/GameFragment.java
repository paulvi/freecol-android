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

import net.sf.freecol.client.gui.MapViewer;
import net.sf.freecol.client.gui.MapViewer.MapViewerListener;
import net.sf.freecol.client.gui.action.BuildColonyAction;
import net.sf.freecol.client.gui.action.FreeColAction;
import net.sf.freecol.client.gui.i18n.Messages;
import net.sf.freecol.common.model.Colony;
import net.sf.freecol.common.model.Tile;
import net.sf.freecol.common.model.Unit;

import org.freecolandroid.R;
import org.freecolandroid.debug.FCLog;
import org.freecolandroid.repackaged.javax.swing.ImageIcon;
import org.freecolandroid.ui.FreeColFragment;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class GameFragment extends FreeColFragment implements MapViewerListener,
		OnTouchListener {

	private final static String ACTION_PAN_CAMERA = "pan_camera";

	private final static String ACTION_MOVE_UNIT = "move_unit";

	private String mSelectedAction = ACTION_PAN_CAMERA;

	private ImageView mUnitImage;

	private TextView mUnitName;

	private TextView mUnitMoves;

	private ImageView mPrevUnit;

	private ImageView mNextUnit;

	private Tile mDragStart;

	private void buildColony() {
		AsyncTask.THREAD_POOL_EXECUTOR.execute(new Runnable() {
			
			@Override
			public void run() {
				// Make use of BuildColonyAction
				FreeColAction action = mClient.getActionManager().getFreeColAction(
						BuildColonyAction.id);
				action.update();
				if (action.isEnabled()) {
					action.actionPerformed(null);
				}
			}
		});
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		GameCanvas canvas = (GameCanvas) getView().findViewById(R.id.canvas);
		canvas.init(mClient);

		canvas.setOnTouchListener(this);

		mUnitImage = (ImageView) getView().findViewById(R.id.unit_image);
		mUnitName = (TextView) getView().findViewById(R.id.unit_name);
		mUnitMoves = (TextView) getView().findViewById(R.id.unit_moves);

		mPrevUnit = (ImageView) getView().findViewById(R.id.prev_unit);
		mPrevUnit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Unit currentUnit = mClient.getGUI().getMapViewer()
						.getActiveUnit();
				List<Unit> units = mClient.getMyPlayer().getUnits();
				int currentIndex = units.indexOf(currentUnit);
				if (currentIndex > 0) {
					mClient.getGUI().getMapViewer()
							.setActiveUnit(units.get(currentIndex - 1));
				}
			}
		});

		mNextUnit = (ImageView) getView().findViewById(R.id.next_unit);
		mNextUnit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Unit currentUnit = mClient.getGUI().getMapViewer()
						.getActiveUnit();
				List<Unit> units = mClient.getMyPlayer().getUnits();
				int currentIndex = units.indexOf(currentUnit);
				if (currentIndex < units.size() - 1) {
					mClient.getGUI().getMapViewer()
							.setActiveUnit(units.get(currentIndex + 1));
				}
			}
		});

		Unit activeUnit = mClient.getGUI().getMapViewer().getActiveUnit();
		if (activeUnit != null) {
			onUnitSelected(activeUnit);
		}

		mClient.getGUI().getMapViewer().addListener(this);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		activity.getActionBar().show();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.menu_actions, menu);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.frag_game, container, false);
	}

	@Override
	public void onResume() {
	    super.onResume();
	    getActivity().getActionBar().setTitle(R.string.app_name);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_move) {
			Toast.makeText(getActivity(), "Select target", Toast.LENGTH_SHORT)
					.show();
			mSelectedAction = ACTION_MOVE_UNIT;
			return true;
		} else if (id == R.id.action_end_turn) {
			mClient.getInGameController().endTurn();
		} else if (id == R.id.action_build_colony) {
			buildColony();
		}
		return false;
	}

	@Override
	public void onTileSelected(Tile tile) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int action = event.getAction();
		MapViewer map = mClient.getGUI().getMapViewer();
		if (action == MotionEvent.ACTION_DOWN) {
			mDragStart = map.convertToMapTile((int) event.getX(),
					(int) event.getY());
		} else if (action == MotionEvent.ACTION_UP) {
			Tile tile = null;
			try {
				tile = map.convertToMapTile((int) event.getX(),
						(int) event.getY());
			} catch (Exception e) {
				FCLog.log("Error getting tile", e);
			}
			if (tile != null) {
				if (ACTION_PAN_CAMERA.equals(mSelectedAction)) {
					if (tile == mDragStart) {
						// No dragging
						map.setFocus(tile);
						// Check if there is a colony in the tile
						Colony colony = tile.getColony();
						if (colony != null) {
							mClient.getGUI().showColonyPanel(colony);
						}
						
						// Check if there is a unit that can move this turn
						Unit unit = tile.getMovableUnit();
						if (unit == null) {
							// Otherwise select the first available unit
							unit = tile.getFirstUnit();
						}
						if (unit != null) {
							mClient.getGUI().setActiveUnit(unit);
						}
					} else if (mDragStart != null) {
						Unit unit = map.getActiveUnit();
						if (unit != null) {
							FCLog.log("Drag from " + mDragStart + " to " + tile);

							if (unit.getTile() != mDragStart) {
								// Not moving the selected unit, check if there
								// is a movable unit in the start tile
								unit = mDragStart.getMovableUnit();
							}
							if (unit != null) {
								// Move the unit:
								mClient.getInGameController().setDestination(
										unit, tile);
								if (mClient.currentPlayerIsMyPlayer()) {
									mClient.getInGameController()
											.moveToDestination(unit);
								}
							}
						}
					}
				} else if (ACTION_MOVE_UNIT.equals(mSelectedAction)) {
					// Move the unit:
					Unit unit = map.getActiveUnit();
					mClient.getInGameController().setDestination(unit, tile);
					if (mClient.currentPlayerIsMyPlayer()) {
						mClient.getInGameController().moveToDestination(unit);
					}
				}
			}
			mSelectedAction = ACTION_PAN_CAMERA;
		}
		return true;
	}

	@Override
	public void onUnitSelected(Unit unit) {
		if (unit != null) {
			mUnitName.setText(Messages.message(Messages.getLabel(unit)));
			mUnitMoves.setText(Messages.message("moves") + " "
					+ unit.getMovesAsString());
			ImageIcon unitIcon = mClient.getGUI().getImageLibrary()
					.getUnitImageIcon(unit);
			mUnitImage.setImageBitmap(unitIcon.getImage().getBitmap());

			// Enable or disable next/previous unit buttons
			List<Unit> units = mClient.getMyPlayer().getUnits();
			int currentIndex = units.indexOf(unit);
			if (currentIndex == 0) {
				mPrevUnit.setVisibility(View.GONE);
			} else {
				mPrevUnit.setVisibility(View.VISIBLE);
			}
			if (currentIndex == units.size() - 1) {
				mNextUnit.setVisibility(View.GONE);
			} else {
				mNextUnit.setVisibility(View.VISIBLE);
			}
		}
	}

}
