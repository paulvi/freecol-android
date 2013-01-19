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

import static org.freecolandroid.Constants.LOG_TAG;

import java.util.List;
import java.util.Locale;

import net.sf.freecol.client.ClientOptions;
import net.sf.freecol.client.FreeColClient;
import net.sf.freecol.client.gui.ImageLibrary;
import net.sf.freecol.client.gui.i18n.Messages;
import net.sf.freecol.common.model.AbstractGoods;
import net.sf.freecol.common.model.Colony;
import net.sf.freecol.common.model.ColonyTile;
import net.sf.freecol.common.model.GoodsType;
import net.sf.freecol.common.model.Map.Direction;
import net.sf.freecol.common.model.Player;
import net.sf.freecol.common.model.Player.NoClaimReason;
import net.sf.freecol.common.model.StringTemplate;
import net.sf.freecol.common.model.Tile;
import net.sf.freecol.common.model.TileType;
import net.sf.freecol.common.model.Unit;
import net.sf.freecol.common.model.UnitLocation.NoAddReason;

import org.freecolandroid.debug.FCLog;
import org.freecolandroid.repackaged.java.awt.Color;
import org.freecolandroid.repackaged.java.awt.Graphics2D;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;

public class ColonyMapCanvas extends SurfaceView implements Callback {

    private class PaintThread extends Thread {

        private boolean mRunning;

        @Override
        public void run() {
            Log.d(LOG_TAG, "ColonyMapCanvas.PaintThread.run() - start");
            System.out.println();
            Canvas canvas = null;
            while (mRunning) {
                try {
                    canvas = mHolder.lockCanvas();
                    onDraw(canvas);
                } catch (Exception e) {
                    Log.w(LOG_TAG, "Exception while drawing", e);
                } finally {
                    if (canvas != null) {
                        mHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
            Log.d(LOG_TAG, "ColonyMapCanvas.PaintThread.run() - stop");
        }

        public void setRunning(boolean running) {
            mRunning = running;
        }

    }

    private Tile[][] mTiles = new Tile[3][3];

    private PaintThread mPaintThread = new PaintThread();

    private SurfaceHolder mHolder;

    private Colony mColony;

    private FreeColClient mClient;

    private Graphics2D mGraphics;

    private Paint mPaint;

    private OnUnitLocationUpdatedListener mListener;

    private ImageView mDragShadowView;

    public ColonyMapCanvas(Context context) {
        super(context);

        mHolder = getHolder();
        mHolder.addCallback(this);
    }

    public ColonyMapCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);

        mHolder = getHolder();
        mHolder.addCallback(this);
    }

    public void init(FreeColClient client, Colony colony, ImageView dragShadowView) {
        mPaint = new Paint();
        mPaint.setColor(android.graphics.Color.RED);
        mPaint.setStyle(Style.FILL);
        mPaint.setStrokeWidth(5);

        mDragShadowView = dragShadowView;
        mClient = client;
        mColony = colony;
        mGraphics = new Graphics2D();
        Tile tile = colony.getTile();
        mTiles[0][0] = tile.getNeighbourOrNull(Direction.N);
        mTiles[0][1] = tile.getNeighbourOrNull(Direction.NE);
        mTiles[0][2] = tile.getNeighbourOrNull(Direction.E);
        mTiles[1][0] = tile.getNeighbourOrNull(Direction.NW);
        mTiles[1][1] = tile;
        mTiles[1][2] = tile.getNeighbourOrNull(Direction.SE);
        mTiles[2][0] = tile.getNeighbourOrNull(Direction.W);
        mTiles[2][1] = tile.getNeighbourOrNull(Direction.SW);
        mTiles[2][2] = tile.getNeighbourOrNull(Direction.S);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mColony != null && canvas != null) {
            mGraphics.setCanvas(canvas);

            mGraphics.setColor(Color.black);
            mGraphics.fillRect(0, 0, getWidth(), getHeight());

            TileType tileType = mColony.getTile().getType();
            ImageLibrary library = mClient.getGUI().getImageLibrary();
            int tileWidth = library.getTerrainImageWidth(tileType) / 2;
            int tileHeight = library.getTerrainImageHeight(tileType) / 2;
            // Draw terrain and improvements (fields, roads)
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    if (mTiles[x][y] != null) {
                        int xx = ((2 - x) + y) * tileWidth;
                        int yy = (x + y) * tileHeight;
                        canvas.save();
                        canvas.translate(xx, yy);
                        mClient.getGUI().getColonyTileGUI()
                                .displayColonyTile(mGraphics, mTiles[x][y], mColony);
                        canvas.restore();
                    }
                }
            }
            // Draw production and working units
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    if (mTiles[x][y] != null) {
                        int xx = ((2 - x) + y) * tileWidth;
                        int yy = (x + y) * tileHeight;
                        canvas.save();
                        canvas.translate(xx, yy);
                        ColonyTile tile = mColony.getColonyTile(mTiles[x][y]);
                        if (tile.isColonyCenterTile()) {
                            // Central tile containing the colony
                            drawProduction(tile.getProduction(), canvas, tileWidth * 2,
                                    tileHeight * 2);
                        }
                        List<Unit> workUnits = tile.getUnitList();
                        if (workUnits != null && !workUnits.isEmpty()) {
                            canvas.restore();
                            canvas.save();
                            Unit unit = workUnits.get(0);
                            Bitmap unitIcon = library.getUnitImageIcon(unit).getImage().getBitmap();
                            canvas.translate(xx + tileWidth - unitIcon.getWidth() / 2, yy);
                            canvas.drawBitmap(unitIcon, 0, 0, mPaint);
                        }
                        canvas.restore();
                    }
                }
            }
        }
    }

    private void drawProduction(List<AbstractGoods> production, Canvas canvas, int tileWidth,
            int tileHeight) {
        int verticalStops = production.size() + 1;
        float gapHeigh = tileHeight / (float) verticalStops;
        for (AbstractGoods goods : production) {
            // Move one step down
            canvas.translate(0, gapHeigh);
            int horizontalStops = goods.getAmount() + 1;
            float gapWidth = tileWidth / (float) horizontalStops;
            Bitmap goodsImage = mClient.getGUI().getImageLibrary().getGoodsImage(goods.getType())
                    .getBitmap();
            canvas.save();
            for (int i = 0; i < goods.getAmount(); i++) {
                // Move one step right
                canvas.translate(gapWidth, 0);
                // Adjust for bitmap width & height
                canvas.save();
                canvas.translate(-goodsImage.getWidth() / 2.0f, -goodsImage.getHeight() / 2.0f);
                canvas.drawBitmap(goodsImage, 0, 0, mPaint);
                canvas.restore();
            }
            canvas.restore();

        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mPaintThread.setRunning(true);
        mPaintThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        mPaintThread.setRunning(false);
        while (retry) {
            try {
                mPaintThread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public boolean onDragEvent(DragEvent event) {
        switch (event.getAction()) {
        case DragEvent.ACTION_DRAG_STARTED:
            return true;
        case DragEvent.ACTION_DROP:
            Unit unit = (Unit) event.getLocalState();
            handleUnitDrop(unit, (int) event.getX(), (int) event.getY());
            return true;
        default:
            return true;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // Check if we are tapping a Tile and if that tile contains an Unit
            ColonyTile workTile = getColonyTileAt((int) event.getX(), (int) event.getY());
            if (workTile != null) {
                List<Unit> units = workTile.getUnitList();
                if (units != null && !units.isEmpty()) {
                    Unit unit = units.get(0);
                    mDragShadowView.setImageBitmap(mClient.getGUI().getImageLibrary()
                            .getUnitImageIcon(unit).getImage().getBitmap());
                    startDrag(ClipData.newPlainText("Drag", "Drag"), new View.DragShadowBuilder(
                            mDragShadowView), unit, 0);
                }

            }
        }
        return true;
    }

    private void handleUnitDrop(Unit unit, int x, int y) {
        FCLog.log("Unit dropped at x=" + x + ", y=" + y);
        // Find the tile that the unit was dropped on
        ColonyTile workTile = getColonyTileAt(x, y);
        if (workTile != null) {
            boolean canWork = tryWork(workTile, unit);
            if (canWork) {
                mListener.unitLocationUpdated(unit, workTile);
            }
        }
    }

    private boolean tileContains(int px, int py, int tileWidth, int tileHeight) {
        int dx = Math.abs(tileWidth / 2 - px);
        int dy = Math.abs(tileHeight / 2 - py);
        return (dx + tileWidth * dy / tileHeight) <= tileWidth / 2;
    }

    private ColonyTile getColonyTileAt(int x, int y) {
        Rect tileRect = new Rect();
        TileType tileType = mColony.getTile().getType();
        ImageLibrary library = mClient.getGUI().getImageLibrary();
        int tileWidth = library.getTerrainImageWidth(tileType) / 2;
        int tileHeight = library.getTerrainImageHeight(tileType) / 2;
        // Draw terrain and improvements (fields, roads)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (mTiles[i][j] != null) {
                    int xx = ((2 - i) + j) * tileWidth;
                    int yy = (i + j) * tileHeight;
                    tileRect.set(xx, yy, xx + 2 * tileWidth, yy + 2 * tileHeight);
                    if (tileRect.contains(x, y)) {
                        if (tileContains(x - tileRect.left, y - tileRect.top, 2 * tileWidth,
                                2 * tileHeight)) {
                            return mColony.getColonyTile(mTiles[i][j]);
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * Try to work this tile with a specified unit.
     * 
     * @param unit
     *            The <code>Unit</code> to work the tile.
     * @return True if the unit succeeds.
     */
    private boolean tryWork(ColonyTile colonyTile, Unit unit) {
        Tile tile = colonyTile.getWorkTile();
        Player player = unit.getOwner();

        if (tile.getOwningSettlement() != mColony) {
            // Need to acquire the tile before working it.
            NoClaimReason claim = player.canClaimForSettlementReason(tile);
            switch (claim) {
            case NONE:
            case NATIVES:
                if (mClient.getInGameController().claimLand(tile, mColony, 0)
                        && tile.getOwningSettlement() == mColony) {
                    FCLog.log("Colony " + mColony.getName() + " claims tile " + tile.toString()
                            + " with unit " + unit.getId());
                } else {
                    FCLog.log("Colony " + mColony.getName() + " did not claim " + tile.toString()
                            + " with unit " + unit.getId());
                    return false;
                }
                break;
            default: // Otherwise, can not use land
                mClient.getGUI().errorMessage(
                        "noClaimReason." + claim.toString().toLowerCase(Locale.US));
                return false;
            }
            // Check reason again, claim should be satisfied.
            if (tile.getOwningSettlement() != mColony) {
                throw new IllegalStateException("Claim failed");
            }
        }

        // Claim sorted, but complain about other failure.
        NoAddReason reason = colonyTile.getNoAddReason(unit);
        if (reason != NoAddReason.NONE) {
            mClient.getGUI()
                    .errorMessage("noAddReason." + reason.toString().toLowerCase(Locale.US));
            return false;
        }

        // Choose the work to be done.
        // FTM, do not change the work type unless explicitly
        // told to as this destroys experience (TODO: allow
        // multiple experience accumulation?).
        GoodsType workType = unit.getWorkType();
        if (workType == null) {
            // Try to use expertise, then tile-specific
            workType = unit.getType().getExpertProduction();
            if (workType == null) {
                workType = colonyTile.getWorkType(unit);
            }
        }
        // Set the unit to work. Note this might upgrade the
        // unit, and possibly even change its work type as the
        // server has the right to maintain consistency.
        mClient.getInGameController().work(unit, colonyTile);
        // Now recheck, and see if we want to change to the
        // expected work type.
        if (workType != null && workType != unit.getWorkType()) {
            mClient.getInGameController().changeWorkType(unit, workType);
        }

        if (mClient.getClientOptions().getBoolean(ClientOptions.SHOW_NOT_BEST_TILE)) {
            ColonyTile best = mColony.getVacantColonyTileFor(unit, false, workType);
            if (best != null
                    && colonyTile != best
                    && (colonyTile.getProductionOf(unit, workType) < best.getProductionOf(unit,
                            workType))) {
                StringTemplate template = StringTemplate.template("colonyPanel.notBestTile")
                        .addStringTemplate("%unit%", Messages.getLabel(unit))
                        .add("%goods%", workType.getNameKey())
                        .addStringTemplate("%tile%", best.getLabel());
                mClient.getGUI().showInformationMessage(template);
            }
        }
        return true;
    }

    public void setOnUnitLocationUpdatedListener(OnUnitLocationUpdatedListener listener) {
        mListener = listener;
    }

}
