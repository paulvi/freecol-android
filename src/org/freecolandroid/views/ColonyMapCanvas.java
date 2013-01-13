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

package org.freecolandroid.views;

import static org.freecolandroid.Constants.LOG_TAG;

import java.util.List;

import net.sf.freecol.client.FreeColClient;
import net.sf.freecol.client.gui.ImageLibrary;
import net.sf.freecol.common.model.AbstractGoods;
import net.sf.freecol.common.model.Colony;
import net.sf.freecol.common.model.ColonyTile;
import net.sf.freecol.common.model.Map.Direction;
import net.sf.freecol.common.model.Tile;
import net.sf.freecol.common.model.TileType;

import org.freecolandroid.repackaged.java.awt.Color;
import org.freecolandroid.repackaged.java.awt.Graphics2D;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.Toast;

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
                    // mRunning = false;
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

    public void init(FreeColClient client, Colony colony) {
        mPaint = new Paint();
        mPaint.setColor(android.graphics.Color.RED);
        mPaint.setStyle(Style.FILL);
        mPaint.setStrokeWidth(5);

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
            Toast.makeText(getContext(), "Dragged unit to map", Toast.LENGTH_SHORT).show();
            return true;
        default:
            return true;
        }

    }

}
