package org.freecolandroid.ui.colony;

import net.sf.freecol.client.FreeColClient;
import net.sf.freecol.client.gui.ImageLibrary;
import net.sf.freecol.common.model.Colony;
import net.sf.freecol.common.model.Goods;
import net.sf.freecol.common.model.GoodsContainer;
import net.sf.freecol.common.model.GoodsType;

import org.freecolandroid.R;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WarehouseView extends LinearLayout {

    private Colony mColony;
    private OnColonyUpdatedListener mListener;
    private FreeColClient mClient;

    public WarehouseView(Context context) {
        super(context);
        init();
    }

    public WarehouseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WarehouseView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setGravity(Gravity.CENTER_VERTICAL);
        setOrientation(HORIZONTAL);
    }

    public void setup(FreeColClient client, Colony colony, OnColonyUpdatedListener listener) {
        mClient = client;
        mColony = colony;
        mListener = listener;
    }

    public void refresh() {
        removeAllViews();
        if (mColony != null) {
            GoodsContainer container = mColony.getGoodsContainer();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            ImageLibrary imageLibrary = mClient.getGUI().getImageLibrary();
            for (GoodsType goodsType : mColony.getSpecification().getGoodsTypeList()) {
                if (goodsType.isStorable()) {
                    final Goods goods = container.getGoods(goodsType);
                    Bitmap icon = imageLibrary.getGoodsImage(goods.getType(), 1f).getBitmap();
                    String amount = Integer.toString(goods.getAmount());
                    View goodsView = inflater.inflate(R.layout.list_item_goods, this, false);
                    final ImageView iconView = (ImageView) goodsView.findViewById(R.id.icon);
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

                    // Setup drag & drop
                    goodsView.setOnTouchListener(new OnTouchListener() {

                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                DragHolder holder = new DragHolder(goods, mColony);
                                v.startDrag(ClipData.newPlainText("Drag", "Drag"),
                                        new DragShadowBuilder(iconView), holder, 0);
                            }
                            return true;
                        }
                    });

                    addView(goodsView, new LayoutParams(LayoutParams.WRAP_CONTENT,
                            LayoutParams.WRAP_CONTENT));

                }
            }
        }
    }

}
