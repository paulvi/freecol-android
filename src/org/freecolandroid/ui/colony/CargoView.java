package org.freecolandroid.ui.colony;

import net.sf.freecol.client.FreeColClient;
import net.sf.freecol.client.gui.ImageLibrary;
import net.sf.freecol.client.gui.i18n.Messages;
import net.sf.freecol.common.model.Goods;
import net.sf.freecol.common.model.StringTemplate;
import net.sf.freecol.common.model.Unit;

import org.freecolandroid.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class CargoView extends FrameLayout {

    private class CargoListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mCarrier == null ? 0 : mCarrier.getGoodsCount() + mCarrier.getUnitCount();
        }

        @Override
        public Object getItem(int position) {
            throw new UnsupportedOperationException("Not implemented");
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            if (mCarrier != null && mCarrier.getGoodsCount() > 0 && mCarrier.getUnitCount() > 0) {
                return 2;
            } else {
                return 1;
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (mCarrier == null) {
                return 0;
            } else if (getViewTypeCount() == 1 || position < mCarrier.getUnitCount()) {
                return 0;
            } else {
                return 1;
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageLibrary lib = mClient.getGUI().getImageLibrary();
            if (position < mCarrier.getUnitCount()) {
                // Create or populate a unit view
                ImageView unitView;
                if (convertView != null) {
                    unitView = (ImageView) convertView;
                } else {
                    unitView = new ImageView(getContext());
                    convertView = unitView;
                }
                Bitmap icon = lib.getUnitImageIcon(mCarrier.getUnitList().get(position)).getImage()
                        .getBitmap();
                unitView.setImageBitmap(icon);
            } else {
                // Create or populate a goods view
                position -= mCarrier.getUnitCount();
                ImageView goodsView;
                if (convertView != null) {
                    goodsView = (ImageView) convertView;
                } else {
                    goodsView = new ImageView(getContext());
                    convertView = goodsView;
                }
                Bitmap icon = lib.getGoodsImage(mCarrier.getGoodsList().get(position).getType())
                        .getBitmap();
                goodsView.setImageBitmap(icon);
            }
            return convertView;
        }

    }

    private Unit mCarrier;

    private CargoListAdapter mAdapter;

    private FreeColClient mClient;

    private OnColonyUpdatedListener mListener;

    public CargoView(Context context) {
        super(context);
    }

    public CargoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CargoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater inf = LayoutInflater.from(getContext());
        inf.inflate(R.layout.view_cargo, this, true);
        mAdapter = new CargoListAdapter();
        GridView grid = (GridView) findViewById(R.id.grid);
        grid.setAdapter(mAdapter);
    }

    public void init(FreeColClient client, OnColonyUpdatedListener listener) {
        mClient = client;
        mListener = listener;
    }

    public void setCarrier(Unit carrier) {
        if (carrier != null && !carrier.isCarrier()) {
            throw new IllegalArgumentException("Unit must be a carrier");
        }
        mCarrier = carrier;
        update();
    }

    public void update() {
        TextView title = (TextView) findViewById(R.id.title);
        if (mCarrier == null) {
            // Show empty view
            title.setText(Messages.message("cargoOnCarrier"));
        } else {
            // Update the title
            int spaceLeft = mCarrier.getSpaceLeft();
            StringTemplate t = StringTemplate.template("cargoOnCarrierLong")
                    .addStringTemplate("%name%", Messages.getLabel(mCarrier))
                    .addAmount("%space%", spaceLeft);
            title.setText(Messages.message(t));
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onDragEvent(DragEvent event) {
        switch (event.getAction()) {
        case DragEvent.ACTION_DRAG_STARTED: {
            DragHolder holder = (DragHolder) event.getLocalState();
            return holder.goods != null;
        }
        case DragEvent.ACTION_DROP: {
            DragHolder holder = (DragHolder) event.getLocalState();
            int loadableAmount = mCarrier.getLoadableAmount(holder.goods.getType());
            loadableAmount = Math.min(loadableAmount, holder.goods.getAmount());
            if (loadableAmount > 0) {
                Goods goodsToAdd = new Goods(holder.goods.getGame(), holder.goods.getLocation(),
                        holder.goods.getType(), loadableAmount);
                holder.goods.setAmount(holder.goods.getAmount() - loadableAmount);
                mClient.getInGameController().loadCargo(goodsToAdd, mCarrier);
                mListener.onGoodsMoved();
            }
        }
            break;
        default:
            break;
        }
        return true;
    }
}
