package tech.boshu.changjiangshidai.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.Saleshistory;
import tech.boshu.changjiangshidai.bean.mode.PrOrder;
import tech.boshu.changjiangshidai.bean.mode.ResponseSaleHistory;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;

/**
 * Created by Administrator on 2016/1/7.
 */
public class AllSalesHistoryAdapter extends CommonAdapter<PrOrder> {
    private int type;

    public AllSalesHistoryAdapter(Context context, List<PrOrder> datas, int type) {
        super(context, datas);
        this.context = context;
        this.datas = datas;
        this.type = type;
        this.layoutId = R.layout.adapter_all_sales_history;
    }

    @Override
    public void convert(ViewHolder holder, PrOrder prOrder) {
        ImageView icon = holder.getView(R.id.icon);
        TextView customername = holder.getView(R.id.customername);
        TextView order_code = holder.getView(R.id.order_code);
        TextView order_create_time = holder.getView(R.id.order_create_time);
        TextView total_money = holder.getView(R.id.total_money);
        if (prOrder != null) {
            if (type == 0) {
                customername.setText(prOrder.store);
                order_code.setText(prOrder.billNo);
                order_create_time.setText(prOrder.reportDate);
                total_money.setText(String.valueOf(prOrder.totalMoney));

            } else {
                customername.setText(prOrder.customer);
                order_code.setText(prOrder.id);
                order_create_time.setText(prOrder.createTime);
                total_money.setText(String.valueOf(prOrder.totalMoney));
            }
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
