

package tech.boshu.changjiangshidai.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.FinishedPurchaseOrderDetailItem;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)Adapter.java
 */

public class FinishedPurchaseOrderDetailItemAdapter extends CommonAdapter<FinishedPurchaseOrderDetailItem> {

    public FinishedPurchaseOrderDetailItemAdapter(Context context, List<FinishedPurchaseOrderDetailItem> datas) {
        super(context, datas);
        this.context = context;
        this.datas = datas;
        this.layoutId = R.layout.adapter_finished_purchase_order_detail_item;
    }


    public void convert(final ViewHolder holder, final FinishedPurchaseOrderDetailItem data) {
        TextView areaTextView = holder.getView(R.id.area);
        TextView standerTextView = holder.getView(R.id.stander);
        TextView countTextView = holder.getView(R.id.count);


        holder.setText(R.id.area, "产地:浙江杭州");
        holder.setText(R.id.stander, "规格: 1型");
        holder.setText(R.id.count, "200元*50条");
    }
}