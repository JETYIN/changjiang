package tech.boshu.changjiangshidai.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.purchasehistory.PurchaseHistoryPrOrder;

/**
 * Created by Administrator on 2016/1/7.
 */
public class PurchaseHistoryAdapter extends CommonAdapter<PurchaseHistoryPrOrder> {

    public PurchaseHistoryAdapter(Context context, List<PurchaseHistoryPrOrder> datas) {
        super(context, datas);
        this.context = context;
        this.datas = datas;
        this.layoutId = R.layout.adapter_purchase_history;
    }

    @Override
    public void convert(ViewHolder holder, PurchaseHistoryPrOrder prOrder) {
        ImageView icon = holder.getView(R.id.icon);
        TextView customername = holder.getView(R.id.customername);
        TextView order_code = holder.getView(R.id.order_code);
        TextView order_create_time = holder.getView(R.id.order_create_time);
        TextView total_money = holder.getView(R.id.total_money);
        if (prOrder != null) {
            customername.setText(prOrder.provider);
            order_code.setText(prOrder.id);
            order_create_time.setText(prOrder.createTime);
            total_money.setText(String.valueOf("¥"+prOrder.totalMoney));
            if (prOrder.status == 0) {
                icon.setBackgroundResource(R.mipmap.icon_order_draft);
            } else if (prOrder.status == 1) {
                icon.setBackgroundResource(R.mipmap.icon_order);
            } else if (prOrder.status == 2) {
                icon.setBackgroundResource(R.mipmap.icon_order_canle);
            }
        }

    }
}
